package com.example.rusalfood.domain.shared_pref

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

class EncryptedSharedPrefImpl @Inject constructor(context: Context) :
    EncryptedSharedPref {

    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    private val encryptedSharedPref = EncryptedSharedPreferences.create(
        context,
        "encrypted_shared_pref",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun putString(name: String, value: String) {
        encryptedSharedPref.edit().putString(name, value).apply()
    }

    override fun getString(name: String, defaultValue: String) : String? {
       return encryptedSharedPref.getString(name, defaultValue)
    }

}