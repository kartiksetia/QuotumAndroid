package com.quotum.quotum.quotum.models

import com.google.gson.annotations.SerializedName

data class SocialLoginResponseModel(
    @SerializedName("result") val result : Result
)
data class Result (

    @SerializedName("id") val id : String,
    @SerializedName("ttl") val ttl : Int,
    @SerializedName("created") val created : String,
    @SerializedName("userId") val userId : String
)