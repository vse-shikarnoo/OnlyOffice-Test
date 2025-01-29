package kv.test.office.ui.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kv.test.office.data.model.User
import kv.test.office.data.repository.OnlyOfficeRepository

private const val TAG = "Profile ViewModel Logs"

class ProfileViewModel : ViewModel() {

    private val repository = OnlyOfficeRepository()

    val profileState = mutableStateOf<User>(User())

    val logoutState = mutableStateOf(false)

    init {
        getProfile()
    }


    private fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                profileState.value = repository.getProfile()
                Log.i(TAG, "getProfile: try")
            } catch (t: Throwable) {
                Log.e(TAG, "getProfile: error", t)
            } finally {
                Log.i(TAG, "getProfile: finally")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                Log.i(TAG, "logout: try")
                repository.logout()
                logoutState.value = true
            } catch (t: Throwable) {
                Log.e(TAG, "logout: error", t)
            } finally {
                Log.i(TAG, "logout: finally")
            }
        }
    }
}