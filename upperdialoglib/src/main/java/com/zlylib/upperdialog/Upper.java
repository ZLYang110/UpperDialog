package com.zlylib.upperdialog;


import android.content.Context;

import com.zlylib.upperdialog.dialog.DialogLayer;
import com.zlylib.upperdialog.manager.ActivityHolder;
import com.zlylib.upperdialog.view.UpperActivity;

/**
 * @author zhangliyang
 */
public class Upper {


    /**
    * 弹窗
    * */
    public static DialogLayer dialog(Context context) {
        return new DialogLayer(context);
    }

    public static void dialog(UpperActivity.OnLayerCreatedCallback callback) {
        UpperActivity.start(ActivityHolder.getApplication(), callback);
    }
   public static DialogLayer dialog() {
        return new DialogLayer(ActivityHolder.getCurrentActivity());
    }


}



