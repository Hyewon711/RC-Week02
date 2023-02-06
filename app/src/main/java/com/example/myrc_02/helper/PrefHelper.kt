package com.example.myrc_02.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bumptech.glide.Glide.init

class PrefHelper (context: Context) {

    private val PREFS_NAME = "session"
    private var sharedPref: SharedPreferences

    val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    /* ID & PW */
    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    /* ID & IsLogin */
    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    /* Toast & Login Check용 ID */
    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    /* 데이터 삭제 */
    fun clear() {
        editor?.remove("PREF_USERNAME")?.apply()
        editor?.remove("PREF_PASSWORD")?.apply()
    }

    fun clearOrder() {
        editor?.remove("PREF_ORDER_NAME")?.apply()
        editor?.remove("PREF_ORDER_TEL")?.apply()
        editor?.remove("PREF_ORDER_MAIL")?.apply()
    }

}