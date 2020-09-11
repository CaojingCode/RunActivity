package com.caojing.runactivity.activity

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.caojing.runactivity.R
import com.caojing.runactivity.adapter.ArticleListAdapter
import com.caojing.runactivity.viewmodel.ArticleListViewModel
import com.jijia.kotlinlibrary.base.BaseActivity
import com.jijia.kotlinlibrary.utils.appObserve
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_article_list.*

/**
 * 文章列表
 */
open class ArticleListActivity : BaseActivity() {

    private var isRefresh = true //是否是下拉刷新请求数据

    var cid = 0 //分类id ，文章列表中没有筛选，值为0

    override fun layoutResID(): Int {
        return R.layout.activity_article_list
    }

    val articleViewModel by lazy {
        ViewModelProvider(this).get(ArticleListViewModel::class.java)
    }

    //文章列表适配器
    private lateinit var articleAdapter: ArticleListAdapter

    override fun initView() {
        setTittle("文章列表")
        requestApi()
        rvArticleList.layoutManager = LinearLayoutManager(this)
        articleAdapter = ArticleListAdapter()
        rvArticleList.adapter = articleAdapter
        articleAdapter.bindToRecyclerView(rvArticleList)

        refreshLayout.autoRefresh()
        refreshLayout.setOnRefreshListener {
            isRefresh = true
            articleViewModel.refreshArticle(cid)
        }
        refreshLayout.setOnLoadMoreListener {
            isRefresh = false
            articleViewModel.loadMoreArticle(cid)
        }

    }

    /**
     * 进入页面接口请求
     */
    open fun requestApi() {
        articleViewModel.getTopArticleData()
    }

    override fun initData() {
        articleViewModel.articleLiveData.appObserve(this, {
            if (isRefresh) {
                //下拉刷新，则替换数据
                articleAdapter.setNewData(it.dataList);
                refreshLayout.finishRefresh()
            } else {
                articleAdapter.addData(it.dataList)
                if (it.over) {
                    refreshLayout.finishLoadMoreWithNoMoreData()
                } else {
                    refreshLayout.finishLoadMore(true)
                }

            }
        }, {
            //请求失败回调
            if (isRefresh) {
                refreshLayout.finishRefresh(false)
            } else {
                refreshLayout.finishLoadMore(false)
            }
        }, {
            //数据为空回调
            if (isRefresh) {
                refreshLayout.finishRefreshWithNoMoreData()
            } else {
                refreshLayout.finishLoadMoreWithNoMoreData()
            }
        })
    }
}