package com.example.vktopicsobserver.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vktopicsobserver.domain.model.CommentModel

@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var uid: Int,
    var text: String = "",
    var date: String = "",
    var profileId: Int = 0,
    var profileName: String = "",
    var profilePhoto: String = "",
    var topicId: Int = 0
)

fun CommentModel.mapToDB(): Comment {
    return Comment(
        uid = this.uid,
        text = this.text,
        date = this.date,
        profileId = this.profile.uid,
        profileName = this.profile.name,
        profilePhoto = this.profile.photo,
        topicId = this.topicId,
    )
}