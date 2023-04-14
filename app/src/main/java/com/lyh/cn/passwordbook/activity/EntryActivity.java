package com.lyh.cn.passwordbook.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;
//转跳
public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TranslucentUtils.setTranslucentBoth(getWindow());
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
