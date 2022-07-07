package com.example.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


open class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val mNoteRepository: NoteRepository
    private val mAllNotes: LiveData<List<Note?>?>?
    fun getmAllNotes(): LiveData<List<Note?>?>? {
        return mNoteRepository.allNotes
    }

    fun insert(note: Note?) {
        mNoteRepository.insert(note)
    }

    fun delete(note: Note?) {
        mNoteRepository.delete(note)
    }

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        val mNoteDao = db!!.noteDao
        mNoteRepository = NoteRepository(mNoteDao!!)
        mAllNotes = mNoteRepository.allNotes
    }
}