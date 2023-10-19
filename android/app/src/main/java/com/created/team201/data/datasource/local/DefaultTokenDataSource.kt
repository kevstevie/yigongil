package com.created.team201.data.datasource.local

import javax.inject.Inject

class DefaultTokenDataSource @Inject constructor(
    private val tokenStorage: TokenStorage,
) : TokenDataSource {
    override fun getIsGuest(): Boolean {
        return tokenStorage.isGuest(REFRESH_TOKEN)
    }

    override fun getAccessToken(): String {
        return tokenStorage.fetchToken(ACCESS_TOKEN)
    }

    override fun getRefreshToken(): String {
        return tokenStorage.fetchToken(REFRESH_TOKEN)
    }

    override fun setAccessToken(token: String) {
        tokenStorage.putToken(ACCESS_TOKEN, token)
    }

    override fun setRefreshToken(token: String) {
        tokenStorage.putToken(REFRESH_TOKEN, token)
    }

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }
}
