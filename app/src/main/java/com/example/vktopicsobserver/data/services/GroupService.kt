package com.example.vktopicsobserver.data.services

import com.example.vktopicsobserver.data.models.GroupResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupService {
    @GET("method/groups.getById?")
    suspend fun getGroupById(
        @Query("access_token") accessToken: String,
        @Query("group_id") groupId: String,
        @Query("v") version: String
    ): GroupResponse
}