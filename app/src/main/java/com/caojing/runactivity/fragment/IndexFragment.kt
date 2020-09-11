package com.caojing.runactivity.fragment

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ActivityUtils
import com.caojing.runactivity.R
import com.caojing.runactivity.activity.ArticleListActivity
import com.caojing.runactivity.activity.SystemArticleActivity
import com.caojing.runactivity.adapter.GlideImageLoader
import com.caojing.runactivity.adapter.MenuAdapter
import com.caojing.runactivity.adapter.TopArticleAdapter
import com.caojing.runactivity.entity.ArticleBean
import com.caojing.runactivity.entity.BannerData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jijia.kotlinlibrary.utils.appObserve
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.banner_layout.view.*
import kotlinx.android.synthetic.main.fragment_index.view.*

class IndexFragment : MainFragment(), OnBannerListener, ViewPager.OnPageChangeListener,
    BaseQuickAdapter.OnItemClickListener {

    companion object {
        val instant: IndexFragment by lazy {
            IndexFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_index
    }

    private lateinit var bannerView: View  //轮播图控件


    lateinit var menuView: RecyclerView  //首页功能菜单
    var menuAdapter: MenuAdapter = MenuAdapter() //首页功能菜单适配器

    private lateinit var homePageAdapter: TopArticleAdapter
    private var bannerList: List<BannerData> = mutableListOf()

    override fun initView(view: View) {
        homePageAdapter = TopArticleAdapter()
        view.rvIndex.layoutManager = LinearLayoutManager(mActivity)
        view.rvIndex.adapter = homePageAdapter
        //讲轮播图当作列表的头部添加到列表中
        bannerView = LayoutInflater.from(mActivity).inflate(R.layout.banner_layout, null)
        bannerView.banner.setOnPageChangeListener(this)
        bannerView.banner.setOnBannerListener(this)
        bannerView.banner.setImageLoader(GlideImageLoader())
        homePageAdapter.addHeaderView(bannerView)
        menuView =
            LayoutInflater.from(mActivity).inflate(R.layout.menu_layout, null) as RecyclerView
        menuView.layoutManager = GridLayoutManager(mActivity, 4)
        menuView.adapter = menuAdapter
        homePageAdapter.addHeaderView(menuView)
        homePageAdapter.onItemClickListener = this
        menuAdapter.onItemClickListener = this

    }

    override fun initData() {
        //监听bannerLiveData数据变化，更新UI
        mActivity.mainViewModel.bannerLiveData.appObserve(mActivity, {
            bannerList = it
            bannerView.banner.setImages(it).start()
            bannerView.tvBannerTittle.visibility = View.VISIBLE
            bannerView.tvBannerTittle.text = it[0].title
        })

        //监听置顶文章数据变化
        mActivity.mainViewModel.topArticleLD.appObserve(mActivity, {
            homePageAdapter.setNewData(it as MutableList<ArticleBean>)
        }, {
            homePageAdapter.setEmptyView(R.layout.state_error_layout, menuView)
        })

        mActivity.mainViewModel.menuLiveData.observe(mActivity, Observer {
            menuAdapter.setNewData(it)
        })
    }

    override fun OnBannerClick(position: Int) {
        //轮播图点击事件

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        bannerView.tvBannerTittle.text = bannerList[position].title
    }


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        //点击功能菜单列表
        when (adapter) {
            is TopArticleAdapter -> {
                //点击置顶文章列表

            }
            is MenuAdapter -> when (position) {
                0 -> {
                    ActivityUtils.startActivity(ArticleListActivity::class.java)
                }
                1 -> {
                    ActivityUtils.startActivity(SystemArticleActivity::class.java)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        bannerView.banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        bannerView.banner.stopAutoPlay()
    }


}