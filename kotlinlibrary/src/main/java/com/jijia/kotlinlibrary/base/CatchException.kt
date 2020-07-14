package com.jijia.kotlinlibrary.base

import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.jijia.kotlinlibrary.utils.ExceptionUtil


object CatchException {
     suspend fun start(block: suspend() -> Unit) :Boolean{
        try {
            block()
            return true
        } catch (e: Exception) {
            LogUtils.d("BaseAndroid",e.message)
            ExceptionUtil.catchException(e)
        }
         return false
    }
}