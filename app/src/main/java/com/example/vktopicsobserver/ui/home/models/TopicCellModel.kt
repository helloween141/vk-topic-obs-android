package com.example.vktopicsobserver.ui.home.models

import com.example.vktopicsobserver.data.db.entity.Topic
import com.example.vktopicsobserver.data.models.TopicItem
import com.example.vktopicsobserver.domain.model.TopicModel

data class TopicCellModel(val uid: Int, val title: String, val groupPhoto: String)

fun TopicModel.mapToUI(): TopicCellModel {
    return TopicCellModel(uid = this.uid, title = this.title, groupPhoto = this.groupPhoto)
}

fun Topic.mapToUI(): TopicCellModel {
    return TopicCellModel(uid = this.uid, title = this.title, groupPhoto = this.groupPhoto)
}