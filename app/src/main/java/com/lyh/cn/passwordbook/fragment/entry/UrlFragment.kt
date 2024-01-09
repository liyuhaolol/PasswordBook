package com.lyh.cn.passwordbook.fragment.entry

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyh.cn.passwordbook.activity.entry.InitActivity
import com.lyh.cn.passwordbook.base.BaseFragment
import com.lyh.cn.passwordbook.databinding.FragmentUrlBinding
import com.lyh.cn.passwordbook.utils.AesGcmCipher
import com.lyh.cn.passwordbook.utils.MySpUtils

class UrlFragment : BaseFragment(){
    private lateinit var b:FragmentUrlBinding
    private var typeMark = 0//0是url1是本地

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentUrlBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.skip.setOnClickListener(View.OnClickListener {
            if (checkAES()){
                val activity = requireActivity() as InitActivity
                activity.changePage(1)
            }
        })
        b.key.setText(AesGcmCipher.createAesKey())
        b.gen.setOnClickListener(View.OnClickListener {
            b.key.setText(AesGcmCipher.createAesKey())
        })
        b.localArea.visibility = View.GONE
        b.useUrl.setOnClickListener(View.OnClickListener {
            b.webArea.visibility = View.VISIBLE
            b.localArea.visibility = View.GONE
            typeMark = 0
        })
        b.useLocal.setOnClickListener(View.OnClickListener {
            b.webArea.visibility = View.GONE
            b.localArea.visibility = View.VISIBLE
            typeMark = 1
        })
    }

    private fun checkAES():Boolean{
        val key = b.key.text.toString()
        if (TextUtils.isEmpty(key)){
            showToast("密钥不能为空")
            return false
        }else{
            MySpUtils.setAesKey(requireActivity(),key)
            return true
        }
    }
}