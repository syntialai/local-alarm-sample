package com.syntia.local_alarm_sample.presentation.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.syntia.local_alarm_sample.utils.DailyReminderWorker
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class DailyReminderBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val REQUEST_CODE = 123
        const val DAILY_REMINDER_TAG = "DAILY_REMINDER"
    }

    @Inject @ApplicationContext
    lateinit var applicationContext: Context

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            enqueueDailyReminderWork(applicationContext)
        }
    }

    private fun enqueueDailyReminderWork(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<DailyReminderWorker>()
            .setConstraints(constraints)
            .addTag(DAILY_REMINDER_TAG)
            .build()
        WorkManager
            .getInstance(context)
            .enqueueUniqueWork(
                DAILY_REMINDER_TAG,
                ExistingWorkPolicy.REPLACE,
                oneTimeWorkRequest
            )
    }
}