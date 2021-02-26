package com.example.vktopicsobserver.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.vktopicsobserver.data.db.entity.TopicGroup

@Dao()
interface TopicGroupDao {
    @Query("SELECT * FROM topics_groups ORDER BY groupId")
    suspend fun getAll(): List<TopicGroup>

    @Query("SELECT EXISTS(SELECT * FROM topics_groups WHERE groupId = :groupId AND topicId = :topicId)")
    suspend fun isExists(groupId: Int, topicId: Int): Boolean

    @Insert
    suspend fun insert(topicGroup: TopicGroup)

    @Delete
    suspend fun delete(topicGroup: TopicGroup)

}