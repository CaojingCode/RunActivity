package com.caojing.runactivity.activity

import com.blankj.utilcode.util.ActivityUtils
import com.caojing.runactivity.R
import com.jijia.kotlinlibrary.base.BaseActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : BaseActivity() {
    override fun layoutResID(): Int {
        return R.layout.activity_start
    }

    override fun initView() {
        titleBarHide()

        MainScope().launch {
            delay(3000)
            ActivityUtils.startActivity(MainActivity::class.java)
            finish()
        }
    }

    override fun initData() {
    }
}