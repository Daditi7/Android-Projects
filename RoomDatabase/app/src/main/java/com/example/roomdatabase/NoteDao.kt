package com.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note?)

    @Delete
    fun delete(note: Note?)

    @get:Query("Select * from notes_table order by id ASC")
    val allNotes: LiveData<List<Note?>?>?
}