package com.jijia.kotlinlibrary.base

import android.content.res.Resources
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.jijia.kotlinlibrary.BuildConfig
import com.jijia.kotlinlibrary.utils.fullScreen
import com.jijia.kotlinlibrary.utils.loadingDialog
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import me.jessyan.autosize.AutoSizeCompat
import me.jessyan.autosize.AutoSizeConfig

abstract class BaseActivity : AppCompatActivity() {
    lateinit var loadingDialog: QMUITipDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = loadingDialog()

        /**
         * 一行代码实现全屏
         */
        fullScreen()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initView()
        initData()
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        initView()
        initData()
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        super.setContentView(view, params)
        initView()
        initData()
    }

    protected abstract fun initView()
    protected abstract fun initData()

    override fun getResources(): Resources {
        if (Looper.getMainLooper().thread == Thread.currentThread()) AutoSizeCompat.autoConvertDensity(
            super.getResources(), BuildConfig.DESIGNHEIGHT.toFloat(), AutoSizeConfig.getInstance().screenWidth > AutoSizeConfig.getInstance().screenHeight
        )

        return super.getResources()
    }
}