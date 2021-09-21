package com.syntia.local_alarm_sample.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.syntia.local_alarm_sample.R
import com.syntia.local_alarm_sample.presentation.view.MainActivity

object NotificationUtils {

    private const val NOTIFICATION_CHANNEL_ID = "NotificationChannelId"
    private const val NOTIFICATION_ID = 12345
    private const val CHANNEL_ID = "channel_id"
    private const val CHANNEL_NAME = "channel_name"

    fun showNotification(
        context: Context,
        title: String,
        notificationId: Int = NOTIFICATION_ID
    ) {
        val notificationManagerCompat = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        val notificationBuilder = getNotificationBuilder(
            context,
            title,
            NOTIFICATION_CHANNEL_ID
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManagerCompat, notificationBuilder)
        }

        notificationManagerCompat.notify(notificationId, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(
        notificationManager: NotificationManager,
        notificationBuilder: NotificationCompat.Builder,
        channelId: String = CHANNEL_ID,
        channelName: String = CHANNEL_NAME
    ) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationBuilder.setChannelId(channelId)
        notificationManager.createNotificationChannel(channel)
    }

    private fun getNotificationBuilder(
        context: Context,
        title: String,
        channelId: String
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentIntent(getPendingIntentToHome(context))
            .setAutoCancel(true)
    }


    private fun getPendingIntentToHome(context: Context) = PendingIntent.getActivity(
        context,
        1000,
        getHomeIntent(context),
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    private fun getHomeIntent(context: Context) = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
}