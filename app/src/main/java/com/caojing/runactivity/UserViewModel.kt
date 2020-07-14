package com.caojing.runactivity

import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.flyco.tablayout.listener.CustomTabEntity
import com.jijia.kotlinlibrary.base.AppLiveData
import com.jijia.kotlinlibrary.base.BaseViewModel
import com.jijia.kotlinlibrary.entity.ApiResponse
import com.jijia.kotlinlibrary.utils.addData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.arrayListOf
import kotlin.collections.indices
import kotlin.collections.mutableListOf
import kotlin.collections.set

class UserViewModel() : BaseViewModel() {
    private var baseUrl: String = "https://www.wanandroid.com/"

    private val tittles = arrayListOf("首页", "资讯", "消息", "我的")

    private val unSelectedIds = arrayListOf(
        R.drawable.main_index_icon,
        R.drawable.main_house_icon,
        R.drawable.main_message_icon,
        R.drawable.main_me_icon
    )

    private val selectedIds = arrayListOf(
        R.drawable.main_index_select_icon,
        R.drawable.main_house_select_icon,
        R.drawable.main_message_select_icon,
        R.drawable.main_me_select_icon
    )

    val liveData = AppLiveData<ApiResponse<List<WxarticleBean>>>()

    val navigationLiveData = AppLiveData<HashMap<String, Any>>()

    var numLiveData=AppLiveData<Int>()

    fun updateNavigation() {
        var hashMap = HashMap<String, Any>()
        var tabEntity = mutableListOf<CustomTabEntity>()
        var fragments = mutableListOf<Fragment>()
        for (i in tittles.indices) {
            fragments.add(i, CodeFragment.getCodeFragment(tittles[i]))
        }
        tabEntity.addData(tittles, unSelectedIds, selectedIds)
        hashMap["fragments"] = fragments
        hashMap["tabEntity"] = tabEntity
        navigationLiveData.postValue(hashMap)
    }


    fun getUserList() {
        viewModelScope.launch {
            liveData.postValue(getData(baseUrl, "wxarticle/chapters/json"))
        }
    }

    fun MyTimerTask() {
        viewModelScope.launch {
            repeat(100){
                numLiveData.postValue(it)
                delay(500L)
            }
        }
    }
}