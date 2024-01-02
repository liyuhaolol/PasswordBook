package com.lyh.cn.passwordbook.fragment.entry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyh.cn.passwordbook.base.BaseFragment
import com.lyh.cn.passwordbook.databinding.FragmentPwdBinding
import com.lyh.cn.passwordbook.databinding.FragmentUrlBinding

class PasswordFragment : BaseFragment(){
    private lateinit var b:FragmentPwdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentPwdBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onResume() {
        super.onResume()
        Log.e("qwer","PasswordFragment -> onResume")
    }
}