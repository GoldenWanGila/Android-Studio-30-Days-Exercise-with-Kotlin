package com.example.day15_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.day15_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.sentNotificationButton.setOnClickListener(sentNotificationButtonClickHandler)
    }

    private val sentNotificationButtonClickHandler = View.OnClickListener {
        val channelId = "channel id test"

        val notificationChannel =
            NotificationChannel(channelId, "Sun", NotificationManager.IMPORTANCE_LOW)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Notification from Sun")
            .setContentText("It's the first time to use notification")
            // small icon is important for notification. Without it, will cause app crash.
            .setSmallIcon(R.mipmap.ic_notification_foreground)
            .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(1, notificationBuilder)
    }
}