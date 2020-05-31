package com.quotum.quotum.quotum.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AccessTripRequestModel {
    @Expose
    @SerializedName("requestId")
    private val requestId: String? = null

    @Expose
    @SerializedName("clientId")
    private val clientId: String? = null
}