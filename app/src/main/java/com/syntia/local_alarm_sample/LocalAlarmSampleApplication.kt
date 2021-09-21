package com.syntia.local_alarm_sample

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.syntia.local_alarm_sample.presentation.broadcastreceiver.DailyReminderBroadcastReceiver
import com.syntia.local_alarm_sample.utils.AlarmManagerUtils
import com.syntia.local_alarm_sample.utils.DailyReminderWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class LocalAlarmSampleApplication : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        setDailyReminderAlarm()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    private fun setDailyReminderAlarm() {
        if (AlarmManagerUtils.isAlarmExist(applicationContext)) {
            AlarmManagerUtils.cancelAlarm(applicationContext)
        }
        AlarmManagerUtils.setDailyReminder(applicationContext)
    }
}