package com.zlylib.upperdialog.manager;

import android.view.View;

/**
 * @author zhangliyang
 */
public class ScrollCompat {

    public static boolean canScrollHorizontally(View v, int direction) {
        return v.canScrollHorizontally(direction);
    }

    public static boolean canScrollVertically(View v, int direction) {
        return v.canScrollVertically(direction);
    }
}
