package com.syntia.local_alarm_sample.domain.usecase.interactor

import com.syntia.local_alarm_sample.data.repository.MovieRepository
import com.syntia.local_alarm_sample.domain.usecase.GetMovieUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetMovieInteractor @Inject constructor(private val movieRepository: MovieRepository) : GetMovieUseCase {

    override fun getTrendingMovieCount(): Single<Int> {
        return movieRepository
            .getTrendingMovies()
            .map { it.totalResults }
            .observeOn(Schedulers.io())
    }
}