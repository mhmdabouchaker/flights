package com.mac.flights.extensions

import android.app.Activity
import android.app.ActivityManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.mac.flights.R

fun Activity.applyTaskDescription() {
    val appName = getString(R.string.app_name)

    val description = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val color = ContextCompat.getColor(this, R.color.black)

        ActivityManager.TaskDescription(appName, R.mipmap.ic_app_launcher, color)
    } else {
        ActivityManager.TaskDescription(appName)
    }

    this.setTaskDescription(description)
}