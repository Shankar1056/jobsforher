package com.jobsforher.utility

import android.content.Context

object ClsGeneral {
    fun setBooleanPreference(context: Context, key: String?, value: Boolean?) {
        val editor = context.getSharedPreferences(
            "WED_APP", Context.MODE_PRIVATE
        ).edit()
        editor.putBoolean(key, value!!)
        editor.commit()
    }

    fun getBooleanPreferences(context: Context, key: String?): Boolean {
        val prefs = context.getSharedPreferences(
            "WED_APP",
            Context.MODE_PRIVATE
        )
        return prefs.getBoolean(key, false)
    }
}