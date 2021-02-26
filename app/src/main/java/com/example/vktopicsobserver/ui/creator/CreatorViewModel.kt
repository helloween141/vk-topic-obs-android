package com.example.vktopicsobserver.ui.creator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vktopicsobserver.constants.TOPIC_CREATED
import com.example.vktopicsobserver.constants.WRONG_TOPIC_URL
import com.example.vktopicsobserver.helpers.Helpers
import com.example.vktopicsobserver.domain.repository.TopicGroupRepository
import kotlinx.coroutines.launch


class CreatorViewModel(application: Application) : AndroidViewModel(application) {
    private val topicGroupRepository: TopicGroupRepository = TopicGroupRepository(application)
    var resultMessage: MutableLiveData<String> = MutableLiveData()

    fun addTopic(url: String) {
        val topicInfo: Map<String, Int> = Helpers.parseTopicUrl(url)
        val topicId = topicInfo.getValue("topicId")
        val groupId = topicInfo.getValue("groupId")

        if (topicId > 0 && groupId > 0) {
            viewModelScope.launch {
                topicGroupRepository.add(groupId, topicId)
                resultMessage.value = TOPIC_CREATED
            }
        } else {
            resultMessage.value = WRONG_TOPIC_URL
        }
    }

}