package com.caojing.runactivity.fragment

import android.view.View
import com.caojing.runactivity.R
import com.jijia.kotlinlibrary.base.BaseFragment

class QuestionFragment:MainFragment() {
    companion object{
        val instant: QuestionFragment by lazy {
            QuestionFragment()
        }
    }
    override fun initView(view:View) {
    }

    override fun initData() {
    }

    override fun layoutId(): Int {
        return R.layout.fragment_question
    }
}