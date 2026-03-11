package com.memlegends.game.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Meme(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rarity: MemeRarity = MemeRarity.COMMON,
    val power: Int = (10..100).random(),
)

enum class MemeRarity(val label: String, val color: Long) {
    COMMON("Common", 0xFF9E9E9E),
    UNCOMMON("Uncommon", 0xFF4CAF50),
    RARE("Rare", 0xFF2196F3),
    EPIC("Epic", 0xFF9C27B0),
    LEGENDARY("Legendary", 0xFFFF9800),
}
