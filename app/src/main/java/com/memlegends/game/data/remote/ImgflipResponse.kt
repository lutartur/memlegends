package com.memlegends.game.data.remote

import com.google.gson.annotations.SerializedName

data class MemeApiResponse(
    val count: Int,
    val memes: List<MemeApiItem>,
)

data class MemeApiItem(
    val postLink: String,
    val subreddit: String,
    val title: String,
    val url: String,
    val nsfw: Boolean,
    val spoiler: Boolean,
    val author: String,
    val ups: Int,
    val preview: List<String>,
)
