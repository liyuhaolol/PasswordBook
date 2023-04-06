package com.lyh.cn.passwordbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lyh.cn.passwordbook.adapter.PwdAdapter;
import com.lyh.cn.passwordbook.greendao.DaoMaster;
import com.lyh.cn.passwordbook.greendao.DaoSession;
import com.lyh.cn.passwordbook.greendao.PwdInfoDao;
import com.lyh.cn.passwordbook.model.PwdInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DaoSession daoSession;
    private PwdInfoDao pwdInfoDao;

    private Button btn;

    private RecyclerView recy;

    private PwdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();
        initView();
    }

    private void initDB(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "pwd.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        pwdInfoDao = daoSession.getPwdInfoDao();
    }

    private void initView(){
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPwdInfo();
            }
        });
        List<PwdInfo> list = pwdInfoDao.queryBuilder().limit(5).build().list();
        recy = findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PwdAdapter(list);
        recy.setAdapter(adapter);
    }

    private void addPwdInfo(){
        PwdInfo pwdInfo = new PwdInfo();
        pwdInfo.setEmail("test@qq.com");
        pwdInfo.setHasPhone(true);
        pwdInfo.setPassword("12345");
        pwdInfo.setRemarks("hello");
        pwdInfo.setWebSiteName("百度");
        pwdInfo.setWebSiteUrl("https://www.baidu.com");
        pwdInfoDao.insert(pwdInfo);
    }
}