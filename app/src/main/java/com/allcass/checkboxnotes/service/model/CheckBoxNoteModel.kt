package com.allcass.checkboxnotes.service.model

import androidx.room.Embedded
import androidx.room.Relation

data class CheckBoxNoteModel (
    @Embedded val note: NoteModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "noteId"
    )
    val checkboxes: CheckBoxModel
)
