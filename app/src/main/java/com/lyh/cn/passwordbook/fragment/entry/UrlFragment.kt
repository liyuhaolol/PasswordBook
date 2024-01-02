package com.lyh.cn.passwordbook.fragment.entry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyh.cn.passwordbook.activity.entry.InitActivity
import com.lyh.cn.passwordbook.base.BaseFragment
import com.lyh.cn.passwordbook.databinding.FragmentUrlBinding

class UrlFragment : BaseFragment(){
    private lateinit var b:FragmentUrlBinding

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
            val activity = requireActivity() as InitActivity
            activity.changePage(1)
        })
    }
    override fun onResume() {
        super.onResume()
        Log.e("qwer","UrlFragment -> onResume")
    }
}