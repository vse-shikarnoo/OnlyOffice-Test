package kv.test.office.data.repository

import android.util.Log
import kv.test.office.data.api.OnlyOfficeService
import kv.test.office.data.model.User

private const val TAG = "Profile Repository Logs"
class ProfileRepository {
    suspend fun getProfile(): User {
        val result = OnlyOfficeService.onlyOfficeService!!.getUserProfile(OnlyOfficeService.getToken()?:"")
        Log.i(TAG, "getProfile: $result")
        return result.response
    }

    suspend fun logout(){
        Log.i(TAG, "logout: ")
        OnlyOfficeService.onlyOfficeService!!.logOut()
        OnlyOfficeService.clearToken()
    }
}