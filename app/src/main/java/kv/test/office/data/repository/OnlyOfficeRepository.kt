package kv.test.office.data.repository

import android.util.Log
import kv.test.office.data.api.OnlyOfficeService
import kv.test.office.data.model.AuthRequest
import kv.test.office.data.model.AuthResponse
import kv.test.office.data.model.FilesInfo
import kv.test.office.data.model.FilesResponse
import kv.test.office.data.model.User

const val TAG = "OnlyOffice Repository Logs"

class OnlyOfficeRepository(

) {

    //Немного бомбанул с того что в ответе приходил токен, а почему то не парсился
    suspend fun authenticate(portal: String, email: String, password: String) {

        OnlyOfficeService.create(portal)


        curToken = OnlyOfficeService.onlyOfficeService!!.authenticate(AuthRequest(email, password)).response.token
    }

    suspend fun getMyDocuments(): FilesInfo {
        val result = OnlyOfficeService.onlyOfficeService!!.getMyDocuments(curToken)
        Log.i(TAG, "getFiles: $result")
        return result.response
    }

    suspend fun getFiles(folderId:Int): FilesInfo{
        val result = OnlyOfficeService.onlyOfficeService!!.getFiles(curToken,folderId)
        Log.i(TAG, "getFiles $folderId: $result")
        return result.response
    }

    suspend fun getProfile(): User {
        val result = OnlyOfficeService.onlyOfficeService!!.getUserProfile(curToken)
        Log.i(TAG, "getProfile: $result")
        return result.response
    }

    suspend fun logout(){
        Log.i(TAG, "logout: ")
        OnlyOfficeService.onlyOfficeService!!.logOut()
        curToken = ""
    }

    companion object {
        var curToken = ""
    }
}