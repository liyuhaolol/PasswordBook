package com.lyh.cn.passwordbook.activity.entry

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.lyh.cn.passwordbook.base.BaseActivity
import com.lyh.cn.passwordbook.databinding.ActivityInitBinding
import com.lyh.cn.passwordbook.fragment.entry.FingerprintFragment
import com.lyh.cn.passwordbook.fragment.entry.PasswordFragment
import com.lyh.cn.passwordbook.fragment.entry.UrlFragment
import spa.lyh.cn.fingerprintutils.FingerprintVerifyManager
import spa.lyh.cn.fingerprintutils.fp.IFingerprint

class InitActivity:BaseActivity() {
    private lateinit var b:ActivityInitBinding
    lateinit var fragmentPagerAdapter: FragmentStateAdapter

    val urlFragment = UrlFragment()
    val passwordFragment = PasswordFragment()
    val fingerprintFragment = FingerprintFragment()
    var fragmentList:ArrayList<Fragment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityInitBinding.inflate(layoutInflater)
        setContentView(b.root)
        initView()
    }

    private fun initView(){
        //判断列表是否要加入生物识别的部分
        fragmentList.add(urlFragment)
        fragmentList.add(passwordFragment)
        val type = FingerprintVerifyManager.canAuthenticate(this, false)
        if (type == IFingerprint.CAN_AUTHENTICATE){
            fragmentList.add(fingerprintFragment)
        }
        fragmentPagerAdapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return fragmentList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }
        b.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        b.vp.adapter = fragmentPagerAdapter
        b.vp.isUserInputEnabled = false
    }

    //调整页数
    fun changePage(index:Int){
        b.vp.currentItem = index
    }

    fun getFragmentSize():Int{
        return fragmentList.size
    }
}