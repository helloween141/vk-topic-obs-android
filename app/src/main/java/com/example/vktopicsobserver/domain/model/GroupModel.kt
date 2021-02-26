package com.example.vktopicsobserver.domain.model

import com.example.vktopicsobserver.data.models.GroupRemote

data class GroupModel(val uid: Int, val name: String, val photo: String)

fun GroupRemote.mapToModel(): GroupModel {
    return GroupModel(uid = this.uid, name = this.name, photo = this.photo_200)
}