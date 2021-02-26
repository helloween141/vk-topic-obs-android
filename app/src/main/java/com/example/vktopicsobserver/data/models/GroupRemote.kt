package com.example.vktopicsobserver.data.models

import com.google.gson.annotations.SerializedName

data class GroupResponse(
    val response: List<GroupRemote>,
)

data class GroupRemote(
    @SerializedName("id")
    val uid: Int,
    val name: String,
    val screen_name: String,
    val is_closed: Int,
    val type: String,
    val photo_50: String,
    val photo_100: String,
    val photo_200: String,
)
