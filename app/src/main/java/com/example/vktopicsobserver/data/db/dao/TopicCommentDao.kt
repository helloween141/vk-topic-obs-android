package com.example.vktopicsobserver.data.db.dao

import androidx.room.*
import com.example.vktopicsobserver.data.db.entity.TopicWithComments
import com.example.vktopicsobserver.settings.SettingsObject

@Dao
interface TopicCommentDao {
    @Transaction
    @Query(
        "SELECT * FROM topics ORDER BY  " +
                "CASE WHEN :sort = 'DESC' THEN id END DESC," +
                "CASE WHEN :sort = 'ASC' THEN id END ASC"
    )
    fun getTopicAndComments(sort: String = SettingsObject.topicsSort): List<TopicWithComments>
}