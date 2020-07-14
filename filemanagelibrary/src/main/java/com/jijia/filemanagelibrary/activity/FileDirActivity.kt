package com.jijia.filemanagelibrary.activity

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.jijia.filemanagelibrary.R
import com.jijia.filemanagelibrary.adapter.DirAdapter
import com.jijia.filemanagelibrary.fragment.ViewPageFragment
import kotlinx.android.synthetic.main.filedir_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileFilter


/**
 * 文件夹分类页面
 */
class FileDirActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filedir_activity)
        initView()
    }

    private fun initView() {
        var array = arrayOf("MP4", "PDF", "Word", "Excel")
        slidingTabLayout.setViewPager(
            vpFragment,
            array,
            this,
            arrayListOf(
                ViewPageFragment.getCodeFragment(array[0]),
                ViewPageFragment.getCodeFragment(array[1]),
                ViewPageFragment.getCodeFragment(array[2]),
                ViewPageFragment.getCodeFragment(array[3])
            )
        )

    }

}