package com.tephra.mc.whatsupdoc.utils

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.tephra.mc.whatsupdoc.receiver.AlarmReceiver
import java.util.*

class NotificationUtils {

    fun setNotification(timeInMilliSeconds: Long, context: Context) {

        if (timeInMilliSeconds > 0) {

            val alarmManager = context.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context.applicationContext, AlarmReceiver::class.java) // AlarmReceiver1 = broadcast receiver

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeInMilliSeconds

            val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        }

    }
}