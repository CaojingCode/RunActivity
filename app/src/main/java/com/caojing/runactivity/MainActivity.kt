package com.caojing.runactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.flyco.tablayout.listener.CustomTabEntity
import com.jijia.filemanagelibrary.activity.FileDirActivity
import com.jijia.kotlinlibrary.base.BaseActivity
import com.jijia.kotlinlibrary.base.CatchException
import com.jijia.kotlinlibrary.entity.AppState
import com.jijia.kotlinlibrary.utils.addData
import com.jijia.kotlinlibrary.utils.getData
import com.jijia.kotlinlibrary.utils.mmkv
import com.jijia.kotlinlibrary.utils.permissionRequest
import com.tamsiree.rxkit.RxWebViewTool
import com.tamsiree.rxkit.interfaces.OnWebViewLoad
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainActivity : AppBaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initView() {
        userViewModel.updateNavigation()
        userViewModel.navigationLiveData.observe(this, Observer {
            /**
             * 一行代码构建底部导航栏
             */
            tabLayout.setTabData(
                it["tabEntity"] as ArrayList<CustomTabEntity>,
                this,
                R.id.vpFragment,
                it["fragments"] as ArrayList<Fragment>
            )
            tabLayout.isTabSpaceEqual
        })


        /**
         * 一行代码实现网络请求
         */
        userViewModel.getUserList()
        userViewModel.liveData.observe(this, Observer {
            tvMessage.text = it.data?.toString()
//            if (it.state == AppState.LOADING)
//                loadingDialog.show()
        })


        userViewModel.numLiveData.observe(this, Observer {
            tvMessage.text = it.toString()
        })

        btnJump.setOnClickListener {
            permissionRequest(PermissionConstants.STORAGE) {
                ActivityUtils.startActivity(FileDirActivity::class.java)
            }
        }

        /**
         * 一行代码实现数据数据存储
         * 性能对比sp提高很多倍
         */
        mmkv.encode("sss", "bbbbbbbbb")
        ToastUtils.showShort(mmkv.getString("sss", "ccccccc"))


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
