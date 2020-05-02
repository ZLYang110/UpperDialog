package com.zlyandroid.upperdialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.common.AnimatorHelper;
import com.zlylib.upperdialog.dialog.DialogLayer;
import com.zlylib.upperdialog.manager.Layer;
import com.zlylib.upperdialog.view.DragLayout;
import com.zlylib.upperdialog.view.UpperActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {


    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        unbinder = ButterKnife.bind(this);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

   @OnClick({
           R.id.tv_show_app_context,
           R.id.tv_show_no_context,
           R.id.tv_show,
           R.id.tv_show_delay,
           R.id.tv_show_top,
           R.id.tv_show_bottom,
           R.id.tv_show_blur_bg,
           R.id.tv_show_dark_bg,
           R.id.tv_show_tran_bg,
           R.id.tv_show_bottom_zoom_alpha_in,
           R.id.tv_show_bottom_in,
           R.id.tv_show_bottom_alpha_in,
           R.id.tv_show_top_in,
           R.id.tv_show_top_alpha_in,
           R.id.tv_show_top_bottom,
           R.id.tv_show_top_bottom_alpha,
           R.id.tv_show_bottom_top,
           R.id.tv_show_bottom_top_alpha,
           R.id.tv_show_left_in,
           R.id.tv_show_left_alpha_in,
           R.id.tv_show_right_in,
           R.id.tv_show_right_alpha_in,
           R.id.tv_show_left_right,
           R.id.tv_show_left_right_alpha,
           R.id.tv_show_right_left,
           R.id.tv_show_right_left_alpha,
           R.id.tv_show_reveal,

   })
    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.tv_show_app_context:
               Upper.dialog(new UpperActivity.OnLayerCreatedCallback() {
                   @Override
                   public void onLayerCreated(@NonNull DialogLayer dialogLayer) {
                       dialogLayer.contentView(R.layout.dialog_normal)
                               .backgroundDimDefault()
                               .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                               .show();
                   }
               });
               break;
           case R.id.tv_show_no_context:
               Upper.dialog()
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .show();
               break;
           case R.id.tv_show:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .show();
               break;
           case R.id.tv_show_delay:
               App.sHandler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Upper.dialog(DialogActivity.this)
                               .contentView(R.layout.dialog_normal)
                               .backgroundDimDefault()
                               .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                               .show();
                   }
               }, 2000);
               break;
           case R.id.tv_show_top://显示顶部
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_match_width)
                       .avoidStatusBar(true)
                       .backgroundDimDefault()
                       .gravity(Gravity.TOP)
                       .dragDismiss(DragLayout.DragStyle.Top)
                       .onClickToDismiss(R.id.fl_dialog_no)
                       .show();
               break;
           case R.id.tv_show_bottom:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_list)
                       .backgroundDimDefault()
                       .gravity(Gravity.BOTTOM)
                       .dragDismiss(DragLayout.DragStyle.Bottom)
                       .onClickToDismiss(R.id.fl_dialog_no)
                       .show();
               break;
           case R.id.tv_show_blur_bg://背景高斯模糊
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundBlurPercent(0.05f)
                       .backgroundColorInt(getResources().getColor(R.color.dialog_blur_bg))
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .show();
               break;

           case R.id.tv_show_dark_bg://背景变暗
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_tran_bg://背景全透明
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_bottom_zoom_alpha_in://底部缩放进入底部缩放退出alpha
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               AnimatorSet set = new AnimatorSet();
                               Animator a1 = AnimatorHelper.createBottomAlphaInAnim(content, 0.3F);
                               a1.setInterpolator(new DecelerateInterpolator(1.5f));
                               Animator a2 = AnimatorHelper.createZoomAlphaInAnim(content, 0.9F);
                               a2.setInterpolator(new DecelerateInterpolator(2.5f));
                               set.playTogether(a1, a2);
                               return set;
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               AnimatorSet set = new AnimatorSet();
                               Animator a1 = AnimatorHelper.createBottomAlphaOutAnim(content, 0.3F);
                               a1.setInterpolator(new DecelerateInterpolator(1.5f));
                               Animator a2 = AnimatorHelper.createZoomAlphaOutAnim(content, 0.9F);
                               a2.setInterpolator(new DecelerateInterpolator(2.5f));
                               set.playTogether(a1, a2);
                               return set;
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .show();
               break;
           case R.id.tv_show_bottom_in://底部进入底部退出
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .dragDismiss(DragLayout.DragStyle.Bottom)
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_bottom_alpha_in:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .dragDismiss(DragLayout.DragStyle.Bottom)
                       .dragTransformer(new DialogLayer.DragTransformer() {
                           @Override
                           public void onDragging(View content, View background, float f) {
                               content.setAlpha(1 - f);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;

           case R.id.tv_show_top_in:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .dragDismiss(DragLayout.DragStyle.Top)
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_top_alpha_in:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createTopAlphaInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createTopAlphaOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_top_bottom:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createTopInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createBottomOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_bottom_top:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createBottomInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createTopOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_top_bottom_alpha:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createTopAlphaInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createBottomAlphaOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_bottom_top_alpha:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createBottomAlphaInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createTopAlphaOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_left_in:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .dragDismiss(DragLayout.DragStyle.Left)
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_left_alpha_in:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createLeftAlphaInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createLeftAlphaOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_right_in:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .dragDismiss(DragLayout.DragStyle.Right)
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_right_alpha_in:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createRightAlphaInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createRightAlphaOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_left_right:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createLeftInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createRightOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_right_left:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createRightInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createLeftOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_left_right_alpha:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createLeftAlphaInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createRightAlphaOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_right_left_alpha:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               return AnimatorHelper.createRightAlphaInAnim(content);
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               return AnimatorHelper.createLeftAlphaOutAnim(content);
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
           case R.id.tv_show_reveal:
               Upper.dialog(DialogActivity.this)
                       .contentView(R.layout.dialog_normal)
                       .backgroundDimDefault()
                       .contentAnimator(new DialogLayer.AnimatorCreator() {
                           @Override
                           public Animator createInAnimator(View content) {
                               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                   return AnimatorHelper.createCircularRevealInAnim(content, content.getMeasuredWidth() / 2, content.getMeasuredHeight() / 2);
                               } else {
                                   return null;
                               }
                           }

                           @Override
                           public Animator createOutAnimator(View content) {
                               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                   return AnimatorHelper.createCircularRevealOutAnim(content, content.getMeasuredWidth() / 2, content.getMeasuredHeight() / 2);
                               } else {
                                   return null;
                               }
                           }
                       })
                       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                       .onClick(new Layer.OnClickListener() {
                           @Override
                           public void onClick(Layer anyLayer, View v) {
                               anyLayer.dismiss();
                           }
                       }, R.id.fl_dialog_yes)
                       .show();
               break;
       }
    }
}
