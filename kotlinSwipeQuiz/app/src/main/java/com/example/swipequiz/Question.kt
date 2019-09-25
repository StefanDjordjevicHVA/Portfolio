package com.example.swipequiz

data class Question(var question: String, var answer: Boolean)
{
    companion object
    {
        val QUESTIONS = arrayOf(
            "Blub",
            "Blob"
        )

        val ANSWERS = arrayOf(
            false,
            true
        )
    }
}