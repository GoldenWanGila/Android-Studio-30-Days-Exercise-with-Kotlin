package com.example.day3_imagepicker

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Setting requestCode
    private val ACTION_ALBUM_REQUEST_CODE = 0
    private val ACTION_CAMERA_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val album = findViewById<Button>(R.id.album)
        val camera = findViewById<Button>(R.id.camera)
        // register must start before onStart
        val albumActivityResult = registerForActivityResult(StartActivityForResult()) { result ->
            onActivityResult(ACTION_ALBUM_REQUEST_CODE, result)
        }
        val cameraActivityResult = registerForActivityResult(StartActivityForResult()) { result ->
            onActivityResult(ACTION_CAMERA_REQUEST_CODE, result)
        }
        // 相簿
        album.setOnClickListener {
            // 此 intent 表示要獲取裝置裡面的檔案
            val intent = Intent(Intent.ACTION_PICK)
            // 透過設定 intent 的 type，可以限制要選取的檔案格式
            intent.type = "image/*"
            // launch 後，Android 會自動開啟合適的 APP 來滿足 intent
            albumActivityResult.launch(intent)
        }
        // 相機
        camera.setOnClickListener {
            // 此 intent 表示要獲取拍照後的照片
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // launch 後，Android 會自動開啟合適的 APP 來滿足 intent
            cameraActivityResult.launch(intent)
        }
    }

    private fun onActivityResult(requestCode: Int, result: ActivityResult) {
        /*
        requestCode: 自行先行設定的常數，用來區隔兩種 request
        result: contain resultCode: Int and data: Intent?
            resultCode-->status to indicate the success of the operation
            data-->an intent that carries the result data(nullable)
         */
        if (result.resultCode == Activity.RESULT_OK) {
            val photo = findViewById<ImageView>(R.id.photo)
            val intent = result.data
            when (requestCode) {
                ACTION_ALBUM_REQUEST_CODE -> {
                    val resolver = this.contentResolver
                    val selectedPhotoUri = intent?.data
                    val source = ImageDecoder.createSource(resolver, selectedPhotoUri!!)
                    photo.setImageBitmap(ImageDecoder.decodeBitmap(source))
                }
                ACTION_CAMERA_REQUEST_CODE -> {
                    photo.setImageBitmap(intent?.extras?.get("data") as Bitmap)
                }
                else -> {
                    Toast.makeText(this, "no correspond resultCode found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}