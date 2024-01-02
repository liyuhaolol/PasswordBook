package com.lyh.cn.passwordbook.fragment.entry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyh.cn.passwordbook.base.BaseFragment
import com.lyh.cn.passwordbook.databinding.FragmentFingerBinding
import com.lyh.cn.passwordbook.databinding.FragmentUrlBinding

class FingerprintFragment:BaseFragment() {
    private lateinit var b: FragmentFingerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentFingerBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onResume() {
        super.onResume()
        Log.e("qwer","FingerprintFragment -> onResume")
    }
}