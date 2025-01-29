package kv.test.office.data.repository

import android.util.Log
import kv.test.office.data.api.OnlyOfficeService
import kv.test.office.data.model.FilesInfo

private const val TAG = "Trash Repository Logs"
class TrashRepository {

    suspend fun getTrash(): FilesInfo{
        val result = OnlyOfficeService.onlyOfficeService!!.getTrash(OnlyOfficeService.getToken()?:"")
        Log.i(TAG, "getFiles: $result")
        return result.response
    }

    suspend fun getFiles(folderId:Int): FilesInfo {
        val result = OnlyOfficeService.onlyOfficeService!!.getFiles(OnlyOfficeService.getToken()?:"",folderId)
        Log.i(TAG, "getFiles $folderId: $result")
        return result.response
    }
}