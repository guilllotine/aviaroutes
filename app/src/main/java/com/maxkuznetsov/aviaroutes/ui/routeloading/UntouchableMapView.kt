package com.maxkuznetsov.aviaroutes.ui.routeloading

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.google.android.gms.maps.MapView

/**
 * Created by max
 */
class UntouchableMapView(context: Context, attributeSet: AttributeSet) : MapView(context, attributeSet) {
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }
}