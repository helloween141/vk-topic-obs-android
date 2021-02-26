package com.example.vktopicsobserver.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vktopicsobserver.domain.model.TopicModel

@Entity(tableName = "topics")
data class Topic(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var uid: Int,
    var title: String = "",
    var groupPhoto: String = "",
)

fun TopicModel.mapToDB(): Topic {
    return Topic(
        uid = this.uid,
        title = this.title,
        groupPhoto = this.groupPhoto
    )
}