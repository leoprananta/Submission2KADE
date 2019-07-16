package com.example.leonanta.submission3.mvp.main

import com.google.gson.Gson
import com.example.leonanta.submission3.model.MatchEventResponse
import com.example.leonanta.submission3.api.APIRepository
import com.example.leonanta.submission3.api.TheSportsDBAPI
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainActivity,
                    private val APIRepository: APIRepository,
                    private val gson: Gson) {

    var idChoice = 0

    fun getLastMatch(id: String) {
        view.showLoading()
        idChoice = 0
        doAsync {
            val data = gson.fromJson(APIRepository
                    .doRequest(TheSportsDBAPI.getLastMatch(id)),
                    MatchEventResponse::class.java
            )
            uiThread {
                view.hideLoading()
                try {
                    view.showLastMatch(data.events!!)
                } catch (e: NullPointerException) {
                    view.showNoData()
                }
            }
        }
    }

    fun getNextMatch(id: String) {
        view.showLoading()
        idChoice = 1
        doAsync {
            val data = gson.fromJson(APIRepository
                    .doRequest(TheSportsDBAPI.getNextMatch(id)),
                    MatchEventResponse::class.java
            )
            uiThread {
                view.hideLoading()
                try {
                    view.showLastMatch(data.events!!)
                } catch (e: NullPointerException) {
                    view.showNoData()
                }
            }
        }
    }
}
