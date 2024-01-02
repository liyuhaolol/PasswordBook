package com.lyh.cn.passwordbook.activity.entry;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lyh.cn.passwordbook.activity.MainActivity;
import com.lyh.cn.passwordbook.activity.entry.InitActivity;
import com.lyh.cn.passwordbook.activity.entry.LoginActivity;
import com.lyh.cn.passwordbook.base.BaseActivity;
import com.lyh.cn.passwordbook.utils.MySpUtils;

import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;
//转跳
public class EntryActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TranslucentUtils.setTranslucentBoth(getWindow());
/*        if (!MySpUtils.getFirstInit(this)){
            //初次启动，显示配置页面
            Intent intent = new Intent(this, InitActivity.class);
            startActivity(intent);
        }else {
            //去登录页
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }*/
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
