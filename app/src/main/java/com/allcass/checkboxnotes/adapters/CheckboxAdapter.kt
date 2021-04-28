import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allcass.checkboxnotes.R
import kotlinx.android.synthetic.main.list_item_checkbox.view.*


class CheckboxAdapter(val checkboxList: MutableList<Pair<Boolean,String>>)
    : RecyclerView.Adapter<CheckboxAdapter.CheckboxViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_checkbox, parent, false)
        return CheckboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckboxViewHolder, position: Int) {
        holder.loadView(checkboxList[position])
    }

    override fun getItemCount(): Int {
        return checkboxList.size
    }
    inner class CheckboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun loadView(checkbox: Pair<Boolean,String>){
            itemView.dynamic_check.isChecked = checkbox.first
            itemView.dynamic_check.text = checkbox.second
        }
    }
}