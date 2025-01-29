package kv.test.office.ui.rooms

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kv.test.office.data.model.FilesInfo
import kv.test.office.data.repository.RoomsRepository


class RoomsViewModel : ViewModel() {

    private val repository = RoomsRepository()

    val docsState = mutableStateOf(FilesInfo())

    val errorState = mutableStateOf(false)
    val loadingState = mutableStateOf(false)

    private val currentFolderId = mutableIntStateOf(0)
    private val rootId = mutableIntStateOf(0)
    val isRoot = mutableStateOf(true)

    init {
        getStart()
    }

    fun getStart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loadingState.value = true
                errorState.value = false
                docsState.value = repository.getRooms()

                rootId.intValue = docsState.value.current.id
                currentFolderId.intValue = docsState.value.current.id
            } catch (t: Throwable) {
                //Log.e(TAG, "getStart: error", t)
                errorState.value = true
                docsState.value = FilesInfo()
            } finally {
                loadingState.value = false
                //Log.i(TAG, "getStart: finally")
            }
        }
    }


    fun getFiles(folderId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Log.i(TAG, "getFiles: try")
                loadingState.value = true
                errorState.value = false
                docsState.value = repository.getFiles(folderId)

                currentFolderId.intValue = docsState.value.current.id
                isRoot.value = currentFolderId.intValue == rootId.intValue

            } catch (t: Throwable) {
                errorState.value = true
                //Log.e(TAG, "getFiles: error", t)
            } finally {
                loadingState.value = false
                //Log.i(TAG, "getFiles: finally")
            }
        }
    }

}