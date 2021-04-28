package com.allcass.checkboxnotes.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.recyclerview.widget.RecyclerView
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.view.listener.CheckBoxListener
import kotlinx.android.synthetic.main.list_item_checkbox.view.*

//todo: atualizar o adapter para receber CheckBoxModel

class CheckboxAdapter(val checkboxList: MutableList<CheckBoxModel> = mutableListOf()) :
    RecyclerView.Adapter<CheckboxAdapter.CheckboxViewHolder>() {

    private lateinit var mListener: CheckBoxListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_checkbox, parent, false)
        return CheckboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckboxViewHolder, position: Int) {
        holder.loadView(checkboxList[position])
    }

    override fun getItemCount(): Int {
        return checkboxList.size
    }

    fun attachListener(listener: CheckBoxListener) {
        mListener = listener
    }

    inner class CheckboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun loadView(checkbox: CheckBoxModel) {
            //itemView.dynamic_check.id = checkbox.id
            itemView.dynamic_check.isChecked = checkbox.status
            itemView.dynamic_check.text = checkbox.text
            itemView.dynamic_check.setOnCheckedChangeListener(
                object : OnCheckedChangeListener {
                    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                        mListener.onCheckChanged(checkbox, buttonView, isChecked)
                    }
                }
            )
        }
    }
}
