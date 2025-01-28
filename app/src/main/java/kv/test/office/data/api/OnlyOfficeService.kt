package kv.test.office.data.api

import kv.test.office.data.model.AuthRequest
import kv.test.office.data.model.AuthResponse
import kv.test.office.data.model.FileItem
import kv.test.office.data.model.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OnlyOfficeService {
    @POST("api/2.0/authentication")
    suspend fun authenticate(@Body request: AuthRequest): AuthResponse

    @GET("api/2.0/people/@self")
    suspend fun getUserProfile(): User

    @GET("api/2.0/files/{folderId}")
    suspend fun getFiles(@Path("folderId") folderId: String? = null): List<FileItem>

    @POST("/api/2.0/authentication/logout")
    suspend fun logOut()

    companion object{
        //"https://testdocspaceportal.onlyoffice.com/"
        fun create(baseUrl: String): OnlyOfficeService{
            val okHttpClient = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(OnlyOfficeService::class.java)
        }
    }

}