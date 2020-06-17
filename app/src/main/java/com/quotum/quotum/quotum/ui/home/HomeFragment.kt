package com.quotum.quotum.quotum.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.quotum.quotum.quotum.R
import com.quotum.quotum.quotum.TripAdapter
import com.quotum.quotum.quotum.models.GetTripLocationResponseModel
import com.quotum.quotum.quotum.network.QuotumClient
import com.quotum.quotum.quotum.utility.PermissionUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.rvTrip)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())
        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = TripAdapter()

        getNearByTrips()
        return root
    }

    private fun getNearByTrips(){
        if (PermissionUtils.checkLocationPermissions(this.requireContext())){
            obtieneLocalizacion()
        }else{
            requestLpcationPermissions()
        }

    }

    private fun getTrips(){
        QuotumClient.instance.getTripByLocation(latitude,longitude,10)
            .enqueue(object : Callback<GetTripLocationResponseModel>{
                override fun onFailure(call: Call<GetTripLocationResponseModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<GetTripLocationResponseModel>, response: Response<GetTripLocationResponseModel>) {
                    if(response.isSuccessful){
                        val tripLocationResponseModel : GetTripLocationResponseModel = response.body()!!
                    }
                }
            })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 42) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                obtieneLocalizacion()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun obtieneLocalizacion(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                latitude = location?.latitude!!
                longitude = location.longitude
                getTrips()
            }
    }

    fun requestLpcationPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            42
        )
    }

}