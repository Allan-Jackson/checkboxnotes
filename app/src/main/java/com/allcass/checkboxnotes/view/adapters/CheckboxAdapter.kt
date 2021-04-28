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

class CheckboxAdapter(private var mCheckboxList: MutableList<CheckBoxModel> = mutableListOf()) :
    RecyclerView.Adapter<CheckboxAdapter.CheckboxViewHolder>() {

    private lateinit var mListener: CheckBoxListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_checkbox, parent, false)
        return CheckboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckboxViewHolder, position: Int) {
        holder.loadView(mCheckboxList[position])
    }

    override fun getItemCount(): Int {
        return mCheckboxList.size
    }

    //inicializa o mListener - que cuida do evento do checkbox - na Activity
    fun attachListener(listener: CheckBoxListener) {
        mListener = listener
    }

    //atualiza os dados do adapter, necessário para centralizar a manipulação dos dados na camada de ViewModel
    //ou seja, a CheckBoxViewModel é quem vai atualizar os dados com uma lista LiveData - que vai chamar este método quando for alterada
    fun updateCheckBoxList(checkBoxList: List<CheckBoxModel>){
        mCheckboxList = checkBoxList as MutableList
        notifyDataSetChanged()
    }

    //função para inserir/criar um novo checkbox na lista, por padrão já inclui no final
    fun insertCheckbox(checkbox: CheckBoxModel, position: Int = itemCount){
        mCheckboxList.add(position,checkbox)
        notifyItemInserted(position)
    }

    //retorna a lista de checkBoxes como non-mutable list
    fun getCheckboxList(): List<CheckBoxModel> = mCheckboxList


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
