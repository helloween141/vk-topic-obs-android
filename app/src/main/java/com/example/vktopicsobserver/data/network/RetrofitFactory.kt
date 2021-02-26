package com.example.vktopicsobserver.data.network

import com.example.vktopicsobserver.data.services.CommentService
import com.example.vktopicsobserver.data.services.GroupService
import com.example.vktopicsobserver.data.services.TopicService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    companion object {
        val instance = RetrofitFactory()
        const val accessToken = "b5ba3241b5ba3241b5ba3241a5b5d28a0abb5bab5ba3241e9dd203a1ed3656177e2f61b"
        const val baseUrl = "https://api.vk.com/"
        const val version = "5.130"
    }

    private fun okHttpInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(okHttpInterceptor()).build()

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val vkGroupService: GroupService = retrofitClient.create(GroupService::class.java)
    val vkTopicService: TopicService = retrofitClient.create(TopicService::class.java)
    val vkCommentService: CommentService = retrofitClient.create(CommentService::class.java)
}
