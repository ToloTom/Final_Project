package com.example.final_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.round

class CartActivity : AppCompatActivity(){

    private lateinit var cart_recyclerView: RecyclerView
    private var cartList: ArrayList<Equipment> = ArrayList()
    private lateinit var textView_total: TextView
    private var subtotal: Double = 00.00
    private var tax: Double = 0.095
    private lateinit var button_Home: Button
    private lateinit var button_Purchase: Button

    private val equipmentViewModel: EquipmentViewModel by viewModels{
        EquipmentViewModelFactory((application as EquipmentApplication).repository)
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cart_recyclerView = findViewById(R.id.recyclerView_cart)
        textView_total = findViewById(R.id.textView_Total)
        button_Home = findViewById(R.id.button_homeFromCart)
        button_Purchase = findViewById(R.id.button_cartOrder)

        val adapter = EquipmentListAdapter()
        cart_recyclerView.adapter = adapter
        cart_recyclerView.layoutManager = LinearLayoutManager(this)

        equipmentViewModel.allEquipment.observe(this, {
            equipments -> equipments?.let{
            for(equipment: Equipment in it){
                var i : Int = 0
                subtotal += equipment.quantity * equipment.price
                while(i < equipment.quantity){
                    cartList.add(equipment)
                    i++
                }
            }
        }
            tax = subtotal * tax
            val total: Double =  subtotal + tax

            textView_total.text = "Subtotal: $" + String.format("%.2f", subtotal).toDouble() +"\n" + "Tax: $" + String.format("%.2f", tax).toDouble() + "\n" + "Total: $" + String.format("%.2f", total).toDouble()

            var cart: List<Equipment> = cartList.toMutableList()
            adapter.submitList(cart)
        })

        val intent: Intent = Intent(this@CartActivity, MainActivity::class.java)

        button_Home.setOnClickListener { startActivity(intent) }

        button_Purchase.setOnClickListener {
            equipmentViewModel.allEquipment.observe(this,{
                equipments -> equipments?.let{
                    for(equipment: Equipment in it){
                        equipmentViewModel.updateEquipment(0, equipment.id)
                    }
                }
            })
            Toast.makeText(this,"Thank you for your purchase", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

    }
}