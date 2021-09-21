package com.syntia.local_alarm_sample.utils

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.syntia.local_alarm_sample.domain.usecase.GetMovieUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@HiltWorker
class DailyReminderWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getMovieUseCase: GetMovieUseCase
) : Worker(appContext, workerParams) {

    private var disposable: Disposable? = null

    override fun doWork(): Result {
        disposable = getMovieUseCase.getTrendingMovieCount().subscribe({ movieCount ->
            val title = "Today trending movie count is $movieCount"
            NotificationUtils.showNotification(appContext, title)
        }, { exception ->
            Log.e("Error fetch", "message" + exception.message.toString())
        })
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        disposable?.dispose()
    }
}