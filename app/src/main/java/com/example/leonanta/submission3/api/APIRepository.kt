package com.example.leonanta.submission3.api

import java.net.URL

class APIRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}
