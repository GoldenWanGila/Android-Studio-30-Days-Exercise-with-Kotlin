package com.example.day16_pushmessenging

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MessageService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.e("firebase message", message.notification?.body!!)
        sendNotification(message.notification?.body!!)
    }

    private fun sendNotification(message: String) {
        val channelId = "channel id test"

        val notificationChannel =
            NotificationChannel(channelId, "Sun", NotificationManager.IMPORTANCE_LOW)
        val notificationBuilder = NotificationCompat.Builder(this, "channel id test")
            .setContentTitle("Notification from Firebase")
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(1, notificationBuilder)
    }
}