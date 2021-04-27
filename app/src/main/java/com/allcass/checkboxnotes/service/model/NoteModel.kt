package com.allcass.checkboxnotes.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class NoteModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = ""

)
