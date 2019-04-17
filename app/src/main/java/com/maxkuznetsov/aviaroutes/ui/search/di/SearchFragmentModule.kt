package com.maxkuznetsov.aviaroutes.ui.search.di

import com.maxkuznetsov.aviaroutes.data.repository.Repository
import com.maxkuznetsov.aviaroutes.ui.search.SearchContract
import com.maxkuznetsov.aviaroutes.ui.search.SearchPresenter
import dagger.Module
import dagger.Provides
import java.util.*

/**
 * Created by max
 */
@Module
class SearchFragmentModule {

    @Provides
    fun providePresenter(
        repository: Repository
    ): SearchContract.Presenter = SearchPresenter(repository, Locale.getDefault().isO3Language)

}