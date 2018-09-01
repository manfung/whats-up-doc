package com.tephra.mc.whatsupdoc.service

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import com.tephra.mc.whatsupdoc.R
import com.tephra.mc.whatsupdoc.ui.login.LoginActivity


class NotificationService : IntentService("NotificationService") {

    companion object {
        const val CHANNEL_NAME = "com.tephra.mc.whatsupdoc.CHANNEL_NAME"
        const val CHANNEL_ID = "com.tephra.mc.whatsupdoc.CHANNEL_ID"
    }

    private lateinit var mNotification: Notification
    private val mNotificationId: Int = 1000

    @TargetApi(26)
    private fun createChannel() {
        // API 26+ supports richer notification display
        val context = this.applicationContext
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val importance = NotificationManager.IMPORTANCE_HIGH

        val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        notificationChannel.enableVibration(true)
        notificationChannel.setShowBadge(true)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.parseColor("#e8334a")
        notificationChannel.description = getString(R.string.notification_msg)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationManager.createNotificationChannel(notificationChannel)
    }

    override fun onHandleIntent(intent: Intent?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            createChannel()
        }

        val context = this.applicationContext
        val notifyIntent = Intent(this, LoginActivity::class.java)
        val title = getString(R.string.notification_title)
        val message = getString(R.string.notification_msg)
        val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            mNotification = Notification.Builder(this, CHANNEL_ID)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                            .bigText(message))
                    .setContentText(message).build()
        } else {

            mNotification = Notification.Builder(this)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                            .bigText(message))
                    .setContentText(message).build()

        }

        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(mNotificationId, mNotification)
    }

}