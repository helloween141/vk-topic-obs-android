package com.example.vktopicsobserver.ui.home.models

import com.example.vktopicsobserver.data.db.entity.Topic
import com.example.vktopicsobserver.domain.model.GroupModel

data class GroupCellModel(val name: String, val photo: String)

fun GroupModel.mapToUI(): GroupCellModel {
    return GroupCellModel(name = this.name, photo = this.photo)
}
