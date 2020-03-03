package com.example.task5opdracht1

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainActivityViewModel(application: Application) : AndroidViewModel(application)
{
    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()
}