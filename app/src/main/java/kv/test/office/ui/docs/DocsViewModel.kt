package kv.test.office.ui.docs

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kv.test.office.data.model.FilesInfo
import kv.test.office.data.repository.OnlyOfficeRepository


private const val TAG = "Docs ViewModel Logs"

class DocsViewModel : ViewModel() {

    private val repository = OnlyOfficeRepository()

    val docsState = mutableStateOf(FilesInfo())


    val currentFolderId = mutableStateOf(0)
    val rootId = mutableStateOf<Int>(0)
    val isRoot = mutableStateOf(true)

    init {
        getStart()
        rootId.value = docsState.value.current.id
        currentFolderId.value = docsState.value.current.id
    }

    private fun getStart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.i(TAG, "getStart: try")
                docsState.value = repository.getMyDocuments()
                currentFolderId.value = docsState.value.current.id
                isRoot.value = currentFolderId.value == rootId.value

            } catch (t: Throwable) {
                Log.e(TAG, "getStart: error", t)
            } finally {
                Log.i(TAG, "getStart: finally")
            }
        }
    }


    fun getFiles(folderId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.i(TAG, "getFiles: try")
                docsState.value = repository.getFiles(folderId)
                
                currentFolderId.value = docsState.value.current.id
                isRoot.value = currentFolderId.value == rootId.value

            } catch (t: Throwable) {
                Log.e(TAG, "getFiles: error", t)
            } finally {
                Log.i(TAG, "getFiles: finally")
            }
        }
    }

    fun goToParent(parentId: Int) {

    }
}