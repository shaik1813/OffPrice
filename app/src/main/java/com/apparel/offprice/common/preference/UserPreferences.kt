package com.apparel.offprice.common.preference


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val IS_FIRST_TIME = booleanPreferencesKey("isFirstTime")
    }

    val username: Flow<String?> = dataStore.data.map { preferences -> preferences[USERNAME_KEY] }

    val isFirstTime : Flow<Boolean> = dataStore.data.map { preferences -> preferences[IS_FIRST_TIME] ?: false }

    suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
        }
    }

    suspend fun clearUsername() {
        dataStore.edit { preferences ->
            preferences.remove(USERNAME_KEY)
        }
    }

    suspend fun isFirstTimeVisit(bool: Boolean){
        dataStore.edit { preferences ->
            preferences[IS_FIRST_TIME] = bool
        }
    }
}