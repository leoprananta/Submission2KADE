package com.example.leonanta.submission3.mvp.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.leonanta.submission3.R
import com.example.leonanta.submission3.R.array.leagueName
import com.example.leonanta.submission3.R.id.*
import com.example.leonanta.submission3.api.APIRepository
import com.example.leonanta.submission3.model.MatchEvent
import com.example.leonanta.submission3.mvp.second.DETAIL
import com.example.leonanta.submission3.mvp.second.SecondActivity
import com.example.leonanta.submission3.utils.Invisible
import com.example.leonanta.submission3.utils.Visible
import com.google.gson.Gson
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainView {

    private var matchEvent: MutableList<MatchEvent> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private lateinit var presenter: MainPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    lateinit var leagueId: String

    override fun showLoading() {
        progressBar.Visible()
    }

    override fun hideLoading() {
        progressBar.Invisible()
    }

    override fun showNoData() {
        toast("No Data Available, Cek Your Code")
    }

    override fun showLastMatch(data: List<MatchEvent>) {
        showMatchList(data)
    }

    override fun showNextMatch(data: List<MatchEvent>) {
        showMatchList(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout()
        generateData()
    }

    private fun clicked(item: MatchEvent) {
        startActivity<SecondActivity>(DETAIL to item)
    }

    private fun showMatchList(data: List<MatchEvent>) {
        matchEvent.clear()
        matchEvent.addAll(data)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)
    }

    private fun mainLayout() {
        val spinner = findViewById<Spinner>(main_spinner)
        progressBar = findViewById(main_progBar)
        recyclerView = findViewById(main_recyclerView)
        val bottomNavigation = findViewById<BottomNavigationView>(main_botNav)

        val spinnerItems = resources.getStringArray(leagueName)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueId = spinner.selectedItem.toString()

                when (presenter.idChoice) {
                    0 -> when(leagueId) {
                        "English Premiere League" -> presenter.getLastMatch("4328")
                        "German Bundesliga" -> presenter.getLastMatch("4331")
                        "Spanish La Liga" -> presenter.getLastMatch("4335")
                    }

                    1 -> when(leagueId) {
                        "English Premiere League" -> presenter.getNextMatch("4328")
                        "German Bundesliga" -> presenter.getNextMatch("4331")
                        "Spanish La Liga" -> presenter.getNextMatch("4335")
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        /*bottomNavigation.menu.apply {
            add("Last Match")
                    .setOnMenuItemClickListener {
                        if (leagueId == "English Premiere League") {
                            presenter.getLastMatch("4328")
                        } else if (leagueId == "German Bundesliga") {
                            presenter.getLastMatch("4331")
                        } else if (leagueId == "Spanish La Liga") {
                            presenter.getLastMatch("4335")
                        }
                        false
                    }
                    .setIcon(R.drawable.ic_arrow_back_24dp)
            add("Next Match")
                    .setOnMenuItemClickListener {
                        if (leagueId == "English Premiere League") {
                            presenter.getNextMatch("4328")
                        } else if (leagueId == "German Bundesliga") {
                            presenter.getNextMatch("4332")
                        } else if (leagueId == "Spanish La Liga") {
                            presenter.getNextMatch("4335")
                        }
                        false
                    }
                    .setIcon(R.drawable.ic_arrow_forward_24dp)
        }*/

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                last_match -> {
                    toast("Last")
                }

                next_match -> {
                    toast("Next")
                }

                fav -> {
                    toast("fav")
                }
            }
            true
        }

        bottomNavigation.selectedItemId = last_match
    }

    private fun generateData() {
        val request = APIRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        adapter = MainAdapter(matchEvent, { item: MatchEvent -> clicked(item) })
        recyclerView.adapter = adapter
    }
}
