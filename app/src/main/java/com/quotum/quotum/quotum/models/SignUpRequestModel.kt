package com.quotum.quotum.quotum.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUpRequestModel {
    @Expose
    @SerializedName("deviceType")
    private val deviceType: String? = null

    @Expose
    @SerializedName("deviceToken")
    private val deviceToken: String? = null

    @Expose
    @SerializedName("userData")
    private val userData: UserData? = null

    class UserData {
        @Expose
        @SerializedName("password")
        private val password: String? = null

        @Expose
        @SerializedName("lastName")
        private val lastName: String? = null

        @Expose
        @SerializedName("firstName")
        private val firstName: String? = null

        @Expose
        @SerializedName("mobileNumber")
        private val mobileNumber: String? = null

        @Expose
        @SerializedName("email")
        private val email: String? = null
    }
}