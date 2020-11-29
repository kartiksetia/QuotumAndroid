package com.quotum.quotum.quotum.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class GetTripLocationResponseModel {

    @SerializedName("result")
    @Expose
    private var result: List<Result?>? = null

    fun getResult(): List<Result?>? {
        return result
    }

    fun setResult(result: List<Result?>?) {
        this.result = result
    }

    class Result {
        @SerializedName("location")
        @Expose
        private var location: Location? = null

        @SerializedName("source")
        @Expose
        private var source: String? = null

        @SerializedName("destination")
        @Expose
        private var destination: String? = null

        @SerializedName("startDate")
        @Expose
        private var startDate: String? = null

        @SerializedName("endDate")
        @Expose
        private var endDate: String? = null

        @SerializedName("created")
        @Expose
        private var created: String? = null

        @SerializedName("days")
        @Expose
        private var days: Int? = null

        @SerializedName("budget")
        @Expose
        private var budget: Int? = null

        @SerializedName("modeOfTransport")
        @Expose
        private var modeOfTransport: String? = null

        @SerializedName("persons")
        @Expose
        private var persons: Persons? = null

        @SerializedName("details")
        @Expose
        private var details: List<String?>? = null

        @SerializedName("userId")
        @Expose
        private var userId: String? = null

        @SerializedName("currency")
        @Expose
        private var currency: String? = null

        @SerializedName("id")
        @Expose
        private var id: String? = null

        @SerializedName("pictures")
        @Expose
        private var picture: List<String>? = null

        fun getLocation(): Location? {
            return location
        }

        fun setLocation(location: Location?) {
            this.location = location
        }


        fun getPicture():  List<String>? {
            return picture
        }

        fun setPicture(picture:  List<String>?) {
            this.picture = picture
        }

        fun getCurrency(): String? {
            return currency
        }

        fun setCurrency(currency: String?) {
            this.currency = currency
        }

        fun getSource(): String? {
            return source
        }

        fun setSource(source: String?) {
            this.source = source
        }

        fun getDestination(): String? {
            return destination
        }

        fun setDestination(destination: String?) {
            this.destination = destination
        }

        fun getStartDate(): String? {
            return startDate
        }

        fun setStartDate(startDate: String?) {
            this.startDate = startDate
        }

        fun getEndDate(): String? {
            return endDate
        }

        fun setEndDate(endDate: String?) {
            this.endDate = endDate
        }

        fun getCreated(): String? {
            return created
        }

        fun setCreated(created: String?) {
            this.created = created
        }

        fun getDays(): Int? {
            return days
        }

        fun setDays(days: Int?) {
            this.days = days
        }

        fun getBudget(): Int? {
            return budget
        }

        fun setBudget(budget: Int?) {
            this.budget = budget
        }

        fun getModeOfTransport(): String? {
            return modeOfTransport
        }

        fun setModeOfTransport(modeOfTransport: String?) {
            this.modeOfTransport = modeOfTransport
        }

        fun getPersons(): Persons? {
            return persons
        }

        fun setPersons(persons: Persons?) {
            this.persons = persons
        }

        fun getDetails(): List<String?>? {
            return details
        }

        fun setDetails(details: List<String?>?) {
            this.details = details
        }

        fun getUserId(): String? {
            return userId
        }

        fun setUserId(userId: String?) {
            this.userId = userId
        }

        fun getId(): String? {
            return id
        }

        fun setId(id: String?) {
            this.id = id
        }
    }

    class Location {
        @SerializedName("lat")
        @Expose
        var lat: Float? = null

        @SerializedName("lng")
        @Expose
        var lng: Float? = null

    }

    class Persons {
        @SerializedName("adult")
        @Expose
        var adult: Int? = null

        @SerializedName("children")
        @Expose
        var children: Int? = null

    }
}