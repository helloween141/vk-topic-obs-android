package com.example.vktopicsobserver.domain.repository

import android.app.Application
import com.example.vktopicsobserver.data.db.AppDatabase
import com.example.vktopicsobserver.data.db.dao.CommentDao
import com.example.vktopicsobserver.data.db.dao.TopicGroupDao
import com.example.vktopicsobserver.data.db.entity.Comment
import com.example.vktopicsobserver.data.db.entity.TopicGroup
import com.example.vktopicsobserver.data.db.entity.mapToDB
import com.example.vktopicsobserver.data.models.CommentContent
import com.example.vktopicsobserver.data.network.RetrofitFactory
import com.example.vktopicsobserver.domain.model.CommentModel
import com.example.vktopicsobserver.domain.repository.interfaces.iCommentRepository

class CommentRepository(application: Application) : iCommentRepository {
    private val db = AppDatabase.getInstance(application.applicationContext)!!
    private val commentDao: CommentDao = db.commentDao()

    override suspend fun fetchComments(groupId: Int, topicId: Int): CommentContent {
        return RetrofitFactory.instance.vkCommentService.getComments(
            accessToken = RetrofitFactory.accessToken,
            groupId = groupId,
            topicId = topicId,
            extended = true,
            version = RetrofitFactory.version,
            sort = "desc",
            count = 10
        ).response
    }

    override suspend fun saveToDB(comment: Comment) {
        if (!commentDao.isExists(comment.uid)) {
            commentDao.insert(comment)
        } 
    }

    override suspend fun clearDataDB() {
        commentDao.clear()
    }
}
