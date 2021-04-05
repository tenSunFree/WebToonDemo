package com.example.webtoondemo.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.webtoondemo.*
import com.example.webtoondemo.home.viewModel.HomeViewModelFactory
import com.google.accompanist.glide.GlideImage
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: HomeViewModelFactory
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        val (image, list) = createRefs()
        GlideImage(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
                .aspectRatio(1440 / 776F),
            data = R.drawable.icon_home_top_bar,
            contentScale = ContentScale.FillHeight,
            contentDescription = null
        )
        HomeListScreen(
            modifier = Modifier.constrainAs(list) {
                top.linkTo(image.bottom)
            },
            viewModelFactory = viewModelFactory,
            position = 1
        )
    }
}