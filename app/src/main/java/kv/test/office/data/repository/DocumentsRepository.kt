package kv.test.office.data.repository

import android.util.Log
import kv.test.office.data.api.OnlyOfficeService
import kv.test.office.data.model.FilesInfo

private const val TAG = "Documents Repository Logs"
class DocumentsRepository {

    suspend fun getMyDocuments(): FilesInfo {
        val result = OnlyOfficeService.onlyOfficeService!!.getMyDocuments(OnlyOfficeService.getToken()?:"")
        Log.i(TAG, "getFiles: $result")
        return result.response
    }

    suspend fun getFiles(folderId:Int): FilesInfo{
        val result = OnlyOfficeService.onlyOfficeService!!.getFiles(OnlyOfficeService.getToken()?:"",folderId)
        Log.i(TAG, "getFiles $folderId: $result")
        return result.response
    }
}