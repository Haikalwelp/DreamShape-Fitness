package com.dreamshape.dsfitness.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.dreamshape.dsfitness.GooglePlacesService
import com.dreamshape.dsfitness.instances.RetrofitInstance
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapComponent() {
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(key1 = true) {
        locationPermissionState.launchPermissionRequest()
    }

    AndroidView(
        factory = { ctx ->
            MapView(ctx).apply {
                onCreate(null)
                getMapAsync { googleMap ->
                    if (locationPermissionState.status.isGranted) {
                        setUpGoogleMap(googleMap, ctx)
                    }
                }
            }
        },
        update = { mapView ->
            mapView.onResume()
        }
    )
}

@SuppressLint("MissingPermission")
private fun setUpGoogleMap(googleMap: GoogleMap, context: Context) {
    googleMap.isMyLocationEnabled = true

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        location?.let {
            val userLocation = LatLng(it.latitude, it.longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 14f))
            fetchNearbyGyms(googleMap, userLocation, context)
        }
    }
}

@SuppressLint("MissingPermission")
private fun fetchNearbyGyms(googleMap: GoogleMap, location: LatLng, context: Context) {
    // Create an instance of the Retrofit service
    val service = RetrofitInstance.retrofit.create(GooglePlacesService::class.java)

    // Make the network request
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = service.searchNearbyGyms(
                location = "${location.latitude},${location.longitude}",
                radius = 1500, // Adjust radius as needed
                type = "gym", // "type" is deprecated, use "keyword" instead
                keyword = "gym",
                apiKey = "AIzaSyCg_Xb_B0uUD6hTngK0UsdR37CGSfjf7oI"
            )
            withContext(Dispatchers.Main) {
                response.results.forEach { placeResult ->
                    googleMap.addMarker(
                        MarkerOptions()
                            .title(placeResult.name)
                            .position(LatLng(placeResult.geometry.location.lat, placeResult.geometry.location.lng))
                    )
                }
                val boundsBuilder = LatLngBounds.Builder()
                response.results.forEach { placeResult ->
                    boundsBuilder.include(LatLng(placeResult.geometry.location.lat, placeResult.geometry.location.lng))
                }
                val bounds = boundsBuilder.build()
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
            }
        } catch (e: Exception) {
            Log.e("MapComponent", "Error fetching nearby gyms: ${e.message}")
        }
    }
}
