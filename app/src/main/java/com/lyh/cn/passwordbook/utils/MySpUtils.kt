package com.lyh.cn.passwordbook.utils

import android.content.Context
import spa.lyh.cn.lib_utils.SPUtils

object MySpUtils {
    private val firstInit = "init"
    private val aesKey = "aesKey"
    private val pwd = "pwd"
    private val version = "version"

    @JvmStatic
    fun getFirstInit(context: Context):Boolean{
        return SPUtils.getBoolean(firstInit,true,context)
    }

    @JvmStatic
    fun setFirstInit(context: Context,value:Boolean){
        SPUtils.putBoolean(firstInit,value,context)
    }

    @JvmStatic
    fun getAesKey(context: Context):String{
        return SPUtils.getString(aesKey,"",context)
    }

    @JvmStatic
    fun setAesKey(context: Context,key:String){
        SPUtils.putString(aesKey,key,context)
    }

    @JvmStatic
    fun getPwd(context: Context):String{
        return SPUtils.getString(pwd,"",context)
    }

    @JvmStatic
    fun setPwd(context: Context,key:String){
        SPUtils.putString(pwd,key,context)
    }

    @JvmStatic
    fun getVersion(context: Context):String{
        return SPUtils.getString(version,"",context)
    }

    @JvmStatic
    fun setVersion(context: Context,ver:String){
        SPUtils.putString(version,ver,context)
    }
}