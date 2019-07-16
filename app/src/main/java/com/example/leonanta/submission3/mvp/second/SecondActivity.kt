package com.example.leonanta.submission3.mvp.second

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.example.leonanta.submission3.R
import com.example.leonanta.submission3.R.id.*
import com.example.leonanta.submission3.api.APIRepository
import com.example.leonanta.submission3.model.MatchEvent
import com.example.leonanta.submission3.model.TeamDetail
import com.example.leonanta.submission3.utils.Date
import com.example.leonanta.submission3.utils.Invisible
import com.example.leonanta.submission3.utils.Visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.toast

const val DETAIL = "DETAIL"

class SecondActivity : AppCompatActivity(), SecondView {

    private lateinit var presenter: SecondPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var scrollView: ScrollView
    private lateinit var homeBadge: ImageView
    private lateinit var awayBadge: ImageView

    override fun showLoading() {
        progressBar.Visible()
    }

    override fun hideLoading() {
        progressBar.Invisible()
    }

    override fun showNoData() {
        toast("No Data Available, Cek Your Code")
    }

    override fun showTeamDetail(dataHome: List<TeamDetail>, dataAway: List<TeamDetail>) {
        Picasso.get()
                .load(dataHome[0].strTeamBadge)
                .resize(150, 150)
                .into(homeBadge)

        Picasso.get()
                .load(dataAway[0].strTeamBadge)
                .resize(150, 150)
                .into(awayBadge)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.title = "Match Detail"
        val item = intent.getParcelableExtra<MatchEvent>(DETAIL)
        mainLayout(item)
        generateData(item)
    }

    private fun mainLayout(item: MatchEvent) {
        var date = findViewById<TextView>(R.id.second_date)
        homeBadge = findViewById(second_homeBadge)
        awayBadge = findViewById(second_awayBadge)
        var homeTeam = findViewById<TextView>(second_homeTeam)
        var awayTeam = findViewById<TextView>(second_awayTeam)
        var homeScore = findViewById<TextView>(second_homeScore)
        var awayScore = findViewById<TextView>(second_awayScore)
        var homeGoal = findViewById<TextView>(second_homeGoal)
        var awayGoal = findViewById<TextView>(second_awayGoal)
        var homeShot = findViewById<TextView>(second_homeShot)
        var awayShot = findViewById<TextView>(second_awayShot)
        var homeGoalkeeper = findViewById<TextView>(second_homeGoalkeeper)
        var awayGoalkeeper = findViewById<TextView>(second_awayGoalkeeper)
        var homeDefense = findViewById<TextView>(second_homeDefense)
        var awayDefense = findViewById<TextView>(second_awayDefense)
        var homeMidfield = findViewById<TextView>(second_homeMidfield)
        var awayMidfield = findViewById<TextView>(second_awayMidfield)
        var homeForward = findViewById<TextView>(second_homeForward)
        var awayForward = findViewById<TextView>(second_awayForward)
        var homeSubstitutes = findViewById<TextView>(second_homeSubstitutes)
        var awaySubstitutes = findViewById<TextView>(second_awaySubstitutes)
        scrollView = findViewById(second_scrollView)
        progressBar = findViewById(second_progBar)

        date.text = Date.getDate(item.dateEvent!!)
        homeTeam.text = item.strHomeTeam
        awayTeam.text = item.strAwayTeam
        homeScore.text = item.intHomeScore
        awayScore.text = item.intAwayScore
        homeGoal.text = item.strHomeGoalDetails
        awayGoal.text = item.strAwayGoalDetails
        homeShot.text = item.intHomeShots
        awayShot.text = item.intAwayShots
        homeGoalkeeper.text = item.strHomeLineupGoalkeeper
        awayGoalkeeper.text = item.strAwayLineupGoalkeeper
        homeDefense.text = item.strHomeLineupDefense
        awayDefense.text = item.strAwayLineupDefense
        homeMidfield.text = item.strHomeLineupMidfield
        awayMidfield.text = item.strAwayLineupMidfield
        homeForward.text = item.strHomeLineupForward
        awayForward.text = item.strAwayLineupForward
        homeSubstitutes.text = item.strHomeLineupSubstitutes
        awaySubstitutes.text = item.strAwayLineupSubstitutes
    }

    private fun generateData(item: MatchEvent) {
        val request = APIRepository()
        val gson = Gson()
        presenter = SecondPresenter(this, request, gson)
        presenter.getTeamDetail(item.idHomeTeam!!, item.idAwayTeam!!)
    }
}
