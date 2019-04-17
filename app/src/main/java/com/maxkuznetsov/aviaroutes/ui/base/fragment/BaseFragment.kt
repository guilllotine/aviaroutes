package com.maxkuznetsov.aviaroutes.ui.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by max
 */
abstract class BaseFragment: Fragment() {

    protected abstract fun bindView()

    protected abstract fun unbindView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
    }

    override fun onDestroyView() {
        unbindView()
        super.onDestroyView()
    }

}