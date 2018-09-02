package com.tephra.mc.whatsupdoc.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.tephra.mc.whatsupdoc.data.service.NotificationService

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val service = Intent(context, NotificationService::class.java)
        context.startService(service)
    }

}