package com.example.vktopicsobserver

import com.example.vktopicsobserver.helpers.Helpers
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun formatComment_isCorrect() {
        assertEquals("John", Helpers.formatComment("[id123213:bp-636534_3234|John]"))
    }

    @Test
    fun parseTopicUrl_isCorrect() {
        val result: Map<String, Int> = mapOf(
            "groupId" to 636534,
            "topicId" to 3234,
        )

        assertEquals(result, Helpers.parseTopicUrl("[id123213:bp-636534_3234]"))
    }
}