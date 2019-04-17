package com.maxkuznetsov.aviaroutes.ui.base.presenter

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by max
 */
open class BasePresenterImpl<V>: BasePresenter<V> {

    private val compositeDisposable = CompositeDisposable()

    private var view: V? = null

    @CallSuper
    override fun bindView(view: V) {
        val previousView = this.view

        if (previousView != null) {
            throw IllegalStateException("Previous view is not unbounded! previousView = $previousView")
        }

        this.view = view
    }

    protected fun view(): V? {
        return view
    }

    protected fun disposeOnUnbindView(vararg disposables: Disposable?) {
        disposables.forEach { it?.let { compositeDisposable.add(it) } }
    }

    protected fun removeDisposable(disposable: Disposable?) {
        if (disposable != null) {
            compositeDisposable.remove(disposable)
        }
    }

    protected fun clearDisposable() {
        compositeDisposable.clear()
    }

    @CallSuper
    override fun unbindView(view: V) {
        val previousView = this.view

        if (previousView === view) {
            this.view = null
        } else {
            throw IllegalStateException("Unexpected view! previousView = $previousView, view to unbind = $view")
        }

        compositeDisposable.clear()
    }
}