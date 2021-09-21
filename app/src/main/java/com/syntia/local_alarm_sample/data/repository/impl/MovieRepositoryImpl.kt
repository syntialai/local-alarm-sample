package com.syntia.local_alarm_sample.data.repository.impl

import com.syntia.local_alarm_sample.data.repository.MovieRepository
import com.syntia.local_alarm_sample.data.source.response.ListItemResponse
import com.syntia.local_alarm_sample.data.source.response.MovieResponse
import com.syntia.local_alarm_sample.data.source.service.MovieService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override fun getTrendingMovies(): Single<ListItemResponse<MovieResponse>> {
        return movieService
            .getTrendingMovies()
            .subscribeOn(Schedulers.io())
    }
}