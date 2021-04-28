package com.allcass.checkboxnotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.service.model.NoteModel
import kotlinx.android.synthetic.main.list_item_note.view.*


class NoteAdapter(private var mNoteList: List<NoteModel> = arrayListOf()) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(mNoteList[position])
    }
    override fun getItemCount(): Int {
        return mNoteList.size
    }
    fun updateNotes(list: List<NoteModel>) {
        mNoteList = list
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(note: NoteModel){
            // Obtém os elementos de interface
            val textTitle = itemView.title_note

            // Atribui valores
            textTitle.text = note.title
        }
    }

}