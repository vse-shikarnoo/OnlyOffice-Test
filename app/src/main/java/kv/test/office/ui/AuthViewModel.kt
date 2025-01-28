package kv.test.office.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kv.test.office.data.model.AuthResponse
import kv.test.office.data.repository.OnlyOfficeRepository


private const val TAG = "Auth ViewModel Logs"
class AuthViewModel: ViewModel() {

    private val repository = OnlyOfficeRepository()

    val authState = mutableStateOf(false)
    val errorState = mutableStateOf(false)
    val loadingState = mutableStateOf(false)

    fun authenticate(portal:String, email: String, password:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loadingState.value = true
                Log.i(TAG, "authenticate: try auth")
                delay(2000)
                repository.authenticate(portal,email, password)
                authState.value = true
                errorState.value = false
            }catch (t:Throwable){
                Log.e(TAG, "authenticate: error", t)
                errorState.value = true
            }finally {
                Log.i(TAG, "authenticate: finally")
                loadingState.value = false
            }
        }
    }
}