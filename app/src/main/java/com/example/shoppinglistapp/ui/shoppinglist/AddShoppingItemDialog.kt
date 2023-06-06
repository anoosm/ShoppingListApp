package com.example.shoppinglistapp.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener): AppCompatDialog(context) {
    private lateinit var binding: DialogAddShoppingItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDialogAdd.setOnClickListener {
            val name = binding.edtDialogName.text.toString()
            val quantity = binding.edtDialogQuantity.text.toString()

            if(name.isEmpty() || quantity.isEmpty()) {
                Toast.makeText(context, "Please enter the complete info", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, quantity.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        binding.tvDialogCancel.setOnClickListener {
            cancel()
        }
    }
}