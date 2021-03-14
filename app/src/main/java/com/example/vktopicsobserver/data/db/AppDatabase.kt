package com.example.vktopicsobserver.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vktopicsobserver.constants.DATABASE_NAME
import com.example.vktopicsobserver.constants.DATABASE_VERSION
import com.example.vktopicsobserver.data.db.dao.*
import com.example.vktopicsobserver.data.db.entity.*


@Database(
    entities = [Topic::class, Comment::class, TopicGroup::class, Settings::class],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topicCommentDao(): TopicCommentDao
    abstract fun topicDao(): TopicDao
    abstract fun commentDao(): CommentDao
    abstract fun topicGroupDao(): TopicGroupDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, DATABASE_NAME
                    )
                        .addMigrations(MIGRATION_12_13)
                        .build()
                }
            }
            return instance
        }


    }

}