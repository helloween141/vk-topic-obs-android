package com.example.vktopicsobserver.domain.repository.interfaces

import com.example.vktopicsobserver.data.db.entity.TopicGroup

interface iTopicGroupRepository {

    suspend fun getAllFromDB(): List<TopicGroup>

    suspend fun add(groupId: Int, topicId: Int)

}