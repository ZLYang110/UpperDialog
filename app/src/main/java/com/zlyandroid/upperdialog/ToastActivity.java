package com.zlyandroid.upperdialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zlyandroid.upperdialog.utils.ToastUtils;
import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.common.AnimatorHelper;
import com.zlylib.upperdialog.dialog.DialogLayer;
import com.zlylib.upperdialog.manager.Layer;
import com.zlylib.upperdialog.view.DragLayout;
import com.zlylib.upperdialog.view.UpperActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener {


    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        unbinder = ButterKnife.bind(this);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

   @OnClick({
           R.id.tv_show,
           R.id.tv_show_success,
           R.id.tv_show_fail,
           R.id.tv_show_normal,


   })
    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.tv_show:
               ToastUtils.show(ToastActivity.this,"轻量级浮层弹窗");
               break;
           case R.id.tv_show_success:
               ToastUtils.showSuccess(ToastActivity.this,"成功了");
               break;
           case R.id.tv_show_fail:
               ToastUtils.showFail(ToastActivity.this,"失败了");
               break;
           case R.id.tv_show_normal:
               ToastUtils.showNormal(ToastActivity.this,"测试");
               break;

       }
    }
}
