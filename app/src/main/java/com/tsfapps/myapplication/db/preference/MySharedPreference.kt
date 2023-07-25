package com.tsfapps.myapplication.db.preference

import android.content.Context
import android.content.SharedPreferences
import com.tsfapps.myapplication.utils.Constant.CREATE_RECORD_ID
import com.tsfapps.myapplication.utils.Constant.RECORD_ID
import com.tsfapps.myapplication.utils.Constant.SESSION_KEY
import com.tsfapps.myapplication.utils.Constant.SHARED_PREFS_NAME
import com.tsfapps.myapplication.utils.Constant.SYNC_ID
import com.tsfapps.myapplication.utils.Constant.USER_ID

class MySharedPreference(context: Context) {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    fun setSessionKey(sessionKey: String, userId: String) {
        val editor = sharedPrefs.edit()
        editor.putString(SESSION_KEY, sessionKey)
        editor.putString(USER_ID, userId)
        editor.apply()
    }

    fun getSessionKey(): String? {
        return sharedPrefs.getString(SESSION_KEY, null)
    }
    fun getUserId(): String? {
        return sharedPrefs.getString(USER_ID, null)
    }


    fun setSyncId(syncId: String){
        val editor = sharedPrefs.edit()
        editor.putString(SYNC_ID, syncId)
        editor.apply()
    }

    fun getSyncId(): String? {
        return sharedPrefs.getString(SYNC_ID, null)
    }
  fun setCreateRecordId(recordId: Int){
        val editor = sharedPrefs.edit()
        editor.putInt(CREATE_RECORD_ID, recordId)
        editor.apply()
    }

    fun getCreateRecordId(): Int? {
        return sharedPrefs.getInt(CREATE_RECORD_ID, 0)
    }
 fun setRecordId(recordId: String){
        val editor = sharedPrefs.edit()
        editor.putString(RECORD_ID, recordId)
        editor.apply()
    }

    fun getRecordId(): String? {
        return sharedPrefs.getString(RECORD_ID, null)
    }

}

