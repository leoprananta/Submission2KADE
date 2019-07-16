package com.example.leonanta.submission3.mvp.second

import com.example.leonanta.submission3.model.TeamDetail

interface SecondView {

    fun showLoading()
    fun hideLoading()
    fun showNoData()
    fun showTeamDetail(dataHome: List<TeamDetail>, dataAway: List<TeamDetail>)
}
