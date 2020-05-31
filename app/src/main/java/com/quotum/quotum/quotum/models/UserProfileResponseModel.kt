package com.quotum.quotum.quotum.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserProfileResponseModel {
    @Expose
    @SerializedName("profileId")
    private val profileId: String? = null

    @Expose
    @SerializedName("lastName")
    private val lastName: String? = null

    @Expose
    @SerializedName("firstName")
    private val firstName: String? = null

    @Expose
    @SerializedName("id")
    private val id: String? = null

    @Expose
    @SerializedName("email")
    private val email: String? = null

    @Expose
    @SerializedName("username")
    private val username: String? = null

    @Expose
    @SerializedName("created")
    private val created: String? = null
}