package com.jijia.filemanagelibrary.adapter

import com.blankj.utilcode.util.FileUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jijia.filemanagelibrary.R
import java.io.File

/**
 * 文件夹管理适配器
 */
class DirAdapter :BaseQuickAdapter<File,BaseViewHolder>(R.layout.dir_item_layout){
    override fun convert(holder: BaseViewHolder, item: File) {
        holder.setText(R.id.tvName,item.name)
        holder.setText(R.id.tvFileDir, FileUtils.getDirName(item))

    }
}