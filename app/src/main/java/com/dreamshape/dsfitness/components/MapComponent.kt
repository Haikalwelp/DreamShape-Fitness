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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient

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
    val placesClient: PlacesClient = Places.createClient(context)
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)

    val request = FindCurrentPlaceRequest.newInstance(placeFields)
    placesClient.findCurrentPlace(request).addOnSuccessListener { response ->
        for (placeLikelihood in response.placeLikelihoods) {
            val place = placeLikelihood.place
            if (place.types?.contains(Place.Type.GYM) == true) {
                place.latLng?.let {
                    googleMap.addMarker(
                        MarkerOptions()
                            .title(place.name)
                            .position(it)
                    )
                }
            }
        }
    }.addOnFailureListener { e ->
        Log.e("MapComponent", "Error finding nearby gyms: ", e)
    }
}
