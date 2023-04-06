package com.lyh.cn.passwordbook.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lyh.cn.passwordbook.R
import com.lyh.cn.passwordbook.model.PwdInfo
import org.w3c.dom.Text

class PwdAdapter (list:MutableList<PwdInfo>) :
    BaseQuickAdapter<PwdInfo, BaseViewHolder>(R.layout.item_pwd,list){

    override fun convert(holder: BaseViewHolder, item: PwdInfo) {
        var site:TextView = holder.getView(R.id.site)
        var email:TextView = holder.getView(R.id.email);
        site.text = item.webSiteName
        email.text = item.email
    }
}