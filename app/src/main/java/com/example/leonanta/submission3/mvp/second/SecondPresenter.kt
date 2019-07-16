package com.example.leonanta.submission3.mvp.second

import com.google.gson.Gson
import com.example.leonanta.submission3.model.TeamDetailResponse
import com.example.leonanta.submission3.api.APIRepository
import com.example.leonanta.submission3.api.TheSportsDBAPI
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SecondPresenter(private val view: SecondView,
                      private val APIRepository: APIRepository,
                      private val gson: Gson) {

    fun getTeamDetail(homeTeam: String, awayTeam: String) {
        view.showLoading()
        doAsync {
            val dataHomeTeam = gson.fromJson(APIRepository
                    .doRequest(TheSportsDBAPI.getTeamDetail(homeTeam)),
                    TeamDetailResponse::class.java
            )
            val dataAwayTeam = gson.fromJson(APIRepository
                    .doRequest(TheSportsDBAPI.getTeamDetail(awayTeam)),
                    TeamDetailResponse::class.java
            )
            uiThread {
                try {
                    view.hideLoading()
                    view.showTeamDetail(dataHomeTeam.teams!!, dataAwayTeam.teams!!)
                } catch (e: NullPointerException) {
                    view.showNoData()
                }
            }
        }
    }
}
