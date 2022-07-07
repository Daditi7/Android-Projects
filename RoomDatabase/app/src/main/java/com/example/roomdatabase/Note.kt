package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
class Note {
    @ColumnInfo(name = "text")
    var text: String? = null

    @PrimaryKey(autoGenerate = true)
    var id = 0

    constructor() {}
    constructor(text: String) {
        this.text = text
    }
}