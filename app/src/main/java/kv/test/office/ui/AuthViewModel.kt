package kv.test.office.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kv.test.office.data.model.AuthResponse
import kv.test.office.data.repository.OnlyOfficeRepository

class AuthViewModel: ViewModel() {

    private val repository = OnlyOfficeRepository()

    val authState = mutableStateOf<Boolean>(false)
    val errorState = mutableStateOf(false)
    val loadingState = mutableStateOf(false)

    fun authenticate(portal:String, email: String, password:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(2000)
                repository.authenticate(portal,email, password)
                authState.value = true
            }catch (t:Throwable){
                errorState.value = true
            }finally {
                loadingState.value = false
            }
        }
    }
}