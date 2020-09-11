package com.caojing.runactivity.activity

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.billy.android.swipe.SmartSwipe
import com.billy.android.swipe.consumer.DrawerConsumer
import com.caojing.runactivity.R
import com.caojing.runactivity.adapter.SystemDataAdapter
import com.jijia.kotlinlibrary.utils.appObserve
import kotlinx.android.synthetic.main.activity_article_list.*
import kotlinx.android.synthetic.main.article_draw_layout.view.*

class SystemArticleActivity : ArticleListActivity() {

    private lateinit var drawerConsumer: DrawerConsumer
    private lateinit var drawView: View

    private var oneAdapter = SystemDataAdapter(true)

    private var twoAdapter = SystemDataAdapter(false)
    override fun initView() {
        setGoneRight(false)
        super.initView()
        drawView = LayoutInflater.from(this).inflate(R.layout.article_draw_layout, null)
        drawView.rvMenuOne.layoutManager = LinearLayoutManager(this)
        drawView.rvMenuOne.adapter = oneAdapter
        drawView.rvMenuTwo.layoutManager=LinearLayoutManager(this)
        drawView.rvMenuTwo.adapter=twoAdapter
        drawerConsumer =
            SmartSwipe.wrap(this)
                .addConsumer(DrawerConsumer())
                .setRightDrawerView(drawView)
                .setScrimColor(0x2F000000) //设置遮罩的颜色
                .setShadowColor(resources.getColor(R.color.Gray80_80))    //设置边缘的阴影颜色
        oneAdapter.setOnItemClickListener { adapter, view, position ->
            var dataList=oneAdapter.data
            for (i in dataList.indices){
                dataList[i].isSelect = i==position
            }
            oneAdapter.notifyDataSetChanged()
            twoAdapter.setNewData(dataList[position].children)
        }
        twoAdapter.setOnItemClickListener { _, view, position ->
            cid=twoAdapter.data[position].id
            drawerConsumer.smoothClose() //必须先关闭侧边栏，在刷新数据，不然会导致刷新不更新页面。
            refreshLayout.autoRefresh()
        }
    }

    override fun initData() {
        super.initData()
        articleViewModel.systemLiveData.appObserve(this, {
            oneAdapter.setNewData(it)
            twoAdapter.setNewData(it[0].children)
        })
    }

    override fun rightAction() {
        //点击标题栏右侧按钮
        drawerConsumer.smoothRightOpen()
    }

    override fun requestApi() {
        articleViewModel.getSystemData()
    }
}

