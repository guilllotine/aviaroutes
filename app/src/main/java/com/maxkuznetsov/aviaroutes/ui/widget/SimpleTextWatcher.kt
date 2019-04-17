package com.maxkuznetsov.aviaroutes.ui.widget

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by max
 */
interface SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        //not implemented
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //not implemented
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //not implemented
    }
}