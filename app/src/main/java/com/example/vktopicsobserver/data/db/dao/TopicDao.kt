package com.example.vktopicsobserver.data.db.dao

import androidx.room.*
import com.example.vktopicsobserver.data.db.entity.Topic
import com.example.vktopicsobserver.data.db.entity.TopicWithComments

@Dao
interface TopicDao {
    @Insert
    fun insert(topic: Topic): Long

    @Update
    fun update(topic: Topic)

    @Query("SELECT EXISTS(SELECT * FROM topics WHERE uid = :topicId)")
    suspend fun isExists(topicId: Int): Boolean

    @Query("DELETE FROM topics")
    fun clear()
}