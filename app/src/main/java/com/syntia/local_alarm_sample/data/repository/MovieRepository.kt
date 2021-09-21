package com.syntia.local_alarm_sample.data.repository

import com.syntia.local_alarm_sample.data.source.response.ListItemResponse
import com.syntia.local_alarm_sample.data.source.response.MovieResponse
import io.reactivex.Single

interface MovieRepository {

    fun getTrendingMovies(): Single<ListItemResponse<MovieResponse>>
}