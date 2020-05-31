package com.quotum.quotum.quotum.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CustomerQueryRequestModel {
    @Expose
    @SerializedName("contactNumber")
    private val contactNumber: String? = null

    @Expose
    @SerializedName("email")
    private val email: String? = null

    @Expose
    @SerializedName("name")
    private val name: String? = null
}