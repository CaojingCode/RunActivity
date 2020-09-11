package com.caojing.runactivity.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.caojing.runactivity.viewmodel.ArticleListViewModel


class ArticleViewModelFactory(var cid: Int=0) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleListViewModel::class.java)) {
            return ArticleListViewModel() as T
        }
        throw RuntimeException("unknown class :" + modelClass.name)
    }
}