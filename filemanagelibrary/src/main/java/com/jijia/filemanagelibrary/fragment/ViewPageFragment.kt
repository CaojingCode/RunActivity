package com.jijia.filemanagelibrary.fragment

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import com.jijia.filemanagelibrary.R
import com.jijia.filemanagelibrary.adapter.DirAdapter
import kotlinx.android.synthetic.main.fragment_layout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewPageFragment : Fragment() {

    var mTitle: String = ""
    private var dirAdapter = DirAdapter()

    companion object {
        fun getCodeFragment(title: String = ""): ViewPageFragment {
            val vf = ViewPageFragment()
            vf.mTitle = title
            return vf
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentView = inflater.inflate(R.layout.fragment_layout, null)

        initView(fragmentView.rvFileDir)
        return fragmentView
    }


    private fun initView(rvFileList: RecyclerView) {
        rvFileList.layoutManager = LinearLayoutManager(activity)
        rvFileList.adapter = dirAdapter
        dirAdapter.setOnItemClickListener { adapter, view, position ->
            ToastUtils.showLong(dirAdapter.data[position].name)
        }
        var suffix=".pdf"
        when (mTitle) {
            "MP4" -> {
                suffix=".mp4"
            }
            "PDF" -> {
                suffix=".pdf"
            }
            "Word" -> {
                suffix=".doc"
            }
            "Excel" -> {
                suffix=".xls"
            }
        }
        lifecycleScope.launch {

            var listFile = withContext(Dispatchers.IO) {
                FileUtils.listFilesInDirWithFilter(
                    Environment.getExternalStorageDirectory().absolutePath, {
                        it.name.endsWith(suffix)
                    }, true
                )
            }
            view?.loadingView?.visibility=View.GONE
            ToastUtils.showLong(listFile.size.toString())
            dirAdapter.setList(listFile)
        }
    }
}