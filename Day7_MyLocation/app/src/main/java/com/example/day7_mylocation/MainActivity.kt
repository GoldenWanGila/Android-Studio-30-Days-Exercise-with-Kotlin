package com.example.day7_mylocation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val MY_PERMISSIONS_REQUEST_LOCATION = 100

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locationButton = findViewById<Button>(R.id.locationButton)
        locationButton.setOnClickListener(locationButtonHandler)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) showMapsActivity() else {
                Toast.makeText(this, "需要定位功能", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val locationButtonHandler = View.OnClickListener {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
        } else {
            showMapsActivity()
        }
    }

    private fun showMapsActivity() {
        val intentMapsActivity = Intent(this, MapsActivity::class.java)
        startActivity(intentMapsActivity)
    }
}