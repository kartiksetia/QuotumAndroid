package com.quotum.quotum.quotum.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetTripResponseModel {
    @Expose
    @SerializedName("userId")
    private val userId: String? = null

    @Expose
    @SerializedName("id")
    private val id: String? = null

    @Expose
    @SerializedName("details")
    private val details: List<String>? = null

    @Expose
    @SerializedName("persons")
    private val persons: Persons? = null

    @Expose
    @SerializedName("modeOfTransport")
    private val modeOfTransport: String? = null

    @Expose
    @SerializedName("budget")
    private val budget = 0

    @Expose
    @SerializedName("days")
    private val days = 0

    @Expose
    @SerializedName("created")
    private val created: String? = null

    @Expose
    @SerializedName("endDate")
    private val endDate: String? = null

    @Expose
    @SerializedName("startDate")
    private val startDate: String? = null

    @Expose
    @SerializedName("destination")
    private val destination: String? = null

    @Expose
    @SerializedName("source")
    private val source: String? = null

    @Expose
    @SerializedName("location")
    private val location: Location? = null

    class Persons {
        @Expose
        @SerializedName("children")
        private val children: String? = null

        @Expose
        @SerializedName("adult")
        private val adult = 0
    }

    class Location {
        @Expose
        @SerializedName("lng")
        private val lng = 0

        @Expose
        @SerializedName("lat")
        private val lat = 0
    }
}