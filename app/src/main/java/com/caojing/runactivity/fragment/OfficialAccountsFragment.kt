package com.caojing.runactivity.fragment

import android.view.View
import com.caojing.runactivity.R
import com.jijia.kotlinlibrary.base.BaseFragment

class OfficialAccountsFragment:MainFragment() {

    companion object{
        val instant: OfficialAccountsFragment by lazy {
            OfficialAccountsFragment()
        }
    }
    override fun initView(view:View) {
    }

    override fun initData() {
    }

    override fun layoutId(): Int {
        return R.layout.fragment_account
    }
}