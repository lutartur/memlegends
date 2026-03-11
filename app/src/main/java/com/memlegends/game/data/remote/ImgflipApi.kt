package com.memlegends.game.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MemeApiService {
    @GET("gimme/{count}")
    suspend fun getMemes(@Path("count") count: Int = 50): MemeApiResponse
}
