package com.caojing.runactivity

import androidx.lifecycle.LiveData
import com.jijia.kotlinlibrary.entity.ApiResponse
import com.jijia.kotlinlibrary.net.ApiRequest
import com.jijia.kotlinlibrary.net.RetrofitManage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


object UserApiRequest  {
    private var baseUrl: String = "http://192.168.14.16:8080/"


//    suspend fun listRepos(map: Map<String, String> = HashMap()): ApiResponse<UserBean> {
//        return httpGets(baseUrl,"users/CaojingCode/repos", map)
//    }
//
//    suspend fun getUserList():ApiResponse<List<UserItemBean>> {
//        return httpGets(baseUrl,"hello/users")
//    }
}