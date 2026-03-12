package com.memlegends.game.ui.screens.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.geometry.Rect
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.memlegends.game.ui.components.CardFront
import com.memlegends.game.ui.components.MemeCard
import com.memlegends.game.ui.theme.TableBackground

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var selectedCardId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(uiState.dealCount) { selectedCardId = null }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.dismissError()
        }
    }

    val selectedMeme = uiState.hand.find { it.id == selectedCardId }
    val cardKey = { id: String -> "card-${id}-${uiState.dealCount}" }
    val sharedBoundsSpec = { _: Rect, _: Rect ->
        spring<Rect>(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow,
        )
    }

    SharedTransitionLayout {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                            TableBackground,
                        )
                    )
                ),
        ) {
            // ── Main content ──────────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Hand ${uiState.hand.size}/6",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "Score: ${uiState.score}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }

                Spacer(Modifier.height(8.dp))

                AnimatedVisibility(
                    visible = uiState.hand.isNotEmpty() && !uiState.isLoading,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.weight(1f),
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(horizontal = 44.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        itemsIndexed(
                            items = uiState.hand,
                            key = { _, meme -> "${uiState.dealCount}_${meme.id}" },
                        ) { index, meme ->
                            val isSelected = selectedCardId == meme.id
                            AnimatedVisibility(
                                visible = !isSelected,
                                enter = EnterTransition.None,
                                exit = ExitTransition.None,
                            ) {
                                MemeCard(
                                    meme = meme,
                                    index = index,
                                    modifier = Modifier.sharedBounds(
                                        sharedContentState = rememberSharedContentState(cardKey(meme.id)),
                                        animatedVisibilityScope = this,
                                        boundsTransform = sharedBoundsSpec,
                                    ),
                                    isDimmed = selectedCardId != null,
                                    enabled = selectedCardId == null,
                                    onTap = { selectedCardId = meme.id },
                                )
                            }
                        }
                    }
                }

                if (uiState.isLoading) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                Button(
                    onClick = { viewModel.dealHand() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .height(52.dp),
                    enabled = !uiState.isLoading && selectedCardId == null,
                ) {
                    Icon(Icons.Rounded.Refresh, contentDescription = null)
                    Text(text = "  Deal New Hand", style = MaterialTheme.typography.titleMedium)
                }
            }

            // ── Card overlay ──────────────────────────────────────────────
            AnimatedVisibility(
                visible = selectedMeme != null,
                enter = fadeIn(tween(durationMillis = 320)),
                exit = fadeOut(tween(durationMillis = 280)),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.75f))
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ) { selectedCardId = null },
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ) { },
                    ) {
                        if (selectedMeme != null) {
                            CardFront(
                                meme = selectedMeme,
                                modifier = Modifier
                                    .fillMaxWidth(0.72f)
                                    .sharedBounds(
                                        sharedContentState = rememberSharedContentState(cardKey(selectedMeme.id)),
                                        animatedVisibilityScope = this@AnimatedVisibility,
                                        boundsTransform = sharedBoundsSpec,
                                    ),
                            )
                        }

                        Spacer(Modifier.height(28.dp))

                        Button(
                            onClick = { viewModel.dealHand() },
                            modifier = Modifier
                                .fillMaxWidth(0.72f)
                                .height(52.dp),
                        ) {
                            Icon(Icons.Rounded.Refresh, contentDescription = null)
                            Text(text = "  Deal New Hand", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) { data -> Snackbar(snackbarData = data) }
        }
    }
}
