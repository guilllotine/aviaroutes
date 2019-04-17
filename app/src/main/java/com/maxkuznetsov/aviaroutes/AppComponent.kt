package com.maxkuznetsov.aviaroutes

import com.maxkuznetsov.aviaroutes.data.remote.ApiModule
import com.maxkuznetsov.aviaroutes.data.repository.RepositoryModule
import com.maxkuznetsov.aviaroutes.ui.search.di.SearchFragmentComponent
import com.maxkuznetsov.aviaroutes.ui.search.di.SearchFragmentModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by max
 */
@Singleton
@Component(modules = [(RepositoryModule::class), (ApiModule::class)])
interface AppComponent {
    fun plus(searchFragmentModule: SearchFragmentModule): SearchFragmentComponent
}