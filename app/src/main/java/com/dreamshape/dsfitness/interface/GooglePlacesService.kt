package com.dreamshape.dsfitness

import com.dreamshape.dsfitness.Data.NearbySearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesService {
    @GET("maps/api/place/nearbysearch/json")
    suspend fun searchNearbyGyms(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("keyword") keyword: String,
        @Query("key") apiKey: String
    ): NearbySearchResponse
}