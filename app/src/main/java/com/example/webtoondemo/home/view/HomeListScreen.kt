package com.example.webtoondemo.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.webtoondemo.HomeInfoWithFavorite
import com.example.webtoondemo.R
import com.example.webtoondemo.common.extension.toast
import com.example.webtoondemo.home.model.HomeEvent
import com.example.webtoondemo.home.viewModel.HomeViewModel
import com.example.webtoondemo.home.viewModel.HomeViewModelFactory
import com.google.accompanist.insets.navigationBarsHeight

@Composable
fun HomeListScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: HomeViewModelFactory,
    position: Int,
) {
    val viewModel: HomeViewModel = viewModel(
        key = position.toString(),
        factory = HomeViewModel.provideFactory(viewModelFactory, position)
    )
    val list by viewModel.listEvent.observeAsState(null)
    val event by viewModel.event.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(event) {
        @Suppress("UnnecessaryVariable", "MoveVariableDeclarationIntoWhen")
        val safeEvent = event
        when (safeEvent) {
            is HomeEvent.ErrorEvent -> {
                context.toast(safeEvent.message)
            }
        }
    }
    HomeListScreen(
        modifier = modifier,
        items = list,
    ) { item ->
        context.toast(item.info.title)
    }
}

@Composable
fun HomeListScreen(
    modifier: Modifier = Modifier,
    items: List<HomeInfoWithFavorite>?,
    onClick: (HomeInfoWithFavorite) -> Unit
) {
    when {
        items == null -> {
            Box(
                modifier = modifier.wrapContentSize(Alignment.Center)
            ) {
                LoadingUi()
            }
        }
        items.isNotEmpty() -> {
            ListUi(
                modifier = modifier,
                items = items,
                onClicked = onClick
            )
        }
        else -> {
            Box(
                modifier = modifier.wrapContentSize(Alignment.Center)
            ) {
                EmptyUi()
            }
        }
    }
}

@Composable
fun LoadingUi(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 200.dp)
    ) {
        val (loading) = createRefs()
        CircularProgressIndicator(
            modifier = modifier
                .wrapContentSize(Alignment.Center)
                .constrainAs(loading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            color = Color(0xFF26A862)
        )
    }
}

@Composable
fun ListUi(
    modifier: Modifier = Modifier,
    items: List<HomeInfoWithFavorite>,
    onClicked: (HomeInfoWithFavorite) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(items, key = { _, item ->
            item.info.id
        }) { index, item ->
            if (index == 0) Spacer(Modifier.navigationBarsHeight(16.dp))
            HomeItemUi(
                modifier = modifier,
                item = item.info,
                onClicked = { onClicked.invoke(item) }
            )
            if (index == items.lastIndex) Spacer(Modifier.navigationBarsHeight(200.dp))
        }
    }
}

@Composable
fun EmptyUi() {
    Image(
        painterResource(R.drawable.ic_sentiment_very_dissatisfied_48),
        contentDescription = null
    )
}