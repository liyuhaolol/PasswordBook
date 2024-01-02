package com.lyh.cn.passwordbook.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

open class BaseFragment:Fragment() {

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
            Toast.makeText(activity,text, Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun hideFragment(fragment:Fragment?,ft: FragmentTransaction){
        if (fragment != null && fragment.isAdded && !fragment.isHidden){
            ft.hide(fragment)
        }
    }
}