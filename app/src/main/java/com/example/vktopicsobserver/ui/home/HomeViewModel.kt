package com.example.vktopicsobserver.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vktopicsobserver.data.db.entity.TopicGroup
import com.example.vktopicsobserver.data.db.entity.TopicWithComments
import com.example.vktopicsobserver.data.db.entity.mapToDB
import com.example.vktopicsobserver.data.models.CommentContent
import com.example.vktopicsobserver.domain.model.TopicModel
import com.example.vktopicsobserver.domain.model.mapToModel
import com.example.vktopicsobserver.domain.repository.*
import com.example.vktopicsobserver.ui.home.models.*
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _vkTopicRepository = TopicRepository(application)
    private val _topicGroupRepository = TopicGroupRepository(application)
    private val _vkCommentRepository = CommentRepository(application)
    private val _vkGroupRepository = GroupRepository()

    var loader: MutableLiveData<Boolean> = MutableLiveData()
    val vkListData: MutableLiveData<MutableList<Any>> = MutableLiveData<MutableList<Any>>()

    fun fetchData(refresh: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            if (!refresh) {
                getDataFromDB()
            }
            loader.postValue(true)
            getDataFromAPI()
            loader.postValue(false)
        }
    }

    fun deleteTopic(topicUid: Int) {
        val tempList: MutableList<Any>? = vkListData.value
        viewModelScope.launch(Dispatchers.Main) {
            val topic = withContext(Dispatchers.IO) { _vkTopicRepository.getOneByUid(topicUid) }
            tempList?.removeAll {
                (it is TopicCellModel && it.uid == topicUid) || (it is CommentCellModel && it.topicId == topic.id)
            }
            vkListData.postValue(tempList)
            withContext(Dispatchers.IO) { _topicGroupRepository.delete(topicUid) }
        }
    }

    private suspend fun getDataFromDB() {
        val tempList: MutableList<Any> = emptyList<Any>().toMutableList()
        getTopicWithComments().forEach {
            tempList.add(it.topic.mapToUI())
            tempList.addAll(it.comments.map { comment -> comment.mapToUI() })
        }
        vkListData.postValue(tempList)
    }

    private suspend fun getDataFromAPI() {
        val resultList: MutableList<Any> = emptyList<Any>().toMutableList()
        val topicsGroups = getTopicsGroups()
        try {
            // Очистка закешированных данных
            clearTopicComment()
            topicsGroups.forEach { topicGroup ->
                val (groupId, topicId) = listOf(topicGroup.groupId, topicGroup.topicId)
                val vkTopics = getTopics(groupId, topicId)

                vkTopics.forEach { vkTopic ->
                    vkTopic.apply {
                        this.groupPhoto =
                            withContext(Dispatchers.IO) { _vkGroupRepository.fetchGroup(groupId.toString()) }.photo
                    }
                    resultList.add(vkTopic.mapToUI())

                    // Сохранение топика в БД
                    val insertedTopicId =
                        withContext(Dispatchers.IO) { _vkTopicRepository.saveToDB(vkTopic.mapToDB()) }.toInt()
                    val vkComments = getComments(groupId, vkTopic.uid)
                    vkComments.items.forEach { comment ->
                        if (comment.from_id > 0) {
                            comment.apply {
                                this.profile =
                                    vkComments.profiles.single { it.uid == comment.from_id }
                                this.topicId = insertedTopicId
                            }
                            // Сохранение комментария в БД
                            withContext(Dispatchers.IO) {
                                _vkCommentRepository.saveToDB(
                                    comment.mapToModel().mapToDB()
                                )
                            }
                            Log.d("DEB4", "End")
                            resultList.add(comment.mapToModel().mapToUI())
                        }
                    }
                    vkListData.postValue(resultList)
                }
            }
        } catch (e: Exception) {
            Log.d("API_ERROR", e.toString())
        }
    }

    private suspend fun clearTopicComment() = withContext(Dispatchers.IO) {
        _vkTopicRepository.clearDataDB()
        _vkCommentRepository.clearDataDB()
    }

    private suspend fun getTopicsGroups(): List<TopicGroup> = withContext(Dispatchers.IO) {
        _topicGroupRepository.getAllFromDB()
    }

    private suspend fun getTopics(groupId: Int, topicId: Int): List<TopicModel> =
        withContext(Dispatchers.IO) {
            _vkTopicRepository.fetchTopics(groupId, topicId.toString())
        }

    private suspend fun getComments(groupId: Int, topicId: Int): CommentContent =
        withContext(Dispatchers.IO) {
            _vkCommentRepository.fetchComments(groupId, topicId)
        }

    private suspend fun getTopicWithComments(): List<TopicWithComments> =
        withContext(Dispatchers.IO) {
            _vkTopicRepository.getTopicWithComments()
        }
}