package com.example.webtoondemo.common.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.hardware.display.DisplayManagerCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

inline fun <reified T : Activity> Context.buildIntent(
    vararg extra: Pair<String, Any?>
) = Intent(this, T::class.java).apply {
    putExtras(bundleOf(*extra))
}

inline fun <reified T : Activity> Context.startActivity(
    vararg extra: Pair<String, Any?>
) {
    startActivity(buildIntent<T>(*extra))
}

fun Context.getCompatColor(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(resId), duration)
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(resId), duration)
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun Context.screenWidth() = screen().first

fun Context.screenHeight() = screen().second

fun Context.screen(): Pair<Int, Int> {
    return DisplayManagerCompat.getInstance(this)
        .displays.firstOrNull()
        ?.let {
            val size = Point()
            it.getRealSize(size)
            size.x to size.y
        } ?: 0 to 0
}