package com.example.webtoondemo

import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

@Suppress("NOTHING_TO_INLINE")
@Composable
inline fun WebToonSetup(noinline content: @Composable () -> Unit) {
    MaterialTheme() {
        content()
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ComponentActivity.activityComposeView(
    noinline content: @Composable () -> Unit
) {
    setContent {
        WebToonSetup {
            content()
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun Fragment.fragmentComposeView(
    parent: CompositionContext? = null,
    noinline content: @Composable () -> Unit
) = ComposeView(requireContext()).apply {
    setParentCompositionContext(parent)
    layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    setContent {
        WebToonSetup {
            content()
        }
    }
}
