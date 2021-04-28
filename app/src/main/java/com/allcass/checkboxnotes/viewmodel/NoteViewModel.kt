package com.allcass.checkboxnotes.viewmodel

import CheckboxAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel(){

    fun createCheckbox(editText: TextView, adapter: CheckboxAdapter){
        val text = editText.text.toString()
        val checked = false
        val position = adapter.checkboxList.size
        adapter.checkboxList.add(position,Pair(checked,text))
        adapter.notifyItemInserted(position)
    }
}
