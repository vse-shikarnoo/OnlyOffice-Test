package kv.test.office.data.repository

import kv.test.office.data.api.OnlyOfficeService
import kv.test.office.data.model.AuthRequest

class AuthRepository {
    suspend fun authenticate(portal: String, email: String, password: String) {

        OnlyOfficeService.create(portal)

        OnlyOfficeService.setToken(OnlyOfficeService.onlyOfficeService!!.authenticate(AuthRequest(email, password)).response.token)
    }
}