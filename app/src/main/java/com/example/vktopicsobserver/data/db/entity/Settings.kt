package com.example.vktopicsobserver.data.db.entity

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.vktopicsobserver.domain.model.TopicModel

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var topics_sort: String,
    var comments_sort: String,
    var comments_per_topic: Int,
)

val MIGRATION_12_13 = object : Migration(12, 13) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.insert(
            "settings",
            CONFLICT_REPLACE,
            ContentValues(3).apply {
                put("topics_sort", "DESC")
                put("comments_sort", "DESC")
                put("comments_per_topic", 10)
            })
    }
}