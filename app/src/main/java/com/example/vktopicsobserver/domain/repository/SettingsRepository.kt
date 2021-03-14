package com.example.vktopicsobserver.domain.repository

import android.app.Application
import com.example.vktopicsobserver.data.db.AppDatabase
import com.example.vktopicsobserver.data.db.dao.SettingsDao
import com.example.vktopicsobserver.data.db.entity.Settings
import com.example.vktopicsobserver.domain.repository.interfaces.iSettingsRepository
import com.example.vktopicsobserver.settings.SettingsObject
import com.example.vktopicsobserver.settings.mapToDB

class SettingsRepository(application: Application) : iSettingsRepository {
    private val db = AppDatabase.getInstance(application.applicationContext)!!
    private val settingsDao: SettingsDao = db.settingsDao()

    override suspend fun getAll(): Settings {
        return settingsDao.getAll()
    }

    override suspend fun save() {
        val test = SettingsObject.mapToDB()
        settingsDao.save(SettingsObject.mapToDB())
    }

}
