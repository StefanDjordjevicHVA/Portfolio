package com.example.task5opdracht1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class EditViewModel (application: Application) : AndroidViewModel(application)
{
    private val noteRepository = NoteRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val note = MutableLiveData<Note?>()
    val error = MutableLiveData<String?>()
    val succes = MutableLiveData<Boolean>()

    fun updateNote()
    {
        if (isNoteValid())
        {
            mainScope.launch {
                withContext(Dispatchers.IO){
                    noteRepository.updateNotepad(note.value!!)
                }
                succes.value
            }
        }
    }

    private fun isNoteValid(): Boolean
    {
        return when
        {
            note.value == null ->
            {
                error.value = "Note must not be null"
                false
            }
            note.value!!.title.isBlank() ->
            {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }
}