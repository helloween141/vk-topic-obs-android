package com.example.vktopicsobserver.domain.model

import com.example.vktopicsobserver.data.models.TopicItem

data class TopicModel(val uid: Int, val title: String, var groupPhoto: String)

fun TopicItem.mapToModel(): TopicModel {
    return TopicModel(uid = this.uid, title = this.title, groupPhoto = "")
}
