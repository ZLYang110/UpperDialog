package com.zlyandroid.upperdialog.utils;


import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import com.zlyandroid.upperdialog.R;
import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.common.AnimatorHelper;
import com.zlylib.upperdialog.manager.Layer;

import java.util.Random;

/**
 * @author zhangliyang
 * GitHub: https://github.com/ZLYang110
 */
public class ToastUtils {

    private static Random mRandom = new Random();

    /**
     * 提示
     *    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL :
     *    Gravity.TOP | Gravity.CENTER_HORIZONTAL :
     *    Gravity.LEFT | Gravity.CENTER_VERTICAL :
     *    Gravity.RIGHT | Gravity.CENTER_VERTICAL
     * */
    public static void show(Context context, String msg) {
        show(context, msg, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    }

    public static void show(Context context, String msg, int gravity) {
        Upper.toast(context)
                .duration(3000)
                .message(msg)
                .backgroundColorRes(R.color.default_title_background_color)
                .gravity(gravity)
                .animator(new Layer.AnimatorCreator() {
                    @Override
                    public Animator createInAnimator(View target) {
                        return AnimatorHelper.createZoomAlphaInAnim(target);
                    }

                    @Override
                    public Animator createOutAnimator(View target) {
                        return AnimatorHelper.createZoomAlphaOutAnim(target);
                    }
                })
                .show();
    }
    /**
     * 正确提示
     *
     *    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL :
     *    Gravity.TOP | Gravity.CENTER_HORIZONTAL :
     *    Gravity.LEFT | Gravity.CENTER_VERTICAL :
     *    Gravity.RIGHT | Gravity.CENTER_VERTICAL
     * */
    public static void showSuccess(Context context, String msg) {
        showSuccess(context, msg, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    }

    public static void showSuccess(Context context, String msg, int gravity) {
        Upper.toast(context)
                .duration(3000)
                .icon(R.drawable.ic_success)
                .message(msg)
                .backgroundColorRes(R.color.assist)
                .gravity(gravity)
                .animator(new Layer.AnimatorCreator() {
                    @Override
                    public Animator createInAnimator(View target) {
                        return AnimatorHelper.createZoomAlphaInAnim(target);
                    }

                    @Override
                    public Animator createOutAnimator(View target) {
                        return AnimatorHelper.createZoomAlphaOutAnim(target);
                    }
                })
                .show();
    }


/**
* 错误提示
 *
 *    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL :
 *    Gravity.TOP | Gravity.CENTER_HORIZONTAL :
 *    Gravity.LEFT | Gravity.CENTER_VERTICAL :
 *    Gravity.RIGHT | Gravity.CENTER_VERTICAL
* */
    public static void showFail(Context context, String msg) {
        showFail(context, msg, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    }

    public static void showFail(Context context, String msg, int gravity) {
        Upper.toast(context)
                .duration(3000)
                .icon(R.drawable.ic_fail)
                .message(msg)
                .backgroundColorRes(R.color.rednrd)
                .gravity(gravity)
                .animator(new Layer.AnimatorCreator() {
                    @Override
                    public Animator createInAnimator(View target) {
                        return AnimatorHelper.createZoomAlphaInAnim(target);
                    }

                    @Override
                    public Animator createOutAnimator(View target) {
                        return AnimatorHelper.createZoomAlphaOutAnim(target);
                    }
                })
                .show();
    }

    /**
    * 可自定义
    * */
    public static void showNormal(Context context, String msg ) {
        boolean isSucc = mRandom.nextBoolean();
        Upper.toast()
                .duration(3000)
                .icon(isSucc ? R.drawable.ic_success : R.drawable.ic_fail)
                .message(isSucc ? " 成功了" : " 失败了")
                .alpha(mRandom.nextFloat())
                .backgroundColorInt(Color.argb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255)))
                .gravity(
                        mRandom.nextBoolean() ?
                                mRandom.nextBoolean() ?
                                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL :
                                        Gravity.TOP | Gravity.CENTER_HORIZONTAL :
                                mRandom.nextBoolean() ?
                                        Gravity.LEFT | Gravity.CENTER_VERTICAL :
                                        Gravity.RIGHT | Gravity.CENTER_VERTICAL
                )
                .animator(new Layer.AnimatorCreator() {
                    @Override
                    public Animator createInAnimator(View target) {
                        return AnimatorHelper.createZoomAlphaInAnim(target);
                    }

                    @Override
                    public Animator createOutAnimator(View target) {
                        return AnimatorHelper.createZoomAlphaOutAnim(target);
                    }
                })
                .show();
    }
}
