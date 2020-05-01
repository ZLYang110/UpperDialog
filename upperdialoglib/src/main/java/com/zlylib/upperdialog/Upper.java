package com.zlylib.upperdialog;


import android.content.Context;

import com.zlylib.upperdialog.dialog.DialogLayer;

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


}



