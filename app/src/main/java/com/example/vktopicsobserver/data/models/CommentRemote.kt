package com.example.vktopicsobserver.data.models

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    val response: CommentContent
)

data class CommentContent(
    val count: Int,
    val items: List<CommentItem>,
    val profiles: List<CommentProfile>,
)

data class CommentItem(
    @SerializedName("id")
    val uid: Int,
    val from_id: Int,
    val date: Long,
    val text: String,
    var profile: CommentProfile,
    var topicId: Int = 0
)

data class CommentProfile(
    val first_name: String,
    @SerializedName("id")
    val uid: Int,
    val last_name: String,
    val can_access_closed: Boolean,
    val is_closed: Boolean,
    val sex: Int,
    val screen_name: String,
    val photo_50: String,
    val photo_100: String,
    val online_info: OnlineInfo,
    val online: Int
)

data class OnlineInfo(
    val visible: Boolean,
    val last_seen: Long,
    val is_online: Boolean,
    val app_id: Long,
    val is_mobile: Boolean
)
