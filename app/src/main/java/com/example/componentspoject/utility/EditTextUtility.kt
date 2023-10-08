package com.example.componentspoject.utility

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


object EditTextUtility {
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}