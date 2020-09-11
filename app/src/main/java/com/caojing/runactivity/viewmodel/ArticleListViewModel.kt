package com.caojing.runactivity.viewmodel

import androidx.lifecycle.viewModelScope
import com.caojing.runactivity.entity.ArticleListBean
import com.caojing.runactivity.entity.SystemDataBean
import com.google.gson.reflect.TypeToken
import com.jijia.kotlinlibrary.base.AppLiveData
import com.jijia.kotlinlibrary.base.BaseViewModel
import com.jijia.kotlinlibrary.entity.ApiResponse
import kotlinx.coroutines.launch

/**
 * 文章列表数据处理
 */
class ArticleListViewModel : BaseViewModel() {

     var pageNum: Int = 0

    //文章列表liveData
    val articleLiveData = AppLiveData<ApiResponse<ArticleListBean>>()

    //体系分类数据
    var systemLiveData = AppLiveData<ApiResponse<List<SystemDataBean>>>()

     fun getTopArticleData(cid: Int = 0) {
        var apiName = if (cid > 0) {
            "article/list/$pageNum/json?cid=$cid"
        } else {
            "article/list/$pageNum/json"
        }
        viewModelScope.launch {
            var topArticleList = getData<ArticleListBean>(
                apiName,
                object : TypeToken<ApiResponse<ArticleListBean>>() {}.type
            )
            articleLiveData.postValue(topArticleList)
        }
    }

    /**
     * 体系分类数据列表接口
     */
    fun getSystemData() {
        viewModelScope.launch {
            var systemDataBean = getData<List<SystemDataBean>>(
                "tree/json",
                object : TypeToken<ApiResponse<List<SystemDataBean>>>() {}.type
            )
            systemLiveData.postValue(systemDataBean)
        }
    }

    /**
     * 下拉刷新
     */
    fun refreshArticle(cid: Int=0) {
        pageNum = 0
        getTopArticleData(cid)
    }


    /**
     * 加载更多
     */
    fun loadMoreArticle(cid: Int=0) {
        pageNum++
        getTopArticleData(cid)
    }

}