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
import com.example.vktopicsobserver.domain.repository.CommentRepository
import com.example.vktopicsobserver.domain.repository.GroupRepository
import com.example.vktopicsobserver.domain.repository.TopicGroupRepository
import com.example.vktopicsobserver.domain.repository.TopicRepository
import com.example.vktopicsobserver.ui.home.models.*
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _vkTopicRepository = TopicRepository(application)
    private val _topicGroupRepository = TopicGroupRepository(application)
    private val _vkCommentRepository = CommentRepository(application)
    private val _vkGroupRepository = GroupRepository()

    var loader: MutableLiveData<Boolean> = MutableLiveData()
    val vkListData: MutableLiveData<MutableList<Any>> = MutableLiveData<MutableList<Any>>()

    init {
        fetchData(refresh = false)
    }

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

            topicsGroups.forEach{ topicGroup ->
                val (groupId, topicId) = listOf(topicGroup.groupId, topicGroup.topicId)
                val vkTopics = getTopics(groupId, topicId)

                vkTopics.forEach { vkTopic ->
                    vkTopic.apply {
                        this.groupPhoto = withContext(Dispatchers.IO) { _vkGroupRepository.fetchGroup(groupId.toString()) }.photo
                    }

                    // Сохранение топика в БД
                    val insertedTopicId = withContext(Dispatchers.IO) { _vkTopicRepository.saveToDB(vkTopic.mapToDB()) }.toInt()
                    resultList.add(vkTopic.mapToUI())

                    val vkComments = getComments(groupId, vkTopic.uid)
                    vkComments.items.forEach { comment ->
                        comment.apply {
                            this.profile = vkComments.profiles.filter { it.uid == comment.from_id }.single()
                            this.topicId = insertedTopicId
                        }
                        // Сохранение комментария в БД
                        withContext(Dispatchers.IO) { _vkCommentRepository.saveToDB(comment.mapToModel().mapToDB()) }
                        resultList.add(comment.mapToModel().mapToUI())
                    }
                    vkListData.postValue(resultList)
                }
            }
        }
        catch(e: Exception) {
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

    private suspend fun getTopics(groupId: Int, topicId: Int): List<TopicModel> = withContext(Dispatchers.IO) {
        _vkTopicRepository.fetchTopics(groupId, topicId.toString())
    }

    suspend fun getComments(groupId: Int, topicId: Int): CommentContent = withContext(Dispatchers.IO) {
        _vkCommentRepository.fetchComments(groupId, topicId)
    }

    private suspend fun getTopicWithComments(): List<TopicWithComments> = withContext(Dispatchers.IO) {
        _vkTopicRepository.getTopicWithComments()
    }
}