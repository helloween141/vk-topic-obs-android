package com.example.vktopicsobserver.data.services

import com.example.vktopicsobserver.data.models.CommentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {
    @GET("method/board.getComments?")
    suspend fun getComments(
        @Query("access_token") accessToken: String,
        @Query("group_id") groupId: Int,
        @Query("topic_id") topicId: Int,
        @Query("extended") extended: Boolean,
        @Query("sort") sort: String,
        @Query("count") count: Int,
        @Query("v") version: String
    ): CommentResponse
}