package com.caojing.runactivity.adapter

import com.caojing.runactivity.R
import com.caojing.runactivity.entity.ArticleBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

open class TopArticleAdapter :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_article_layout) {
    override fun convert(holder: BaseViewHolder, item: ArticleBean) {
        holder.setText(R.id.tvTittle, item.title)
        holder.setText(R.id.tvName, item.author + "     " + item.niceDate)

        holder.setGone(R.id.viewLine, holder.layoutPosition == data.size)
    }
}