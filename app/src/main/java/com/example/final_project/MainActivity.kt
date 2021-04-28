package com.example.final_project

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.net.URI

class MainActivity : AppCompatActivity() {

    private lateinit var findCourtButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var googleMapsURL: String = "https://www.google.com/maps/search/?api=1&query=tennis+courts"
        findCourtButton = findViewById(R.id.findCourtButton)

        findCourtButton.setOnClickListener {
            var uri: Uri = Uri.parse(googleMapsURL)
            var intent: Intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}