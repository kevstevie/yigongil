package com.created.team201.data.datasource.local

interface TokenDataSource {

    fun getAccessToken(): String

    fun setAccessToken(token: String)
}
