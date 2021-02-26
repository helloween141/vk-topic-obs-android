package com.example.vktopicsobserver.data.db.entity

import androidx.room.*

data class TopicWithComments(
    @Embedded val topic: Topic,

    @Relation(
        parentColumn = "id",
        entityColumn = "topicId"
    )
    val comments: List<Comment>
)