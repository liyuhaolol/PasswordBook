package com.lyh.cn.passwordbook.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter4.BaseQuickAdapter;
import com.lyh.cn.passwordbook.R;
import com.lyh.cn.passwordbook.adapter.PwdAdapter;
import com.lyh.cn.passwordbook.base.BaseActivity;
import com.lyh.cn.passwordbook.greendao.DaoMaster;
import com.lyh.cn.passwordbook.greendao.DaoSession;
import com.lyh.cn.passwordbook.greendao.PwdInfoDao;
import com.lyh.cn.passwordbook.model.PwdInfo;
import com.lyh.cn.passwordbook.utils.AesGcmCipher;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;

public class MainActivity extends BaseActivity {
    private DaoSession daoSession;
    private PwdInfoDao pwdInfoDao;

    private Button btn;

    private RecyclerView recy;

    private PwdAdapter adapter;

    private List<PwdInfo> list;

    private String key = "rkZOySD40htpnZPSxcxh/Ys6YJhgpZ18dc+AvbWVCmA=";
    private String encrypt = "AAAAAAAAAAAAAAAAZz/SXag8CewnkrcusO/dkYxN2Dge/A==";

    private ActivityResultLauncher<Intent> launcher;

    public final static int CHANGE = 110;//修改已有数据
    public final static int ADD = 120;//添加数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TranslucentUtils.setTranslucentBoth(getWindow());
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
        DrawerLayout draw = findViewById(R.id.draw);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addPwdInfo();
                draw.openDrawer(GravityCompat.START);
            }
        });
        list = new ArrayList<>();
        initList();
        recy = findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PwdAdapter(list);
        recy.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<PwdInfo>() {
            @Override
            public void onClick(@NonNull BaseQuickAdapter<PwdInfo, ?> baseQuickAdapter, @NonNull View view, int i) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("index",i);
                intent.putExtra("pwdInfo",list.get(i));
                launcher.launch(intent);
            }
        });
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result != null){
                            switch (result.getResultCode()){
                                case ADD:
                                    initList();
                                    adapter.notifyDataSetChanged();
                                    break;
                                case CHANGE:
                                    if (result.getData() != null){
                                        int index = result.getData().getIntExtra("index",-1);
                                        if (index >= 0){
                                            PwdInfo pwd = result.getData().getParcelableExtra("pwdInfo");
                                            if (pwd != null){
                                                list.get(index).setId(pwd.getId());
                                                list.get(index).setWebSiteName(pwd.getWebSiteName());
                                                list.get(index).setWebSiteUrl(pwd.getWebSiteUrl());
                                                list.get(index).setEmail(pwd.getEmail());
                                                list.get(index).setPhone(pwd.getPhone());
                                                list.get(index).setUserName(pwd.getUserName());
                                                list.get(index).setPassword(pwd.getPassword());
                                                list.get(index).setRemarks(pwd.getRemarks());
                                                adapter.notifyItemChanged(index);
                                            }
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                });
    }

    private void addPwdInfo(){
/*        PwdInfo pwdInfo = new PwdInfo();
        pwdInfo.setEmail("test@qq.com");
        pwdInfo.setPhone("17600575050");
        pwdInfo.setPassword("12345");
        pwdInfo.setRemarks("hello");
        pwdInfo.setWebSiteName("百度");
        pwdInfo.setWebSiteUrl("https://www.baidu.com");
        pwdInfoDao.insert(pwdInfo);*/
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        launcher.launch(intent);
    }

    private void initList(){
        list.clear();
        list.addAll(pwdInfoDao.queryBuilder().limit(5).build().list());
    }

    private void aesTest(){
        //Log.e("qwer", AesGcmCipher.createAesKey());
        //Log.e("qwer", AesGcmCipher.doEncrypt("你好",key));
        Log.e("qwer",AesGcmCipher.doDecrypt(encrypt,key));
    }
}