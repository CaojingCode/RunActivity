package com.caojing.runactivity.entity

import com.google.gson.annotations.SerializedName

data class ArticleListBean(
    val curPage: Int,
    @SerializedName("datas")
    val dataList: List<ArticleBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
) {

}