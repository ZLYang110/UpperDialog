package com.zlylib.upperdialog.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.dialog.DialogLayer;
import com.zlylib.upperdialog.manager.Layer;
import com.zlylib.upperdialog.utils.Utils;

public class UpperActivity extends Activity implements Layer.OnVisibleChangeListener  {

    private static OnLayerCreatedCallback sOnLayerCreatedCallback = null;

    public static void start(Context context, OnLayerCreatedCallback callback) {
        sOnLayerCreatedCallback = callback;
        Intent intent = new Intent(context, UpperActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        Utils.transparent(this);
        DialogLayer dialogLayer = Upper.dialog(this);
        dialogLayer.onVisibleChangeListener(this);
        if (sOnLayerCreatedCallback != null) {
            sOnLayerCreatedCallback.onLayerCreated(dialogLayer);
        }
    }

    @Override
    public void onShow(Layer layer) {
    }

    @Override
    public void onDismiss(Layer layer) {
        finish();
        overridePendingTransition(0, 0);
    }

    public interface OnLayerCreatedCallback {
        /**
         * 浮层已创建，可在这里进行浮层的初始化和数据绑定
         */
        void onLayerCreated(DialogLayer dialogLayer);
    }
}
