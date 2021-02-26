package com.example.vktopicsobserver.domain.model

import com.example.vktopicsobserver.helpers.Helpers
import com.example.vktopicsobserver.data.models.CommentItem

data class CommentModel(val uid: Int, val text: String, val date: String, val profile: ProfileModel, val topicId: Int)

fun CommentItem.mapToModel(): CommentModel {
    return CommentModel(
        uid = this.uid,
        text = Helpers.formatComment(this.text),
        date = Helpers.convertLongToTime(this.date),
        profile = this.profile.mapToModel(),
        topicId = this.topicId
    )
}

