package kv.test.office.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class AuthRequest(
    val username: String,
    val password: String
)


data class AuthResponse(
    val response: Response
)

data class Response(
    val token: String
)