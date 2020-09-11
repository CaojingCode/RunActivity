package com.caojing.runactivity.fragment

import android.os.Bundle
import com.caojing.runactivity.activity.MainActivity
import com.jijia.kotlinlibrary.base.BaseFragment

abstract class MainFragment : BaseFragment() {
    lateinit var mActivity: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivity = activity as MainActivity
    }
}