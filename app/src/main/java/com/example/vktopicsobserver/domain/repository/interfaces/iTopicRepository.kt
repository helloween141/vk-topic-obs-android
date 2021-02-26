package com.example.vktopicsobserver.domain.repository.interfaces

import com.example.vktopicsobserver.data.db.entity.Topic
import com.example.vktopicsobserver.data.db.entity.TopicWithComments
import com.example.vktopicsobserver.domain.model.TopicModel


interface iTopicRepository {
    suspend fun fetchTopics(groupId: Int, topicIds: String) : List<TopicModel>
    suspend fun getTopicWithComments() : List<TopicWithComments>
    suspend fun saveToDB(topic: Topic): Long
    suspend fun clearDataDB()
}