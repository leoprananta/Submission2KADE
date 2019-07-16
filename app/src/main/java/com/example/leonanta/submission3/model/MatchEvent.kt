package com.example.leonanta.submission3.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchEvent(

        @SerializedName("idEvent")
        var idEvent: String? = "",

        @SerializedName("dateEvent")
        var dateEvent: String? = "",

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = "",

        @SerializedName("intHomeScore")
        var intHomeScore: String? = "",

        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = "",

        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = "",

        @SerializedName("intHomeShots")
        var intHomeShots: String? = "",

        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = "",

        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = "",

        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String? = "",

        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = "",

        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = "",


        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = "",

        @SerializedName("intAwayScore")
        var intAwayScore: String? = "",

        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = "",

        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = "",

        @SerializedName("intAwayShots")
        var intAwayShots: String? = "",

        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = "",

        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = "",

        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String? = "",

        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = "",

        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = ""

) : Parcelable
