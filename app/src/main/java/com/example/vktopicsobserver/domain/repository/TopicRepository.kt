package com.example.vktopicsobserver.domain.repository

import android.app.Application
import com.example.vktopicsobserver.data.db.AppDatabase
import com.example.vktopicsobserver.data.db.dao.CommentDao
import com.example.vktopicsobserver.data.db.dao.TopicCommentDao
import com.example.vktopicsobserver.data.db.dao.TopicDao
import com.example.vktopicsobserver.data.db.entity.Topic
import com.example.vktopicsobserver.data.db.entity.TopicWithComments
import com.example.vktopicsobserver.data.db.entity.mapToDB
import com.example.vktopicsobserver.data.network.RetrofitFactory
import com.example.vktopicsobserver.domain.model.CommentModel
import com.example.vktopicsobserver.domain.model.TopicModel
import com.example.vktopicsobserver.domain.model.mapToModel
import com.example.vktopicsobserver.domain.repository.interfaces.iTopicRepository

class TopicRepository(application: Application) : iTopicRepository {
    private val db = AppDatabase.getInstance(application.applicationContext)!!
    private val topicDao: TopicDao = db.topicDao()
    private val topicCommentDao: TopicCommentDao = db.topicCommentDao()

    override suspend fun fetchTopics(groupId: Int, topicIds: String): List<TopicModel> {
        val result = RetrofitFactory.instance.vkTopicService.getTopicsByGroupId(
            accessToken = RetrofitFactory.accessToken,
            groupId = groupId,
            topicIds = topicIds,
            version = RetrofitFactory.version
        )

        if (result.errorResponse != null) {
            return listOf()
        }

        return result.successResponse!!.items.map { it.mapToModel() }
    }

    override suspend fun getTopicWithComments(): List<TopicWithComments> {
        return topicCommentDao.getTopicAndComments()
    }

    override suspend fun getOneByUid(topicUid: Int): Topic {
        return topicDao.getOneByUid(topicUid)
    }

    override suspend fun saveToDB(topic: Topic): Long {
        if (!topicDao.isExists(topic.uid)) {
            return topicDao.insert(topic)
        }
        return 0
    }

    override suspend fun clearDataDB() {
        topicDao.clear()
    }

}