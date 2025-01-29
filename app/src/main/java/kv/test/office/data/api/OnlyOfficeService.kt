package kv.test.office.data.api

import kv.test.office.data.model.AuthRequest
import kv.test.office.data.model.AuthResponse
import kv.test.office.data.model.FilesResponse
import kv.test.office.data.model.User
import kv.test.office.data.model.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OnlyOfficeService {
    @POST("api/2.0/authentication")
    suspend fun authenticate(@Body request: AuthRequest): AuthResponse

    @GET("api/2.0/people/@self")
    suspend fun getUserProfile(@Header("Authorization") token:String): UserResponse

    @GET("api/2.0/files/@my")
    suspend fun getMyDocuments(@Header("Authorization") token:String): FilesResponse

    @GET("api/2.0/files/{folderid}")
    suspend fun getFiles(
        @Header("Authorization") token:String,
        @Path("folderid") folderId:Int
    ):FilesResponse

    @GET("api/2.0/files/@trash")
    suspend fun getTrash(): FilesResponse

    @GET("api/2.0/files/rooms")
    suspend fun getRooms(): FilesResponse

    @POST("/api/2.0/authentication/logout")
    suspend fun logOut()

    companion object {

        var onlyOfficeService: OnlyOfficeService? = null

        //"https://testdocspaceportal.onlyoffice.com/"
        fun create(baseUrl: String) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            onlyOfficeService = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(OnlyOfficeService::class.java)
        }

        fun clearService(){
            onlyOfficeService = null
        }
    }

}