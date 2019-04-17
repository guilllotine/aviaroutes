package com.maxkuznetsov.aviaroutes.util

import com.maxkuznetsov.aviaroutes.ui.base.view.LoadingView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by max
 */
fun <T> Flowable<T>.async(): Flowable<T> {
    return observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}

fun <T> Flowable<T>.loading(view: LoadingView?): Flowable<T> {
    return doOnSubscribe { view?.showLoadingIndicator() }
        .doFinally { view?.hideLoadingIndicator() }
}