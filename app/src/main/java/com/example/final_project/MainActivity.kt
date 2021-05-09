package com.example.final_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.service.autofill.Validators.not
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.math.RoundingMode.valueOf

class MainActivity : AppCompatActivity() {

    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var findCourtButton: Button
    private lateinit var requestActivityButton: Button
    private lateinit var imageViewLogo: ImageView
    private var googleMapsURL: String = "https://www.google.com/maps/search/?api=1&query=tennis+courts+nea+me"
    val racket1 = Equipment("Head Speed PRO", "file:///android_asset/Racket1.jpg","The SPEED PRO BLACK with the Graphene 360+ technology is made for the advanced tournament player who needs optimized control for their fast-paced game.", 249.99)
    val racket2 = Equipment("Head Speed MP", "file:///android_asset/Racket2.jpg", "The SPEED MP with the Graphene 360+ technology is for the player who needs control for their fast game but with more maneuverability and easier handling.", 239.99)
    val racket3 = Equipment("Head Extreme Tour", "file:///android_asset/Racket3.jpg", "The EXTREME TOUR includes innovative Graphene 360+ technology. It is designed for advanced tournament players looking for optimal spin and power.", 229.99)
    val string1 = Equipment("Synthetic Gut PPS (Gold)", "file:///android_asset/Strings1.jpg", "SYNTHETIC GUT PPS brings the same power, control, and comfort than the MASTER string, but packs some extra speed for club players.", 4.95)
    val string2 = Equipment("Synthetic Gut PPS (White)", "file:///android_asset/Strings2.jpg", "SYNTHETIC GUT PPS brings the same power, control, and comfort than the MASTER string, but packs some extra speed for club players.", 4.95)
    val string3 = Equipment("Synthetic Gut PPS (Black)", "file:///android_asset/Strings3.jpg", "SYNTHETIC GUT PPS brings the same power, control, and comfort than the MASTER string, but packs some extra speed for club players.", 4.95)
    val overgrip1 = Equipment("SUPERCOMP Overgrip (Black)", "file:///android_asset/Overgrip1.jpg", "SUPERCOMB is a tacky overwrap, which provides additional feel to club players.", 5.99)
    val overgrip2 = Equipment("SUPERCOMP Overgrip (White)", "file:///android_asset/Overgrip2.jpg", "SUPERCOMB is a tacky overwrap, which provides additional feel to club players.", 5.99)
    val overgrip3 = Equipment("SUPERCOMP Overgrip (Blue)", "file:///android_asset/Overgrip3.jpg", "SUPERCOMB is a tacky overwrap, which provides additional feel to club players.", 5.99)




    private val equipmentViewModel: EquipmentViewModel by viewModels{
        EquipmentViewModelFactory((application as EquipmentApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.recyclerView_main)
        findCourtButton = findViewById(R.id.findCourtButton)
        requestActivityButton = findViewById(R.id.button_requestRepair)
        imageViewLogo = findViewById(R.id.imageView_mainLogo)
        Picasso.get().load("file:///android_asset/Racketlogo.png").into(imageViewLogo)



        val adapter = EquipmentListAdapter()
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        equipmentViewModel.allEquipment.observe(this, {
            equipments -> equipments?.let{
                if(it.isEmpty()){

                    equipmentViewModel.insert(racket1)
                    equipmentViewModel.insert(racket2)
                    equipmentViewModel.insert(racket3)
                    equipmentViewModel.insert(string1)
                    equipmentViewModel.insert(string2)
                    equipmentViewModel.insert(string3)
                    equipmentViewModel.insert(overgrip1)
                    equipmentViewModel.insert(overgrip2)
                    equipmentViewModel.insert(overgrip3)
                }

            adapter.submitList(it)
            }
        })

        findCourtButton.setOnClickListener {
            var uri: Uri = Uri.parse(googleMapsURL)
            var intent: Intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        requestActivityButton.setOnClickListener {
            var intent: Intent = Intent(this@MainActivity, RepairDetailsActivity::class.java)
            startActivity(intent)
        }

    }
}