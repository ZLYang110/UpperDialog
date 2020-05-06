package com.zlyandroid.upperdialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.zlyandroid.upperdialog.utils.PermissionUtils;
import com.zlyandroid.upperdialog.utils.ToastUtils;
import com.zlylib.mypermissionlib.RequestListener;
import com.zlylib.mypermissionlib.RuntimeRequester;
import com.zlyandroid.upperdialog.utils.dialog.DownloadDialog;
import com.zlylib.upperdialog.ListDialog;
import com.zlylib.upperdialog.LoadingDialog;
import com.zlylib.upperdialog.TipDialog;
import com.zlylib.upperdialog.listener.SimpleCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_life)
    RecyclerView lifeIndexRecyclerView;

    @BindView(R.id.recyclerView_test)
    RecyclerView testIndexRecyclerView;

    private List<String> lifeIndices;
    private List<String> testIndices;

    private LifeIndexAdapter lifeIndexAdapter;
    private LifeIndexAdapter lifeIndexAdapter2;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);


        lifeIndexRecyclerView.setNestedScrollingEnabled(false);
        lifeIndexRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        lifeIndices = new ArrayList<>();
        lifeIndices.add("dialog");
        lifeIndices.add("Toast");
        lifeIndices.add("Popup");
        lifeIndices.add("Float");
        lifeIndexAdapter = new LifeIndexAdapter(this, lifeIndices);
        lifeIndexAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://dialog
                        startActivity(new Intent(MainActivity.this, DialogActivity.class));
                        break;
                    case 1://Toast
                        startActivity(new Intent(MainActivity.this, ToastActivity.class));
                       // Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
                        break;
                    case 2://Popup
                        ToastUtils.show(MainActivity.this,"待开发");
                       // Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
                        break;
                    case 3://Float
                        ToastUtils.show(MainActivity.this,"待开发");
                      //  Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        lifeIndexRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lifeIndexRecyclerView.setAdapter(lifeIndexAdapter);


        testIndexRecyclerView.setNestedScrollingEnabled(false);
        testIndexRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        testIndices = new ArrayList<>();
        testIndices.add("LoadingDialog");
        testIndices.add("TipDialog");
        testIndices.add("ListDialog");
        testIndices.add("DownloadDialog");
        lifeIndexAdapter2 = new LifeIndexAdapter(this, testIndices);
        lifeIndexAdapter2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://LoadingDialog
                        Dialog dialog = LoadingDialog.createLoadingDialog(MainActivity.this, "请稍后...");
                        dialog.show();
                        App.sHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 2000);
                        break;
                    case 1://TipDialog
                        TipDialog.with(MainActivity.this)
                                .message("你觉得好看么？")
                                .onYes(new SimpleCallback<Void>() {
                                    @Override
                                    public void onResult(Void data) {

                                    }
                                })
                                .show();
                        break;
                    case 2://ListDialog
                        List<String> lisStr=new ArrayList<>();
                        lisStr.add("选项1");
                        lisStr.add("选项2");
                        lisStr.add("选项3");
                        lisStr.add("选项4");

                        ListDialog.with(MainActivity.this)
                                .cancelable(true)
//                        .title("列表动画")
                                .datas(lisStr)
                                .currSelectPos(1)
                                .listener(new ListDialog.OnItemSelectedListener() {
                                    @Override
                                    public void onSelect(String data, int pos) {
                                        Log.d("selectStr",lisStr.get(pos));
                                        Toast.makeText(MainActivity.this,lisStr.get(pos),Toast.LENGTH_LONG).show();
                                    }
                                })
                                .show();
                        break;
                    case 3://DownloadDialog
                        //https://github.com/ZLYang110/MyPermission/raw/master/app/release/app-release.apk
                        //https://github.com/goweii/WanAndroid/releases/download/1.4.6/per.goweii.wanandroid-v1.4.6.apk
                        //https://github.com/xing16/WanAndroid/raw/1b009f4c331ba40e57d6ac25e65f552a2a4c4223/app/release/app-release.apk
                        //https://pdds-cdn.uc.cn/27-0/QuarkBrowser/2004/a918c565822ca56db5d5b3602b635ba1/QuarkBrowser_V4.1.0.132_android_pf3300_(Build200428142217).apk?auth_key=1589333697-0-0-a5e5c76b8bfc2a22c261511c3a3befac&SESSID=c0d03eea699479f67804585998605065
                        download("","https://pdds-cdn.uc.cn/27-0/QuarkBrowser/2004/a918c565822ca56db5d5b3602b635ba1/QuarkBrowser_V4.1.0.132_android_pf3300_(Build200428142217).apk?auth_key=1589333697-0-0-a5e5c76b8bfc2a22c261511c3a3befac&SESSID=c0d03eea699479f67804585998605065","",false);
                        break;
                }
            }
        });
        testIndexRecyclerView.setItemAnimator(new DefaultItemAnimator());
        testIndexRecyclerView.setAdapter(lifeIndexAdapter2);
    }

    private static final int REQ_CODE_PERMISSION = 1;
    private RuntimeRequester mRuntimeRequester;
    private void download(final String versionName, final String url, final String urlBackup, final boolean isForce) {
        mRuntimeRequester = PermissionUtils.request(new RequestListener() {
            @Override
            public void onSuccess() {
                DownloadDialog.with(MainActivity.this, isForce, url);
            }

            @Override
            public void onFailed() {
            }
        }, MainActivity.this, REQ_CODE_PERMISSION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mRuntimeRequester != null) {
            mRuntimeRequester.onActivityResult(requestCode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
