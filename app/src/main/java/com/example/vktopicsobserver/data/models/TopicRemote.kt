package com.example.vktopicsobserver.data.models

import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("response")
    val successResponse: TopicContent? = null,
    @SerializedName("error")
    val errorResponse: ErrorContent? = null
)

data class TopicContent(
    val count: Int,
    val items: List<TopicItem>
)

data class TopicItem(
    @SerializedName("id")
    val uid: Int,
    val title: String,
    val created: Long,
    val created_by: Long,
    val updated: Long,
    val updated_by: Long,
    val is_closed: Int,
    val is_fixed: Int,
    val comments: Int,
)


data class ErrorContent(
    val error_code: Int,
    val error_msg: String,
    val request_params: List<ErrorParam>
)

data class ErrorParam(
    val key: String,
    val value: String,
)