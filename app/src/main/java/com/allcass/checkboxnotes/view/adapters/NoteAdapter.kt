package com.allcass.checkboxnotes.view.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.service.model.NoteModel
import com.allcass.checkboxnotes.view.listener.NoteListener
import kotlinx.android.synthetic.main.list_item_note.view.*


class NoteAdapter(private var mNoteList: List<NoteModel> = arrayListOf()) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    private lateinit var mListener: NoteListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_note, parent, false)
        return NoteViewHolder(view, mListener)
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
    fun attachListener(listener: NoteListener) {
        mListener = listener
    }

    inner class NoteViewHolder(itemView: View, private val listener: NoteListener) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: NoteModel) {
            // Obtém os elementos de interface
            val textTitle = itemView.title_note

            // Atribui valores
            textTitle.text = note.title

           textTitle.setOnClickListener {
                listener.onClick(note.id)
           }

            // Atribui eventos
            textTitle.setOnLongClickListener {
                AlertDialog.Builder(itemView.context)
                    .setTitle("REMOVER")
                    .setMessage("Deseja Remover a Nota?")
                    .setPositiveButton("Remover") { dialog, which ->
                        listener.onDelete(note.id)
                    }
                    .setNeutralButton("Cancelar", null)
                    .show()

                true
            }
        }
    }

}
