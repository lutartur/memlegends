package com.memlegends.game.ui.screens.game

import androidx.lifecycle.ViewModel
import com.memlegends.game.data.model.Meme
import com.memlegends.game.data.repository.MemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class GameUiState(
    val hand: List<Meme> = emptyList(),
    val selectedCard: Meme? = null,
    val score: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null,
    val dealCount: Int = 0,
)

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: MemeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        dealHand()
    }

    fun dealHand() {
        val hand = repository.dealHand(count = 6)
        _uiState.update { state ->
            state.copy(
                hand = hand,
                isLoading = false,
                selectedCard = null,
                dealCount = state.dealCount + 1,
            )
        }
    }

    fun selectCard(meme: Meme) {
        _uiState.update { state ->
            if (state.selectedCard?.id == meme.id) {
                state.copy(
                    selectedCard = null,
                    score = state.score + meme.power,
                    hand = state.hand.filter { it.id != meme.id },
                )
            } else {
                state.copy(selectedCard = meme)
            }
        }
    }

    fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }
}
