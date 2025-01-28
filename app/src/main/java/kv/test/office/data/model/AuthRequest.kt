package kv.test.office.data.model

data class AuthRequest(
    val username: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val expires: String
)