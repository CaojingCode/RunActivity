package com.caojing.runactivity

import com.jijia.kotlinlibrary.base.BaseApplication

class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun getBaseUrl(): String {
        return "https://api.github.com/"
    }
}