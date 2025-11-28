package com.apparel.offprice.common.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preference")

@Singleton
class AppPreference @Inject constructor(
    @ApplicationContext private val context: Context
){
    private val dataStore = context.dataStore

    companion object {
        private val LANGUAGE_PREFERENCE = stringPreferencesKey("LanguagePreference")

        private val REGIONAL_PREFERENCE = stringPreferencesKey("RegionalPreference")
    }

    val languagePreference: Flow<String?> = dataStore.data.map { preferences -> preferences[LANGUAGE_PREFERENCE] ?: "en"}

    val regionalPreference: Flow<String?> = dataStore.data.map { preferences -> preferences[REGIONAL_PREFERENCE] ?: ""}


    suspend fun saveLanguagePreference(language: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE_PREFERENCE] = language
        }
    }

    suspend fun clearLanguagePreference() {
        dataStore.edit { preferences ->
            preferences.remove(LANGUAGE_PREFERENCE)
        }
    }

    suspend fun saveRegionalPreference(region: String) {
        dataStore.edit { preferences ->
            preferences[REGIONAL_PREFERENCE] = region
        }
    }

    suspend fun clearRegionalPreference() {
        dataStore.edit { preferences ->
            preferences.remove(REGIONAL_PREFERENCE)
        }
    }


}