package com.maxkuznetsov.aviaroutes.ui.search.di

import com.maxkuznetsov.aviaroutes.ui.search.SearchFragment
import dagger.Subcomponent

/**
 * Created by max
 */
@Subcomponent(modules = [(SearchFragmentModule::class)])
interface SearchFragmentComponent {
    fun inject(fragment: SearchFragment)
}