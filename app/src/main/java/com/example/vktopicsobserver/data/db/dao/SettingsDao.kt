package com.example.vktopicsobserver.data.db.dao

import androidx.room.*
import com.example.vktopicsobserver.data.db.entity.Settings

@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings LIMIT 1")
    fun getAll(): Settings

    @Update
    fun save(vararg settings: Settings)
}