package com.syntia.local_alarm_sample.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.syntia.local_alarm_sample.presentation.broadcastreceiver.DailyReminderBroadcastReceiver
import java.util.Calendar

object AlarmManagerUtils {

    private const val DAILY_REMINDER_SCHEDULE_TIME = 9

    fun setDailyReminder(context: Context, intent: Intent = getClassIntent(context),
        timeToRemind: Int = DAILY_REMINDER_SCHEDULE_TIME) {
        val alarmManager = getAlarmManager(context)
        val pendingIntent = PendingIntent.getBroadcast(context,
            DailyReminderBroadcastReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            getReminderTimeInMillis(timeToRemind),
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun cancelAlarm(context: Context, intent: Intent = getClassIntent(context)) {
        val alarmManager = getAlarmManager(context)
        val pendingIntent = PendingIntent.getBroadcast(context,
            DailyReminderBroadcastReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE)
        pendingIntent?.let {
            alarmManager.cancel(it)
        }
    }

    fun isAlarmExist(context: Context, intent: Intent = getClassIntent(context)): Boolean {
        val pendingIntent = PendingIntent.getBroadcast(context,
            DailyReminderBroadcastReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE)
        return pendingIntent != null
    }

    private fun getAlarmManager(context: Context): AlarmManager = context.getSystemService(
        Context.ALARM_SERVICE) as AlarmManager

    private fun getClassIntent(context: Context) = Intent(context,
        DailyReminderBroadcastReceiver::class.java)

    private fun getReminderTimeInMillis(hour: Int) = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }.timeInMillis
}