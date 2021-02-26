package com.example.vktopicsobserver.domain.repository.interfaces

import com.example.vktopicsobserver.data.db.entity.Comment
import com.example.vktopicsobserver.data.models.CommentContent
import com.example.vktopicsobserver.data.models.CommentResponse
import com.example.vktopicsobserver.domain.model.CommentModel


interface iCommentRepository {
    suspend fun fetchComments(groupId: Int, topicId: Int) : CommentContent
    suspend fun saveToDB(comment: Comment)
    suspend fun clearDataDB()
}