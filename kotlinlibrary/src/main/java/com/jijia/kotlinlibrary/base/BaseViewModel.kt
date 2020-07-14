package com.jijia.kotlinlibrary.base

import androidx.lifecycle.ViewModel
import com.jijia.kotlinlibrary.entity.ApiResponse
import com.jijia.kotlinlibrary.entity.AppState
import com.jijia.kotlinlibrary.net.ApiRequest
import com.jijia.kotlinlibrary.net.ApiService
import com.jijia.kotlinlibrary.net.RetrofitManage

open class BaseViewModel : ViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitManage.retrofit.create(ApiService::class.java)
    }



    suspend fun <T> getData(
        baseUrl: String,
        apiName: String,
        map: Map<String, String> = HashMap<String, String>()
    ): ApiResponse<T> {
        var apiResponse=ApiResponse<T>(data = null)
      var isSuccess=  CatchException.start {
             apiResponse=apiService.httpGet(baseUrl+apiName, map)
        }
        if (!isSuccess){
            apiResponse.state=AppState.ERROR
        }else{
            if (apiResponse.code==0){
                if (apiResponse.data==null){
                    apiResponse.state=AppState.EMPTY
                }else{
                    apiResponse.state=AppState.SUCCESS
                }
            }else{
                apiResponse.state=AppState.ERROR
            }
        }

        return apiResponse
    }


}

