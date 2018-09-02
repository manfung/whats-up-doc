package com.tephra.mc.whatsupdoc.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.tephra.mc.whatsupdoc.data.receiver.AlarmReceiver

class NotificationUtils {

    fun setNotification(timeInMilliSeconds: Long, context: Context) {

        if (timeInMilliSeconds > 0) {

            val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            }

            alarmMgr?.set(
                    AlarmManager.RTC_WAKEUP,
                    timeInMilliSeconds,
                    alarmIntent
            )

        }
    }
}