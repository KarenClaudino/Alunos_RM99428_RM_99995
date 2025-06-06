package karenclaudino.com.github.alunos_rm99428_rm_99995.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import karenclaudino.com.github.alunos_rm99428_rm_99995.R
import karenclaudino.com.github.alunos_rm99428_rm_99995.model.ItemModel

class ItemsAdapter(private val onItemClicked: (ItemModel) -> Unit) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val items = mutableListOf<ItemModel>()

    fun updateItems(newItems: List<ItemModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onItemClicked)
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewLocalName: TextView = itemView.findViewById(R.id.textViewLocalName)
        private val textViewEventType: TextView = itemView.findViewById(R.id.textViewEventType)
        private val textViewImpactDegree: TextView = itemView.findViewById(R.id.textViewImpactDegree)
        private val textViewEventDate: TextView = itemView.findViewById(R.id.textViewEventDate)
        private val textViewAffectedPeople: TextView = itemView.findViewById(R.id.textViewAffectedPeople)
        private val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)

        fun bind(item: ItemModel, onItemClicked: (ItemModel) -> Unit) {
            textViewLocalName.text = "Local: ${item.localName}"
            textViewEventType.text = "Tipo: ${item.eventType}"
            textViewImpactDegree.text = "Impacto: ${item.impactDegree}"
            textViewEventDate.text = "Data: ${item.eventDate}"
            textViewAffectedPeople.text = "Pessoas Afetadas: ${item.affectedPeople}"
            buttonDelete.setOnClickListener { onItemClicked(item) }
        }
    }
}


