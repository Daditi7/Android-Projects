package com.example.roomdatabase

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class NoteRepository internal constructor(private val mNoteDao: NoteDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allNotes: LiveData<List<Note?>?>?

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(note: Note?) {
        NoteRoomDatabase.databaseWriteExecutor.execute { mNoteDao.insert(note) }
    }

    fun delete(note: Note?) {
        NoteRoomDatabase.databaseWriteExecutor.execute { mNoteDao.delete(note) }
    }

    fun getA(note: Note?) {
        NoteRoomDatabase.databaseWriteExecutor.execute { mNoteDao.delete(note) }
    }

    init {
        allNotes = mNoteDao.allNotes
    }
}