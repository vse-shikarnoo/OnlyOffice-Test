package kv.test.office.ui.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kv.test.office.data.model.User
import kv.test.office.data.repository.ProfileRepository

private const val TAG = "Profile ViewModel Logs"

class ProfileViewModel : ViewModel() {

    private val repository = ProfileRepository()

    val profileState = mutableStateOf(User())
    val errorState = mutableStateOf(false)
    val loadingState = mutableStateOf(false)
    val logoutState = mutableStateOf(false)

    init {
        getProfile()
    }


    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loadingState.value = true
                errorState.value = false
                profileState.value = repository.getProfile()
                //Log.i(TAG, "getProfile: try")
            } catch (t: Throwable) {
                errorState.value = true
                profileState.value = User()
                //Log.e(TAG, "getProfile: error", t)
            } finally {
                loadingState.value = false
                //Log.i(TAG, "getProfile: finally")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                loadingState.value = true
                errorState.value = false
                //Log.i(TAG, "logout: try")
                repository.logout()
                logoutState.value = true
            } catch (t: Throwable) {
                errorState.value = true
                //Log.e(TAG, "logout: error", t)
            } finally {
                loadingState.value = false
                //Log.i(TAG, "logout: finally")
            }
        }
    }
}