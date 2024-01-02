package com.lyh.cn.passwordbook.activity.entry

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.lyh.cn.passwordbook.activity.MainActivity
import com.lyh.cn.passwordbook.base.BaseActivity
import com.lyh.cn.passwordbook.databinding.ActivityLoginBinding

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
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}