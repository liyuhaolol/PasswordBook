package com.lyh.cn.passwordbook.fragment.entry

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyh.cn.passwordbook.R
import com.lyh.cn.passwordbook.activity.MainActivity
import com.lyh.cn.passwordbook.activity.entry.InitActivity
import com.lyh.cn.passwordbook.base.BaseFragment
import com.lyh.cn.passwordbook.databinding.FragmentPwdBinding
import com.lyh.cn.passwordbook.databinding.FragmentUrlBinding
import com.lyh.cn.passwordbook.utils.MySpUtils

class PasswordFragment : BaseFragment(){
    private lateinit var b:FragmentPwdBinding
    private lateinit var activity: InitActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentPwdBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.next.setOnClickListener(View.OnClickListener {
            if (checkPwd()){
                if (activity.getFragmentSize() == 2){
                    MySpUtils.setFirstInit(activity,false)
                    val intent = Intent(activity,MainActivity::class.java)
                    startActivity(intent)
                    activity.finish()
                }else{
                    activity.changePage(2)
                }
            }
        })
        activity = requireActivity() as InitActivity
        if (activity.getFragmentSize() == 2){
            b.next.text = getString(R.string.finish)
        }else{
            b.next.text = getString(R.string.next)
        }
    }

    private fun checkPwd():Boolean{
        val p1 = b.pwdFirst.text.toString().trim()
        val p2 = b.pwdSecond.text.toString().trim()
        if (TextUtils.isEmpty(p1)){
            showToast("密码不能为空")
            return false
        }

        if (p1 != p2){
            showToast("两次密码不一样")
            return false
        }
        MySpUtils.setPwd(requireActivity(),p1)
        return true
    }

}