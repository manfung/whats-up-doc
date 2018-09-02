package com.tephra.mc.whatsupdoc.data.service

import android.app.*
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.tephra.mc.whatsupdoc.R
import com.tephra.mc.whatsupdoc.ui.login.LoginActivity

class NotificationService : IntentService("NotificationService") {

    companion object {
        const val CHANNEL_ID = "com.tephra.mc.whatsupdoc.CHANNEL_ID"
    }

    private lateinit var mNotification: Notification
    private val mNotificationId: Int = 1000

    private fun createChannel() {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, getString(R.string.notification_channel_name), NotificationManager.IMPORTANCE_HIGH)
            channel.description = getString(R.string.notification_channel_description)
            channel.enableVibration(true)
            channel.setShowBadge(true)
            channel.enableLights(true)
            channel.description = getString(R.string.notification_msg)
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }

    }

    override fun onHandleIntent(intent: Intent?) {

        createChannel()

        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val message = getString(R.string.notification_msg)

        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(mNotificationId, mBuilder.build())

    }

}