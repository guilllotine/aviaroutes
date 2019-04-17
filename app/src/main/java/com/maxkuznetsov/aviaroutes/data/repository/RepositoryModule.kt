package com.maxkuznetsov.aviaroutes.data.repository

import com.maxkuznetsov.aviaroutes.data.remote.CoordinatesService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by max
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(service: CoordinatesService): Repository = RepositoryImpl(service)
}