package com.example.shoppinglistapp.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]

        holder.itemName.text = currentItem.name
        holder.itemQuantity.text = "${currentItem.quantity}"

        holder.btnDelete.setOnClickListener {
            viewModel.delete(currentItem)
        }

        holder.btnAdd.setOnClickListener {
            currentItem.quantity++
            viewModel.insert(currentItem)
        }

        holder.btnRemove.setOnClickListener {
            if(currentItem.quantity > 0) {
                currentItem.quantity--
                viewModel.insert(currentItem)
            }
        }
    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val itemName: TextView = itemView.findViewById(R.id.tv_shopping_item)
        val itemQuantity: TextView = itemView.findViewById(R.id.tv_quanity)
        val btnDelete: ImageView = itemView.findViewById(R.id.iv_delete)
        val btnAdd: ImageView = itemView.findViewById(R.id.iv_plus)
        val btnRemove: ImageView = itemView.findViewById(R.id.iv_minus)
    }
}