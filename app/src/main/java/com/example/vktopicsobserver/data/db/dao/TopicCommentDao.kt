package com.example.vktopicsobserver.data.db.dao

import androidx.room.*
import com.example.vktopicsobserver.data.db.entity.TopicWithComments

@Dao
interface TopicCommentDao {
    @Transaction
    @Query("SELECT * FROM topics")
    fun getTopicAndComments(): List<TopicWithComments>
}