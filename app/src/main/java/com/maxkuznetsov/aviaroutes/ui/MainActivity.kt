package com.maxkuznetsov.aviaroutes.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.maxkuznetsov.aviaroutes.R
import com.maxkuznetsov.aviaroutes.data.model.RouteCoordinates
import com.maxkuznetsov.aviaroutes.ui.base.NavigationListener
import com.maxkuznetsov.aviaroutes.ui.routeloading.RouteLoadingFragment
import com.maxkuznetsov.aviaroutes.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            replaceFragment(SearchFragment.create())
        }
    }

    override fun navigateToRouteLoading(routeCoordinates: RouteCoordinates) {
        replaceFragment(RouteLoadingFragment.create(routeCoordinates), true)
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, fragment::class.java.name)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}
