package com.caojing.runactivity.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.caojing.runactivity.R
import com.caojing.runactivity.entity.MenuData
import com.caojing.runactivity.viewmodel.MainViewModel
import com.flyco.tablayout.listener.CustomTabEntity
import com.jijia.kotlinlibrary.base.BaseActivity
import com.jijia.kotlinlibrary.entity.AppState
import com.jijia.kotlinlibrary.utils.appObserve
import com.jijia.kotlinlibrary.utils.mmkv
import com.jijia.kotlinlibrary.utils.permissionRequest
import com.qmuiteam.qmui.widget.tab.QMUIBasicTabSegment
import com.qmuiteam.qmui.widget.tab.QMUITabSegment2
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity() {
    private var currentTab = 0

    val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            currentTab = savedInstanceState.getInt("currentTab")
        }
    }

    override fun layoutResID(): Int {
        return R.layout.activity_main
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentTab", tabLayout.currentTab)
    }

    override fun initView() {
        titleBarHide()
        mainViewModel.navigationLiveData.observe(this, Observer {
            /**
             * 一行代码构建底部导航栏
             */
            /**
             * 一行代码构建底部导航栏
             */
            tabLayout.setTabData(
                it["tabEntity"] as ArrayList<CustomTabEntity>,
                this,
                R.id.vpFragment,
                it["fragments"] as ArrayList<Fragment>
            )
            tabLayout.currentTab = currentTab
        })
        /**
         * 一行代码实现数据数据存储
         * 性能对比sp提高很多倍
         */
        mmkv.encode("sss", "bbbbbbbbb")


        /**
         * 一行代码请求权限
         */
        permissionRequest(PermissionConstants.STORAGE) {
            //一行代码加载webView
//            webView.settings.userAgentString = "originFormAndWebView"
//            ToastUtils.showLong(webView.settings.userAgentString)
//            webView.loadUrl("http://120.76.210.152:8088/pano/vr/uri/4855051A90BECB9767EA0BCE8C7DADF5?hc=E9A40E5C431D44E5AC82F4250E5CDEB5&ac=20052510142501FB16E873E044C09F9C&cs=51&ht=2&from=singlemessage")
//            RxWebViewTool.initWebView(this, webView, object : OnWebViewLoad {
//                override fun onPageFinished() {
//                }
//
//                override fun onPageStarted() {
//                }
//
//                override fun onProgressChanged(newProgress: Int) {
//                }
//
//                override fun onReceivedTitle(title: String) {
//                }
//
//                override fun shouldOverrideUrlLoading() {
//                }
//
//            })
        }
    }

    override fun initData() {
    }

}
