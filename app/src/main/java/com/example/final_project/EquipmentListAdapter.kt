package com.example.final_project

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EquipmentListAdapter : ListAdapter<Equipment, EquipmentListAdapter.EquipmentViewHolder>(EquipmentComparator()){

    class EquipmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewName : TextView = itemView.findViewById(R.id.textView_ItemName)
        val textViewPrice: TextView = itemView.findViewById(R.id.textView_ItemPrice)
        val imageView : ImageView = itemView.findViewById(R.id.imageView_itemImage)

        fun bindText(text: String?, textView: TextView){
            textView.text = text
        }

        companion object{
            fun create(parent: ViewGroup) : EquipmentViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_equipment, parent,false)
                return EquipmentViewHolder(view)
            }
        }
    }

    class EquipmentComparator : DiffUtil.ItemCallback<Equipment>(){
        override fun areItemsTheSame(oldItem: Equipment, newItem: Equipment): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Equipment, newItem: Equipment): Boolean {
            return oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        return EquipmentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        val currentEquipment : Equipment  = getItem(position)
        holder.bindText(currentEquipment.title, holder.textViewName)
        holder.bindText("$" + currentEquipment.price.toString(), holder.textViewPrice)
        Picasso.get().load(currentEquipment.type).into(holder.imageView)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailedEquipmentActivity::class.java)
            intent.putExtra("Equipmentid", currentEquipment.id)
            startActivity(holder.itemView.context, intent, null)
        }

    }

}