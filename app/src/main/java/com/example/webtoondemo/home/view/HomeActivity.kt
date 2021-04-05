package com.example.webtoondemo.home.view

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.example.webtoondemo.*
import com.example.webtoondemo.common.extension.toast
import com.example.webtoondemo.home.model.HomeConst
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : FragmentActivity() {

    private val viewId by lazy { View.generateViewId() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getStringExtra("title")?.apply { toast(this) }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        activityComposeView {
            ProvideWindowInsets(false) {
                val contentView = remember {
                    FragmentContainerView(this).apply {
                        id = viewId
                        replaceContainer(HomeFragment.newInstance())
                    }
                }
                AndroidView(
                    modifier = Modifier,
                    factory = { contentView }
                )
            }
        }
    }

    private fun replaceContainer(fragment: Fragment) {
        supportFragmentManager.commit {
            supportFragmentManager.findFragmentByTag(HomeConst.HOME_FRAGMENT_TAG)?.let {
                remove(it)
            }
            replace(viewId, fragment, HomeConst.HOME_FRAGMENT_TAG)
        }
    }
}
