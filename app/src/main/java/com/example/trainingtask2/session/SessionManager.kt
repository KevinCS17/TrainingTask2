package com.example.trainingtask2.session

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.trainingtask2.data.model.LoginModel
import javax.inject.Inject

class SessionManager @Inject constructor(context: Context){
    val token = "SP_TOKEN"
    val keyGenParameterSpec :KeyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        token,
        mainKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    val sharedPreferencesEdit : SharedPreferences.Editor = sharedPreferences.edit()

    fun setToken(token_string : String){
        sharedPreferencesEdit.putString(token,token_string)
        sharedPreferencesEdit.commit()
    }

    @JvmName("getToken1")
    fun getToken(): String {
        return sharedPreferences.getString(token,"")!!
    }

    fun clear(){
        sharedPreferencesEdit.clear()
    }
}