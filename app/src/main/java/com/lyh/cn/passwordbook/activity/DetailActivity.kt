package com.lyh.cn.passwordbook.activity

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

class DetailActivity:BaseActivity() {
    private lateinit var b:ActivityDetailBinding
    private var pwdInfo: PwdInfo? = null
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
        }

        b.btn.setOnClickListener(View.OnClickListener {
            if (reslutCode == MainActivity.ADD){
                //添加
                addData()
            }else{
                //修改
            }
        })
    }

    private fun addData(){
        val data = PwdInfo()
        data.webSiteName = b.platform.text.toString().trim()
        data.webSiteUrl = b.url.text.toString().trim()
        data.email = b.email.text.toString().trim()
        data.phone = b.phone.text.toString().trim()
        data.password = b.password.text.toString().trim()
        data.remarks = b.remark.text.toString().trim()
        if (TextUtils.isEmpty(data.webSiteName)){
            showToast("平台名称不能为空")
            return
        }
        pwdInfoDao.insert(data)
        setResult(reslutCode)
        finish()
    }
}