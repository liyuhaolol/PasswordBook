package com.lyh.cn.passwordbook.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.lyh.cn.passwordbook.R
import com.lyh.cn.passwordbook.base.BaseActivity
import com.lyh.cn.passwordbook.databinding.ActivityDetailBinding
import com.lyh.cn.passwordbook.greendao.DaoMaster
import com.lyh.cn.passwordbook.greendao.DaoMaster.DevOpenHelper
import com.lyh.cn.passwordbook.greendao.DaoSession
import com.lyh.cn.passwordbook.greendao.PwdInfoDao
import com.lyh.cn.passwordbook.model.PwdInfo
import com.lyh.cn.passwordbook.utils.MySpUtils

class DetailActivity:BaseActivity() {
    private lateinit var b:ActivityDetailBinding
    private var pwdInfo: PwdInfo? = null
    private var index = 0
    private var reslutCode:Int = 0
    private lateinit var daoSession: DaoSession
    private lateinit var pwdInfoDao: PwdInfoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(b.root)
        initView()
    }

    private fun initView(){
        pwdInfo = intent.getParcelableExtra("pwdInfo")
        index = intent.getIntExtra("index",0)
        val devOpenHelper = DevOpenHelper(applicationContext, "pwd.db", null)
        val daoMaster = DaoMaster(devOpenHelper.writableDb)
        daoSession = daoMaster.newSession()
        pwdInfoDao = daoSession.getPwdInfoDao()
        if (pwdInfo == null){
            //没有传递数据，那应该是添加
            b.btn.text = getString(R.string.add)
            reslutCode = MainActivity.ADD
        }else{
            //有默认数据，开始显示
            b.btn.text = getString(R.string.change)
            reslutCode = MainActivity.CHANGE
            b.email.setText(pwdInfo!!.email)
            b.phone.setText(pwdInfo!!.phone)
            b.remark.setText(pwdInfo!!.remarks)
            b.platform.setText(pwdInfo!!.webSiteName)
            b.url.setText(pwdInfo!!.webSiteUrl)
            b.username.setText(pwdInfo!!.userName)
            b.password.setText(pwdInfo!!.password)
        }

        b.btn.setOnClickListener(View.OnClickListener {
            if (reslutCode == MainActivity.ADD){
                //添加
                addData()
            }else{
                //修改
                changeData()
            }
        })
    }

    private fun addData(){
        val data = PwdInfo()
        data.webSiteName = b.platform.text.toString().trim()
        data.webSiteUrl = b.url.text.toString().trim()
        data.email = b.email.text.toString().trim()
        data.phone = b.phone.text.toString().trim()
        data.userName = b.username.text.toString().trim()
        data.password = b.password.text.toString().trim()
        data.remarks = b.remark.text.toString().trim()
        if (TextUtils.isEmpty(data.webSiteName)){
            showToast("平台名称不能为空")
            return
        }
        pwdInfoDao.insert(data)
        MySpUtils.setVersion(this,System.currentTimeMillis().toString())
        setResult(reslutCode)
        finish()
    }

    private fun changeData(){
        val data = PwdInfo()
        data.id = pwdInfo!!.id
        data.webSiteName = b.platform.text.toString().trim()
        data.webSiteUrl = b.url.text.toString().trim()
        data.email = b.email.text.toString().trim()
        data.phone = b.phone.text.toString().trim()
        data.userName = b.username.text.toString().trim()
        data.password = b.password.text.toString().trim()
        data.remarks = b.remark.text.toString().trim()
        if (TextUtils.isEmpty(data.webSiteName)){
            showToast("平台名称不能为空")
            return
        }
        pwdInfoDao.update(data)
        MySpUtils.setVersion(this,System.currentTimeMillis().toString())
        val intent = Intent()
        intent.putExtra("index", index)
        intent.putExtra("pwdInfo",data)
        setResult(reslutCode,intent)
        finish()
    }
}