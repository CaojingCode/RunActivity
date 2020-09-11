package com.caojing.runactivity.adapter

import com.caojing.runactivity.R
import com.caojing.runactivity.entity.MenuData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class MenuAdapter :BaseQuickAdapter<MenuData, BaseViewHolder>(R.layout.item_menu){
    override fun convert(holder: BaseViewHolder, item: MenuData) {
        holder.setText(R.id.tvMenuName,item.menuName)
    }
}