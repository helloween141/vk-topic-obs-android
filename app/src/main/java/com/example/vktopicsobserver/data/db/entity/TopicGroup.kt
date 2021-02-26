package com.example.vktopicsobserver.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics_groups")
data class TopicGroup(
    var topicId: Int = 0,
    var groupId: Int = 0,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}