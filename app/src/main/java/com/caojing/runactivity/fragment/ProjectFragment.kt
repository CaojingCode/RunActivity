package com.caojing.runactivity.fragment

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.View
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import com.caojing.runactivity.R
import com.otaliastudios.transcoder.Transcoder
import com.otaliastudios.transcoder.TranscoderListener
import com.otaliastudios.transcoder.source.UriDataSource
import com.otaliastudios.transcoder.strategy.DefaultVideoStrategy
import kotlinx.android.synthetic.main.fragment_account.view.*


class ProjectFragment : MainFragment() {

    lateinit var fragmentView: View

    var videoPath=""

    companion object {
        val instant: ProjectFragment by lazy {
            ProjectFragment()
        }
    }



    override fun initView(view: View) {
        fragmentView = view
        view.btnSelectVideo.setOnClickListener {
            //选择视频
            startActivityForResult(
                Intent(Intent.ACTION_OPEN_DOCUMENT)
                    .addCategory(Intent.CATEGORY_OPENABLE)
                    .setType("*/*"),
                1000
            )
        }
    }

    override fun initData() {
        videoPath =
            context?.getExternalFilesDir("")?.absolutePath + "/videos/1234566.mp4" //视频文件路径
    }

    override fun layoutId(): Int {
        return R.layout.fragment_account
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1000) {
            var uri: Uri? = data?.data ?: return
            if (uri != null) {
                FileUtils.createOrExistsFile(videoPath)
                Transcoder.into(videoPath)
                    .addDataSource(UriDataSource(mActivity, uri))
                    .setVideoTrackStrategy(mTranscodeVideoStrategy)
                    .setListener(object : TranscoderListener {
                        override fun onTranscodeCompleted(successCode: Int) {
                            //压缩成功
                            ToastUtils.showShort("压缩成功")
                            fragmentView.loadingView.visibility = View.GONE
                        }

                        override fun onTranscodeProgress(progress: Double) {
                            //压缩中
                            fragmentView.loadingView.visibility = View.VISIBLE
                        }

                        override fun onTranscodeCanceled() {
                            //压缩取消
                            ToastUtils.showShort("压缩取消")
                            fragmentView.loadingView.visibility = View.GONE
                        }

                        override fun onTranscodeFailed(exception: Throwable) {
                            //压缩失败
                            ToastUtils.showShort("压缩失败" + exception.message)
                            fragmentView.loadingView.visibility = View.GONE
                        }

                    })
                    .transcode()
            }
        }
    }

    var mTranscodeVideoStrategy = DefaultVideoStrategy.Builder()
        .frameRate(30)
        .build()
}