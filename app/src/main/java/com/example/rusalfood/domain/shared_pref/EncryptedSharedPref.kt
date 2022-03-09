package com.example.rusalfood.domain.shared_pref

interface EncryptedSharedPref {
    fun putString(name: String, value: String)
    fun getString(name: String, defaultValue: String) : String?
}