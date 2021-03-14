package com.example.vktopicsobserver.settings

import com.example.vktopicsobserver.data.db.entity.Settings

object SettingsObject {
    var topicsSort: String = "DESC"
    var commentsSort: String = "DESC"
    var commentsPerTopic: Int = 10
}


fun SettingsObject.mapToDB(): Settings {
    return Settings(
        id = 1,
        topics_sort = this.topicsSort,
        comments_sort = this.commentsSort,
        comments_per_topic = this.commentsPerTopic
    )
}
