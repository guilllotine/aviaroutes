package com.maxkuznetsov.aviaroutes.ui.routeloading

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.maxkuznetsov.aviaroutes.R
import com.maxkuznetsov.aviaroutes.data.model.RouteCoordinates
import kotlinx.android.synthetic.main.fragment_route_loading.routingOverlay
import kotlinx.android.synthetic.main.fragment_route_loading.mapview as mapView


/**
 * Created by max
 */

const val ROUTE_COORDINATES_KEY = "key_route_coordinates"

class RouteLoadingFragment : Fragment(), OnMapReadyCallback {

    private lateinit var routeCoordinates: RouteCoordinates

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_route_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        routeCoordinates = arguments?.getParcelable(ROUTE_COORDINATES_KEY) as RouteCoordinates
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val builder = LatLngBounds.Builder()
        val from = LatLng(routeCoordinates.fromLocation.lat, routeCoordinates.fromLocation.lng)
        val to = LatLng(routeCoordinates.toLocation.lat, routeCoordinates.toLocation.lng)
        builder.include(from)
        builder.include(to)
        val fromMarker = googleMap.addMarker(MarkerOptions().position(from))
        val toMarker = googleMap.addMarker(MarkerOptions().position(to))

        val bounds = builder.build()
        val padding = resources.getDimensionPixelSize(R.dimen.route_map_padding)
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        mapView.post {
            googleMap.moveCamera(cu)
            val projection = googleMap.projection
            val screenFromPosition = projection.toScreenLocation(fromMarker.position)
            val screenToPosition = projection.toScreenLocation(toMarker.position)
            routingOverlay.setPoints(screenFromPosition, screenToPosition)
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        mapView.onDestroy()
        super.onDestroyView()
    }

    companion object {
        fun create(routeCoordinates: RouteCoordinates): RouteLoadingFragment {
            val bundle = Bundle()
            bundle.putParcelable(ROUTE_COORDINATES_KEY, routeCoordinates)
            val routeLoadingFragment = RouteLoadingFragment()
            routeLoadingFragment.arguments = bundle
            return routeLoadingFragment
        }
    }

}