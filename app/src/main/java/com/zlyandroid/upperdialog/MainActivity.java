package com.zlyandroid.upperdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.zlylib.upperdialog.ListDialog;
import com.zlylib.upperdialog.LoadingDialog;
import com.zlylib.upperdialog.TipDialog;
import com.zlylib.upperdialog.Upper;
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
                        Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
                        break;
                    case 2://Popup
                        Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
                        break;
                    case 3://Float
                        Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_LONG).show();
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
                                .message("确定要清除缓存吗？")
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

                }
            }
        });
        testIndexRecyclerView.setItemAnimator(new DefaultItemAnimator());
        testIndexRecyclerView.setAdapter(lifeIndexAdapter2);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
