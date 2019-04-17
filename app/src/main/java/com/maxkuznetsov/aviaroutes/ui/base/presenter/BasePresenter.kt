package com.maxkuznetsov.aviaroutes.ui.base.presenter

/**
 * Created by max
 */
interface BasePresenter<V> {
    fun bindView(view: V)
    fun unbindView(view: V)
}