package com.quotum.quotum.quotum.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponseModel {
    @Expose
    @SerializedName("result")
    var result: Result? = null

    class Result {
        @Expose
        @SerializedName("userId")
        var userId: String? = null

        @Expose
        @SerializedName("created")
        var created: String? = null

        @Expose
        @SerializedName("ttl")
        var ttl = 0

        @Expose
        @SerializedName("id")
        var id: String? = null
    }
}