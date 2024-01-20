package com.dreamshape.dsfitness.Data

import com.squareup.moshi.Json

data class NearbySearchResponse(
    @Json(name = "results") val results: List<PlaceResult>
)

data class PlaceResult(
    @Json(name = "geometry") val geometry: Geometry,
    @Json(name = "name") val name: String
)

data class Geometry(
    @Json(name = "location") val location: Location
)

data class Location(
    @Json(name = "lat") val lat: Double,
    @Json(name = "lng") val lng: Double
)
