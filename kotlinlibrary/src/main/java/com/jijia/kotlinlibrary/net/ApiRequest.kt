package com.jijia.kotlinlibrary.net

import com.jijia.kotlinlibrary.entity.ApiResponse
import okhttp3.OkHttpClient

object  ApiRequest {

     private val apiService:ApiService by lazy {
         RetrofitManage.retrofit.create(ApiService::class.java)
     }


    suspend fun <T> httpGets(
    baseUrl:String,
        apiName: String,
        map: Map<String, String> = HashMap<String, String>()
    ): ApiResponse<T> {
        return apiService.httpGet<T>(baseUrl + apiName, map)
    }
}