package com.example.ecommercedemo.core.scheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.ecommercedemo.R

class DeliveryReminderReceiver : BroadcastReceiver() {
    companion object {
        const val CHANNEL_ID = "delivery_reminder_channel"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Delivery Reminder")
            .setContentText("Your groceries will arrive in 30 minutes")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()


        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Delivery Reminders",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Shows reminders for scheduled deliveries"
        }

        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(2001, notification)
    }
}