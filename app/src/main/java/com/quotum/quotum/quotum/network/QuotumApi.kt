package com.quotum.quotum.quotum.network

import com.quotum.quotum.quotum.models.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface QuotumApi {
    @POST("/api/users/emailLogin/")
    fun emailLogin(@Body login: LoginRequestModel): Call<LoginResponseModel>

    @POST("/auth/facebook")
    fun facebookLogin(@Query("code") authCode: String): Call<FacebookResponseModel>

    @POST("/api/users/socialLogin")
    fun socialLogin(@Query("access_token") authCode: String): Call<FacebookResponseModel>


    @POST("/api/customerQueries")
    fun postCustomerQuery(@Body model: CustomerQueryRequestModel): Call<JsonObject>

    @POST("/api/trips/acceptTrip")
    fun postAccessTrip(@Query("access_token") accessToken: String): Call<JsonObject>

    @POST("/api/users/emailSignUp")
    fun postEmailSignUp(@Body model: SignUpRequestModel): Call<JsonObject>

    @POST("/api/users/{userId}/trips")
    fun postUserTrip(
        @Path("userId") id: String,
        @Query("access_token") accessToken: String
    ): Call<PostTripResponseModel>

    @GET("/api/trips/{tripId}/trips")
    fun getTrip(
        @Path("tripId") id: String,
        @Query("access_token") accessToken: String
    ): Call<GetTripResponseModel>

    @GET("/api/trips/getTrips")
    fun getTripByLocation(
        @Query("lat") lat: String,
        @Query("lng") lng: String,
        @Query("distance") distance: String
    ): Call<GetTripLocationResponseModel>

    @GET("/api/users/{id}")
    fun getUserProfile(
        @Path("id") id: String,
        @Query("access_token") accessToken: String
    ): Call<UserProfileResponseModel>
}