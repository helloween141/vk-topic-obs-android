package com.example.vktopicsobserver.domain.repository.interfaces

import com.example.vktopicsobserver.domain.model.GroupModel


interface iGroupRepository {
    suspend fun fetchGroup(groupId: String) : GroupModel
}