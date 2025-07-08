package com.example.ecommercedemo.core.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationManagerCompat

class DeliveryReminderScheduler(private val context: Context) {
    fun canScheduleAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.getSystemService(AlarmManager::class.java).canScheduleExactAlarms()
        } else true
    }

    fun canNotifyUser(): Boolean {
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
    }

    fun scheduleReminder(deliveryTimeInMillis: Long) {
        if (!canScheduleAlarms() || !canNotifyUser()) {
            return
        }

        val triggerAtMillis = deliveryTimeInMillis - (30 * 60 * 1000)
        // Temporary for testing
        // val triggerAtMillis = System.currentTimeMillis() + 5000L
        val intent = Intent(context, DeliveryReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            2001,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            pendingIntent
        )
    }
}