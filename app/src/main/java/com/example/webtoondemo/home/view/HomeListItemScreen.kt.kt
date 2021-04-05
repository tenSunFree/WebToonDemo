package com.example.webtoondemo.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.webtoondemo.HomeInfo
import com.example.webtoondemo.R
import com.example.webtoondemo.common.extension.toAgentGlideUrl
import com.google.accompanist.glide.GlideImage

@Composable
fun HomeItemUi(
    modifier: Modifier = Modifier,
    item: HomeInfo,
    onClicked: (HomeInfo) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .clickable { onClicked(item) }
            .height(120.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
        ) {
            HomeItemOverlayUi(
                item = item
            )
        }
    }
}

@Composable
fun HomeItemOverlayUi(
    modifier: Modifier = Modifier,
    item: HomeInfo
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (image, title, writer) = createRefs()
        Text(
            text = item.title,
            maxLines = 1,
            color = Color(0xFFBF9D7A),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .wrapContentWidth(align = Alignment.Start)
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    bottom.linkTo(writer.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = item.writer,
            maxLines = 1,
            color = Color(0xFF80ADD7),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .wrapContentWidth(align = Alignment.Start)
                .constrainAs(writer) {
                    width = Dimension.fillToConstraints
                    top.linkTo(title.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                }
        )
        Card(modifier = Modifier
            .constrainAs(image) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .aspectRatio(1F)) {
            GlideImage(
                data = item.image.toAgentGlideUrl(),
                fadeIn = true,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colors.secondary
                        )
                    }
                },
                error = {
                    Box(Modifier.fillMaxSize()) {
                        Image(
                            modifier = Modifier.align(Alignment.Center),
                            painter = painterResource(R.drawable.ic_sentiment_very_dissatisfied_48),
                            contentDescription = null
                        )
                    }
                },
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}