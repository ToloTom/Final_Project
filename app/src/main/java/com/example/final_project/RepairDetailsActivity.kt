package com.example.final_project

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class RepairDetailsActivity : AppCompatActivity() {

    companion object{
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }
    private lateinit var button_camera: Button
    private lateinit var button_submitRequest: Button
    private lateinit var button_backHome: Button
    private lateinit var imageView_racketRequest: ImageView
    private lateinit var textViewEditText: EditText
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repair)

        button_camera = findViewById(R.id.button_cameraRequest)
        button_submitRequest = findViewById(R.id.button_submitRequest)
        button_backHome = findViewById(R.id.button_backToHome)
        imageView_racketRequest = findViewById(R.id.imageView_racketRequest)
        textViewEditText = findViewById(R.id.editText_whatsWrong)

        button_camera.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }
            else{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            }
        }

        button_submitRequest.setOnClickListener {
            if(textViewEditText.text.toString() == ""){
                Toast.makeText(this, "Oops, looks like you forgot to tell us what's wrong. Please tell us what is wrong with your tennis racket(s).", Toast.LENGTH_LONG).show()
            }
            else{
                var intent: Intent = Intent(this@RepairDetailsActivity, MainActivity::class.java)
                Toast.makeText(this, "Thank you for submitting a maintenance repair service request. We will get back to you shortly on the next steps.", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
        }

        button_backHome.setOnClickListener { finish() }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                Toast.makeText(this,"Oops, you just denied the permission for camera. Don't worry you can allow it in the settings.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_REQUEST_CODE){
                val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap
                imageView_racketRequest.setImageBitmap(thumbnail)
            }
        }

    }
}