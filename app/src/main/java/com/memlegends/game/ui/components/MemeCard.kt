package com.memlegends.game.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.memlegends.game.data.model.Meme
import kotlinx.coroutines.delay

/** Карта в сетке — всегда рубашкой вниз. */
@Composable
fun MemeCard(
    meme: Meme,
    index: Int,
    modifier: Modifier = Modifier,
    isDimmed: Boolean = false,
    enabled: Boolean = true,
    onTap: () -> Unit = {},
) {
    val shape = RoundedCornerShape(12.dp)

    val offsetY = remember { Animatable(-600f) }
    val dealAlpha = remember { Animatable(0f) }
    val rotation = remember { Animatable((-15..15).random().toFloat()) }

    LaunchedEffect(meme.id) {
        delay(index * 100L)
        dealAlpha.animateTo(1f)
        offsetY.animateTo(
            targetValue = 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium,
            ),
        )
        rotation.animateTo(
            targetValue = 0f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
        )
    }

    val dimAlpha = if (isDimmed) 0.3f else 1f

    Box(
        modifier = modifier
            .graphicsLayer {
                translationY = offsetY.value
                alpha = dealAlpha.value * dimAlpha
                rotationZ = rotation.value
                cameraDistance = 12f * density
            }
            .shadow(elevation = 12.dp, shape = shape)
            .clip(shape)
            .clickable(enabled = enabled) { onTap() },
    ) {
        CardBack()
    }
}

/** Лицевая сторона карты — используется в оверлее. */
@Composable
fun CardFront(meme: Meme, modifier: Modifier = Modifier) {
    val rarityColor = Color(meme.rarity.color)
    val shape = RoundedCornerShape(16.dp)

    Column(
        modifier = modifier
            .shadow(
                elevation = 32.dp,
                shape = shape,
                ambientColor = rarityColor.copy(alpha = 0.5f),
                spotColor = rarityColor.copy(alpha = 0.8f),
            )
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    listOf(rarityColor.copy(alpha = 0.9f), rarityColor.copy(alpha = 0.2f))
                ),
                shape = shape,
            ),
    ) {
        // Rarity banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(rarityColor.copy(alpha = 0.15f))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = meme.rarity.label.uppercase(),
                color = rarityColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
            )
        }

        // Meme image
        val painter = rememberAsyncImagePainter(model = meme.imageUrl)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.foundation.Image(
                painter = painter,
                contentDescription = meme.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
            )
            when (painter.state) {
                is AsyncImagePainter.State.Loading ->
                    CircularProgressIndicator(color = rarityColor, strokeWidth = 2.dp)
                is AsyncImagePainter.State.Error ->
                    Text(text = "😵", fontSize = 40.sp, textAlign = TextAlign.Center)
                else -> Unit
            }
        }

        // Card info
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = meme.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "⚡ ${meme.power}",
                style = MaterialTheme.typography.bodyMedium,
                color = rarityColor,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun CardBack(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.72f)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF6650A4), Color(0xFF1E1E2E))
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "MEM\nLEGENDS",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            letterSpacing = 4.sp,
        )
    }
}
