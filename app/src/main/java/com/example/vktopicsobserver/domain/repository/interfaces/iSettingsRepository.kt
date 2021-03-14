package com.example.vktopicsobserver.domain.repository.interfaces

import com.example.vktopicsobserver.data.db.entity.Settings


interface iSettingsRepository {
    suspend fun getAll(): Settings
    suspend fun save()
}