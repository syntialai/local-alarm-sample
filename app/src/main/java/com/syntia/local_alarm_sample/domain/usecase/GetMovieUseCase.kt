package com.syntia.local_alarm_sample.domain.usecase

import io.reactivex.Single

interface GetMovieUseCase {

    fun getTrendingMovieCount(): Single<Int>
}