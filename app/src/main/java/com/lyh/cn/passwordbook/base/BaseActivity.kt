package com.lyh.cn.passwordbook.base

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import spa.lyh.cn.lib_utils.translucent.TranslucentUtils
import spa.lyh.cn.peractivity.PermissionActivity

open class BaseActivity:PermissionActivity() {

    override fun getResources(): Resources {
        val config = Configuration()
        config.setToDefaults()
        val context:Context = createConfigurationContext(config)
        return context.resources
    }

    fun hideFragment(fragment:Fragment?,ft: FragmentTransaction){
        if (fragment != null && fragment.isAdded && !fragment.isHidden){
            ft.hide(fragment)
        }
    }

    fun showToast(content:Any){
        try {
            var text:String
            if (content is Int || content is Boolean){
                text = "$content"
            }else if (content is String){
                text = content
            }else{
                text = content.toString()
            }
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}