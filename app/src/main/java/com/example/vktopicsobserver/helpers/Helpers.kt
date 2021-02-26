package com.example.vktopicsobserver.helpers

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    @SuppressLint("SimpleDateFormat")
    fun convertLongToTime(time: Long): String {
        val date = Date(time  * 1000)
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return format.format(date)
    }

    fun formatComment(text: String): String {
        val name = Regex("(?<=\\|).+?(?=\\])").find(text)?.value

        return Regex("(?=\\[).+?(?<=\\])").replaceFirst(text, name.toString())
    }

    fun parseTopicUrl(url: String) : Map<String, Int> {
        val data = Regex("(?<=\\-).*\\d").find(url)?.value
        val parts = data?.split("_")

        return mapOf(
            "groupId" to (parts?.get(0)?.toInt() ?: 0),
            "topicId" to (parts?.get(1)?.toInt() ?: 0),
        )
    }
}