package com.syntia.local_alarm_sample.data.source.service

import com.syntia.local_alarm_sample.data.source.config.ApiPath
import com.syntia.local_alarm_sample.data.source.response.ListItemResponse
import com.syntia.local_alarm_sample.data.source.response.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MovieService {

    @GET(ApiPath.TRENDING_MOVIE_WEEK)
    fun getTrendingMovies(): Single<ListItemResponse<MovieResponse>>
}