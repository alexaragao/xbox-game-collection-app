package com.xboxgamecollection.app.database

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TokenDataStore {

    companion object {
        private val dataStore = createDataStore()

        private const val PREFERENCE_ACCESS_TOKEN = "TokenStore_AccessToken"
        private const val PREFERENCE_REFRESH_TOKEN = "TokenStore_RefreshToken"

        private val accessTokenKey = stringPreferencesKey(PREFERENCE_ACCESS_TOKEN)
        private val refreshTokenKey = stringPreferencesKey(PREFERENCE_REFRESH_TOKEN)

        suspend fun getRefreshToken(): String? =
            dataStore.data.map { preferences -> preferences[refreshTokenKey] }.first()

        suspend fun getAccessToken(): String? =
            dataStore.data.map { preferences -> preferences[accessTokenKey] }.first()

        suspend fun storeTokens(accessToken: String, refreshToken: String) =
            dataStore.edit { preferences ->
                preferences[accessTokenKey] = accessToken
                preferences[refreshTokenKey] = refreshToken
            }

        suspend fun clearTokens() =
            dataStore.edit { preferences ->
                preferences.remove(accessTokenKey)
                preferences.remove(refreshTokenKey)
            }
    }

}
