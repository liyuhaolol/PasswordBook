package com.lyh.cn.passwordbook.activity.entry

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lyh.cn.passwordbook.activity.MainActivity
import com.lyh.cn.passwordbook.base.BaseActivity
import com.lyh.cn.passwordbook.databinding.ActivityLoginBinding
import com.lyh.cn.passwordbook.utils.MySpUtils

class LoginActivity : BaseActivity(){
    lateinit var b:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)
        initView()
    }

    private fun initView(){
        b.login.setOnClickListener(View.OnClickListener {
            if (checkPwd()){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun checkPwd():Boolean{
        val realPwd = MySpUtils.getPwd(this)
        val pwd = b.pwd.text.toString().trim()
        return if (realPwd == pwd){
            true
        }else{
            showToast("密码有误")
            false
        }
    }
}