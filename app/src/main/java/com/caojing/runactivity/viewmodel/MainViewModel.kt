package com.caojing.runactivity.viewmodel

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils.getListType
import com.blankj.utilcode.util.ToastUtils
import com.caojing.runactivity.R
import com.caojing.runactivity.entity.ArticleBean
import com.caojing.runactivity.entity.WxarticleBean
import com.caojing.runactivity.entity.BannerData
import com.caojing.runactivity.entity.MenuData
import com.caojing.runactivity.fragment.*
import com.flyco.tablayout.listener.CustomTabEntity
import com.google.gson.reflect.TypeToken
import com.jijia.kotlinlibrary.base.AppLiveData
import com.jijia.kotlinlibrary.base.BaseViewModel
import com.jijia.kotlinlibrary.entity.ApiResponse
import com.jijia.kotlinlibrary.utils.addData
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.arrayListOf
import kotlin.collections.mutableListOf
import kotlin.collections.set

class MainViewModel() : BaseViewModel() {


    private val tittles = arrayListOf("首页", "项目", "公众号", "问答", "我的")

    private val unSelectedIds = arrayListOf(
        R.drawable.main_index_icon,
        R.drawable.main_house_icon,
        R.drawable.main_message_icon,
        R.drawable.main_house_icon,
        R.drawable.main_me_icon
    )

    private val selectedIds = arrayListOf(
        R.drawable.main_index_select_icon,
        R.drawable.main_house_select_icon,
        R.drawable.main_message_select_icon,
        R.drawable.main_house_select_icon,
        R.drawable.main_me_select_icon
    )

    private val fragments = arrayListOf(
        IndexFragment.instant,
        ProjectFragment.instant,
        OfficialAccountsFragment.instant,
        QuestionFragment.instant,
        MeFragment.instant
    )

    private val menuList = mutableListOf(
        MenuData("文章", R.mipmap.ic_launcher),
        MenuData("体系", R.mipmap.ic_launcher),
        MenuData("导航", R.mipmap.ic_launcher),
        MenuData("广场", R.mipmap.ic_launcher)
    )

    //轮播图liveData
    val bannerLiveData = AppLiveData<ApiResponse<List<BannerData>>>()

    //首页导航栏liveData
    val navigationLiveData = AppLiveData<HashMap<String, Any>>()

    //置顶文章liveData
    val topArticleLD = AppLiveData<ApiResponse<List<ArticleBean>>>()

    //首页功能菜单
     val menuLiveData = AppLiveData<MutableList<MenuData>>()

    init {
        updateNavigation()
        getMenuData()
        getBannerData()
        getTopArticleData()
    }

    private fun getMenuData() {
        menuLiveData.postValue(menuList)
    }

    /**
     * 更新首页底部导航栏数据
     */
    private fun updateNavigation() {
        var hashMap = HashMap<String, Any>()
        var tabEntity = mutableListOf<CustomTabEntity>()

        tabEntity.addData(tittles, unSelectedIds, selectedIds)
        hashMap["fragments"] = fragments
        hashMap["tabEntity"] = tabEntity
        navigationLiveData.postValue(hashMap)
    }

    /**
     * 获取首页banner图
     */
    private fun getBannerData() {
        viewModelScope.launch {
            var bannerList = getData<List<BannerData>>(
                apiName = "banner/json",
                type = object : TypeToken<ApiResponse<List<BannerData>>>() {}.type
            )
            bannerLiveData.postValue(bannerList)
        }
    }


    private fun getTopArticleData() {
        viewModelScope.launch {
            var topArticleList = getData<List<ArticleBean>>(
                "article/top/json",
                object : TypeToken<ApiResponse<List<ArticleBean>>>() {}.type
            )

            topArticleLD.postValue(topArticleList)
        }
    }

    override fun getSuccessCode(): Int {
        return 0
    }

}