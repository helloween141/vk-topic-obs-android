package com.example.vktopicsobserver.data.services

import com.example.vktopicsobserver.data.models.TopicResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopicService {
    @GET("method/board.getTopics?")
    suspend fun getTopicsByGroupId(
        @Query("access_token") accessToken: String,
        @Query("group_id") groupId: Int,
        @Query("topic_ids") topicIds: String,
        @Query("v") version: String
    ): TopicResponse
}