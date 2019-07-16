package com.example.leonanta.submission3.mvp.main

import com.example.leonanta.submission3.model.MatchEvent

interface MainView {

    fun showLoading()
    fun hideLoading()
    fun showNoData()
    fun showLastMatch(data: List<MatchEvent>)
    fun showNextMatch(data: List<MatchEvent>)
}
