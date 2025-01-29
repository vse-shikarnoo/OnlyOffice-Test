package kv.test.office.data.model

data class UserResponse(
    val response:User
)

data class User(
    val id: String="",
    val email: String="",
    val displayName: String="",
    val avatarUrl: String=""
)
