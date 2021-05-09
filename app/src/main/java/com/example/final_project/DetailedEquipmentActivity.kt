package com.example.final_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.io.Serializable

class DetailedEquipmentActivity : AppCompatActivity() {

    private lateinit var textView_detailedName: TextView
    private lateinit var textview_detailedInfo: TextView
    private lateinit var textView_detailedPrice: TextView
    private lateinit var textView_detailedQuantity: TextView
    private lateinit var imageView_equipmentImage: ImageView
    private lateinit var button_goHome: Button
    private lateinit var button_detailedCart: Button
    private var str: String = ""

    private val equipmentViewModel: EquipmentViewModel by viewModels{
        EquipmentViewModelFactory((application as EquipmentApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailedequipment)


        textView_detailedName = findViewById(R.id.textView_detailedName)
        textview_detailedInfo = findViewById(R.id.textView_detailedInfo)
        textView_detailedPrice = findViewById(R.id.textView_detailedPrice)
        textView_detailedQuantity = findViewById(R.id.textView_detailedQuantity)
        imageView_equipmentImage = findViewById(R.id.imageView_detailedEquipment)

        button_goHome = findViewById(R.id.button_home)
        button_detailedCart = findViewById(R.id.button_detailedCart)

        intent = getIntent()
        var id : Int = Integer.valueOf(intent.getIntExtra("Equipmentid", 0))

        var cartIntent : Intent = Intent(this@DetailedEquipmentActivity, CartActivity::class.java)

        equipmentViewModel.getEquipment(id).observe(this, {
            equipment -> equipment?.let{
                    textView_detailedName.text = it.title
                    textView_detailedPrice.text = "$" + it.price.toString()
                    textview_detailedInfo.text = it.info
                    textView_detailedQuantity.text = "Quantity: " + it.quantity.toString()
                    str = it.quantity.toString()
                    Picasso.get().load(it.type).into(imageView_equipmentImage)


            }
        })



        button_goHome.setOnClickListener{
            finish()
        }

        button_detailedCart.setOnClickListener{
            var count: Int = Integer.valueOf(str) + 1
            equipmentViewModel.updateEquipment(count,id)
            startActivity(cartIntent)
        }




            // make a toast saying that its been added to cart

    }



}