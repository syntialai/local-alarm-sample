package com.syntia.local_alarm_sample.di

import com.syntia.local_alarm_sample.domain.usecase.GetMovieUseCase
import com.syntia.local_alarm_sample.domain.usecase.interactor.GetMovieInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetMovieUseCase(getMovieInteractor: GetMovieInteractor): GetMovieUseCase
}