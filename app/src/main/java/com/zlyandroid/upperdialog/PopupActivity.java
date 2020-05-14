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

import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.common.Align;
import com.zlylib.upperdialog.common.AnimatorHelper;
import com.zlylib.upperdialog.dialog.DialogLayer;
import com.zlylib.upperdialog.manager.Layer;
import com.zlylib.upperdialog.view.DragLayout;
import com.zlylib.upperdialog.view.UpperActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PopupActivity extends AppCompatActivity implements View.OnClickListener {


    private DialogLayer upper_show_target_right = null;
    private DialogLayer upper_show_target_bottom = null;
    private DialogLayer upper_show_target_full = null;
    private DialogLayer upper_show_menu = null;

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        unbinder = ButterKnife.bind(this);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

   @OnClick({
           R.id.tv_show_menu,
           R.id.tv_show_target_right,
           R.id.tv_show_target_top,
           R.id.tv_show_target_bottom,
           R.id.tv_show_target_left,

   })


    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.tv_show_menu:
               if (upper_show_menu == null) {
                   upper_show_menu = Upper.popup(findViewById(R.id.tv_show_menu))
                           .align(Align.Direction.VERTICAL, Align.Horizontal.ALIGN_RIGHT, Align.Vertical.BELOW, true)
                           .offsetYdp(15)
                           .outsideTouchedToDismiss(true)
                           .outsideInterceptTouchEvent(false)
                           .contentView(R.layout.popup_meun)
                           .contentAnimator(new DialogLayer.AnimatorCreator() {
                               @Override
                               public Animator createInAnimator(View content) {
                                   return AnimatorHelper.createDelayedZoomInAnim(content, 1F, 0F);
                               }

                               @Override
                               public Animator createOutAnimator(View content) {
                                   return AnimatorHelper.createDelayedZoomOutAnim(content, 1F, 0F);
                               }
                           });
               }
               if (upper_show_menu.isShow()) {
                   upper_show_menu.dismiss();
               } else {
                   upper_show_menu.show();
               }
               break;
           case R.id.tv_show_target_right:
               if (upper_show_target_right == null) {
                   upper_show_target_right = Upper.popup(findViewById(R.id.tv_show_target_right))
                           .direction(Align.Direction.HORIZONTAL)
                           .horizontal(Align.Horizontal.TO_RIGHT)
                           .vertical(Align.Vertical.CENTER)
                           .inside(true)
                           .outsideInterceptTouchEvent(false)
                           .contentView(R.layout.popup_normal)
                           .contentAnimator(new DialogLayer.AnimatorCreator() {
                               @Override
                               public Animator createInAnimator(View content) {
                                   return AnimatorHelper.createLeftInAnim(content);
                               }

                               @Override
                               public Animator createOutAnimator(View content) {
                                   return AnimatorHelper.createLeftOutAnim(content);
                               }
                           });
               }
               if (upper_show_target_right.isShow()) {
                   upper_show_target_right.dismiss();
               } else {
                   upper_show_target_right.show();
               }
               break;
           case R.id.tv_show_target_top:
               Upper.popup(findViewById(R.id.tv_show_target_top))
                       .align(Align.Direction.VERTICAL, Align.Horizontal.CENTER, Align.Vertical.ABOVE, true)
                       .contentView(R.layout.popup_match_width)
                       .backgroundDimDefault()
                       .gravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createBottomInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createBottomOutAnim(content);
                           }
                       })
                       .show();
               break;
           case R.id.tv_show_target_bottom:
               if (upper_show_target_bottom == null) {
                   upper_show_target_bottom = Upper.popup(findViewById(R.id.tv_show_target_bottom))
                           .align(Align.Direction.VERTICAL, Align.Horizontal.CENTER, Align.Vertical.BELOW, true)
                           .outsideInterceptTouchEvent(false)
                           .backgroundDimDefault()
                           .contentView(R.layout.popup_match_width)
                           .contentAnimator(new DialogLayer.AnimatorCreator() {
                               @Override
                               public Animator createInAnimator(View content) {
                                   return AnimatorHelper.createTopInAnim(content);
                               }

                               @Override
                               public Animator createOutAnimator(View content) {
                                   return AnimatorHelper.createTopOutAnim(content);
                               }
                           });
               }
               if (upper_show_target_bottom.isShow()) {
                   upper_show_target_bottom.dismiss();
               } else {
                   upper_show_target_bottom.show();
               }
               break;
           case R.id.tv_show_target_left://显示顶部
               Upper.popup(findViewById(R.id.tv_show_target_left))
                       .align(Align.Direction.HORIZONTAL, Align.Horizontal.TO_LEFT, Align.Vertical.CENTER, true)
                       .contentView(R.layout.popup_normal)
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createRightInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createRightOutAnim(content);
                           }
                       })
                       .show();
               break;

       }
    }
}
