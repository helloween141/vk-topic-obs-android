package com.example.vktopicsobserver.domain.repository

import android.app.Application
import com.example.vktopicsobserver.data.db.AppDatabase
import com.example.vktopicsobserver.data.db.dao.TopicGroupDao
import com.example.vktopicsobserver.data.db.entity.TopicGroup
import com.example.vktopicsobserver.domain.repository.interfaces.iTopicGroupRepository

class TopicGroupRepository(application: Application) : iTopicGroupRepository {
    private val db = AppDatabase.getInstance(application.applicationContext)!!
    private val topicGroupDao: TopicGroupDao = db.topicGroupDao()

    override suspend fun getAllFromDB(): List<TopicGroup> {
        return topicGroupDao.getAll()
    }

    override suspend fun add(groupId: Int, topicId: Int) {
        if (!topicGroupDao.isExists(groupId, topicId)) {
            topicGroupDao.insert(TopicGroup(topicId, groupId))
        }
    }

    override suspend fun deleteByTopicId(topicId: Int) {
        topicGroupDao.deleteByTopicId(topicId)
    }

}