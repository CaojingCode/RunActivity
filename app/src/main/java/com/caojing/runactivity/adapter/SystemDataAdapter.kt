package com.caojing.runactivity.adapter

import com.caojing.runactivity.R
import com.caojing.runactivity.entity.SystemDataBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class SystemDataAdapter(private var isOneMenu: Boolean) :
    BaseQuickAdapter<SystemDataBean, BaseViewHolder>(R.layout.item_system_layout) {
    override fun convert(helper: BaseViewHolder, item: SystemDataBean) {
        if (!this.isOneMenu) {
            helper.setBackgroundColor(
                R.id.tvSystemName,
                mContext.resources.getColor(R.color.Gray80_25)
            )
        } else{
            if (item.isSelect){
                helper.setBackgroundColor(
                    R.id.tvSystemName,
                    mContext.resources.getColor(R.color.Gray80_25))
            }else{
                helper.setBackgroundColor(
                    R.id.tvSystemName,
                    mContext.resources.getColor(R.color.qmui_config_color_white))
            }
        }
        helper.setText(R.id.tvSystemName, item.name)
    }
}