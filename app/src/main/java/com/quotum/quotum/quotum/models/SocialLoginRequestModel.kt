package com.quotum.quotum.quotum.models

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class SocialLoginRequestModel(

    @SerializedName("data") val data : JSONObject,
    @SerializedName("type") val type : String
)