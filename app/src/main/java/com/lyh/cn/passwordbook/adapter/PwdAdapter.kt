package com.lyh.cn.passwordbook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chad.library.adapter4.BaseQuickAdapter
import com.lyh.cn.passwordbook.databinding.ItemPwdBinding
import com.lyh.cn.passwordbook.model.PwdInfo

class PwdAdapter (list:MutableList<PwdInfo>) :
    BaseQuickAdapter<PwdInfo, PwdAdapter.PwdInfoVH>(list){

    class PwdInfoVH(val b:ItemPwdBinding):ViewHolder(b.root)

    override fun onBindViewHolder(holder: PwdInfoVH, position: Int, item: PwdInfo?) {
        holder.b.site.text = item!!.webSiteName
        holder.b.email.text = item.email
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): PwdInfoVH {
        return PwdInfoVH(ItemPwdBinding.inflate(LayoutInflater.from(context),parent,false))
    }

}