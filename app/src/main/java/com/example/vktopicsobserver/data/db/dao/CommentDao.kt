package com.example.vktopicsobserver.data.db.dao

import androidx.room.*
import com.example.vktopicsobserver.data.db.entity.Comment
import com.example.vktopicsobserver.data.db.entity.Topic
import com.example.vktopicsobserver.data.db.entity.TopicWithComments

@Dao
interface CommentDao {
    @Insert
    fun insert(vararg comment: Comment)

    @Update
    fun update(vararg comment: Comment)

    @Query("SELECT EXISTS(SELECT * FROM comments WHERE uid = :commentId)")
    suspend fun isExists(commentId: Int): Boolean

    @Query("DELETE FROM comments")
    fun clear()
}