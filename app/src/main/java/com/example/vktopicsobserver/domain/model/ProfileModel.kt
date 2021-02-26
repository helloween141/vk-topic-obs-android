package com.example.vktopicsobserver.domain.model

import com.example.vktopicsobserver.data.models.CommentProfile

data class ProfileModel(val uid: Int, val name: String, val photo: String)

fun CommentProfile.mapToModel(): ProfileModel {
    return ProfileModel(
        uid = this.uid,
        name = "${this.first_name} ${this.last_name}",
        photo = this.photo_100
    )
}
