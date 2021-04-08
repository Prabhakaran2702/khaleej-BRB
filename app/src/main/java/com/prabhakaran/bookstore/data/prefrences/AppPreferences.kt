package com.prabhakaran.bookstore.data.local.prefrences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppPreferences (
    context: Context
){

    private val applicationContext = context.applicationContext
    private val datastore: DataStore<Preferences> = applicationContext.createDataStore(
        name = "com.prabhakaran.bookstore"
    )

    val userName: Flow<String?>
    get() = datastore.data.map {
        it[KEY_USER_NAME]
    }

    val email: Flow<String?>
    get() = datastore.data.map {
        it[KEY_EMAIL]
    }

    val phone: Flow<String?>
        get() = datastore.data.map {
            it[KEY_PHONE_NUMBER]
        }

    val address: Flow<String?>
        get() = datastore.data.map {
            it[KEY_ADDRESS]
        }


    suspend fun saveUserName(value: String){
        datastore.edit {
            it[KEY_USER_NAME] = value
        }
    }

    suspend fun saveEmail(value: String){
        datastore.edit {
            it[KEY_EMAIL] = value
        }
    }

    suspend fun savePhonenumber(value: String){
        datastore.edit {
            it[KEY_PHONE_NUMBER] = value
        }
    }


    suspend fun saveAddress(value: String){
        datastore.edit {
            it[KEY_ADDRESS] = value
        }
    }


    companion object{
        private val KEY_USER_NAME = preferencesKey<String>("KEY_USER_NAME")
        private val KEY_EMAIL = preferencesKey<String>("KEY_EMAIL")
        private val KEY_PHONE_NUMBER = preferencesKey<String>("KEY_PHONE_NUMBER")
        private val KEY_ADDRESS = preferencesKey<String>("KEY_ADDRESS")
    }
}