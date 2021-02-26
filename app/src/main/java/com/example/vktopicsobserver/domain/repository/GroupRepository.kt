package com.example.vktopicsobserver.domain.repository

import com.example.vktopicsobserver.data.network.RetrofitFactory
import com.example.vktopicsobserver.domain.model.GroupModel
import com.example.vktopicsobserver.domain.model.mapToModel
import com.example.vktopicsobserver.domain.repository.interfaces.iGroupRepository

class GroupRepository : iGroupRepository {
    override suspend fun fetchGroup(groupId: String): GroupModel {
        return RetrofitFactory.instance.vkGroupService.getGroupById(
            accessToken = RetrofitFactory.accessToken,
            groupId = groupId,
            version = RetrofitFactory.version
        ).response.map { it.mapToModel() }.first()
    }

}