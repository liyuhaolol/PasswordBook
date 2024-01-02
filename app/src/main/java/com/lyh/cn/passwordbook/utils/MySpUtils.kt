package com.lyh.cn.passwordbook.utils

import android.content.Context
import spa.lyh.cn.lib_utils.SPUtils

object MySpUtils {
    private val firstInit = "init"

    @JvmStatic
    fun getFirstInit(context: Context):Boolean{
        return SPUtils.getBoolean(firstInit,true,context)
    }
}