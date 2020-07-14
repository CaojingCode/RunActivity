package com.caojing.runactivity

import android.app.AppComponentFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jijia.kotlinlibrary.base.BaseActivity
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.app_fragment.*
import kotlinx.android.synthetic.main.app_fragment.view.*
import kotlinx.android.synthetic.main.app_fragment.view.btnFragment

class CodeFragment : Fragment() {

    var mTitle: String=""
    lateinit var myActivity:AppCompatActivity

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myActivity=activity as MainActivity
        userViewModel= ViewModelProvider(myActivity).get(UserViewModel::class.java)
    }

    companion object {
        fun getCodeFragment(title: String = ""): CodeFragment {
            val sf = CodeFragment()
            sf.mTitle = title
            return sf
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.app_fragment, null)
        view.tvName.text = mTitle
        view.btnFragment.onClick {
            userViewModel.MyTimerTask()
        }

        userViewModel.numLiveData.observe(myActivity, Observer {
            view.tvName.text=it.toString()
        })
        return view
    }



}