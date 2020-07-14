package com.caojing.runactivity

import android.app.Application
import com.jijia.kotlinlibrary.base.BaseApplication
import com.jijia.kotlinlibrary.net.RetrofitManage

class AndroidApplication :BaseApplication(){

    override fun onCreate() {
        super.onCreate()
    }

    override fun getBaseUrl(): String {
        return "https://api.github.com/"
    }
}