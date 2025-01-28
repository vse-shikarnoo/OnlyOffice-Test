package kv.test.office.data.repository

import android.util.Log
import kv.test.office.data.api.OnlyOfficeService
import kv.test.office.data.model.AuthRequest
import kv.test.office.data.model.AuthResponse
import kv.test.office.data.model.FileItem
import javax.inject.Inject

const val TAG = "OnlyOffice Repository Logs"

class OnlyOfficeRepository(

) {

    private var api: OnlyOfficeService? = null

    suspend fun authenticate(portal:String, email: String, password: String): AuthResponse {

        api = OnlyOfficeService.create(portal)

        val result = api!!.authenticate(AuthRequest(email, password))
        Log.i(TAG, "authenticate: $result")
        return result
    }

    suspend fun getFiles(folderId: String? = null): List<FileItem> {
        val result =  api!!.getFiles(folderId)
        Log.i(TAG, "getFiles: $result")
        return result
    }
}