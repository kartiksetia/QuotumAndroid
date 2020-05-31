package com.quotum.quotum.quotum.localdatabase

import android.content.Context
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.APP_PREFERENCES_FILE_NAME
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.NAME_KEY
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.PROFILE_PIC_KEY
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.TOKEN_KEY
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.USER_ADDRESS_KEY
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.USER_DOB_KEY
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.USER_EMAIL_KEY
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.USER_ID_KEY
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.USER_MOBILE_KEY
import com.quotum.quotum.quotum.localdatabase.ApplicationConstants.USER_NAME_KEY

object LocalDB {

    fun setUserData(context: Context, userId: String, userName: String, name: String, mobile: String, address: String, email: String,
        dob: String, image: String) {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        val edit = sp.edit()
        edit.putString(USER_ID_KEY, userId)
        edit.putString(USER_NAME_KEY, userName)
        edit.putString(NAME_KEY, name)
        edit.putString(USER_MOBILE_KEY, mobile)
        edit.putString(USER_ADDRESS_KEY, address)
        edit.putString(USER_EMAIL_KEY, email)
        edit.putString(USER_DOB_KEY, dob)
        edit.putString(PROFILE_PIC_KEY, image)
        edit.apply()
    }

    fun setUserToken(context: Context, userToken: String?) {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        val edit = sp.edit()
        edit.putString(TOKEN_KEY, userToken)
        edit.apply()
    }

    fun getUserToken(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(TOKEN_KEY, null)
    }

    fun getUserId(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(USER_ID_KEY, null)
    }

    fun getUserName(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(USER_NAME_KEY, null)
    }

    fun getName(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(NAME_KEY, null)
    }

    fun getUserMobile(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(USER_MOBILE_KEY, null)
    }

    fun getUserAddress(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(USER_ADDRESS_KEY, null)
    }

    fun getUserDOB(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(USER_DOB_KEY, null)
    }

    fun getUserEmail(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(USER_EMAIL_KEY, null)
    }

    fun getUserImage(context: Context): String? {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.getString(PROFILE_PIC_KEY, null)
    }

    fun clearAllAppData(context: Context) {
        val sp = context.getSharedPreferences(
            APP_PREFERENCES_FILE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }
}