package com.example.vktopicsobserver.ui.home.models

import com.example.vktopicsobserver.data.db.entity.Comment
import com.example.vktopicsobserver.domain.model.CommentModel
import com.example.vktopicsobserver.domain.model.ProfileModel

data class CommentCellModel(
    val uid: Int,
    val text: String,
    val date: String,
    val profile: ProfileModel
)

fun CommentModel.mapToUI(): CommentCellModel {
    return CommentCellModel(
        uid = this.uid,
        text = this.text,
        date = this.date,
        profile = this.profile
    )
}

fun Comment.mapToUI(): CommentCellModel {
    return CommentCellModel(
        uid = this.uid,
        text = this.text,
        date = this.date,
        profile = ProfileModel(this.profileId, this.profileName, this.profilePhoto)
    )
}