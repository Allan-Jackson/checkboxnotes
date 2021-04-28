package com.allcass.checkboxnotes.service.model;

import androidx.room.*

@Entity(tableName = "CheckBox")
data class CheckBoxModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var noteId: Long = 0,
    var text: String = "",
    var status: Boolean = false
)



