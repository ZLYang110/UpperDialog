package com.zlyandroid.upperdialog.utils.dialog;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyandroid.upperdialog.R;
import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.listener.SimpleCallback;
import com.zlylib.upperdialog.manager.Layer;


/**
 * @author zhangliyang
 * GitHub: https://github.com/ZLYang110
 */
public class PermissionDialog {

    private final Context mContext;
    private boolean isGoSetting = false;
    private GroupType mGroupType = null;
    private SimpleCallback<Void> onNextListener = null;
    private SimpleCallback<Void> onCloseListener = null;

    public static PermissionDialog with(Context context) {
        return new PermissionDialog(context);
    }

    private PermissionDialog(Context context) {
        this.mContext = context;
    }

    public PermissionDialog setGoSetting(boolean goSetting) {
        isGoSetting = goSetting;
        return this;
    }

    public PermissionDialog setGroupType(GroupType groupType) {
        mGroupType = groupType;
        return this;
    }

    public PermissionDialog setOnNextListener(SimpleCallback<Void> onNextListener) {
        this.onNextListener = onNextListener;
        return this;
    }

    public PermissionDialog setOnCloseListener(SimpleCallback<Void> onCloseListener) {
        this.onCloseListener = onCloseListener;
        return this;
    }

    public void show() {
        Upper.dialog(mContext)
                .contentView(R.layout.basic_ui_dialog_permission)
                .gravity(Gravity.CENTER)
                .backgroundDimDefault()
                .cancelableOnTouchOutside(false)
                .cancelableOnClickKeyBack(false)
                .bindData(new Layer.DataBinder() {
                    @Override
                    public void bindData(Layer layer) {
                        ImageView iv = layer.getView(R.id.basic_ui_iv_dialog_permission);
                        TextView tvTitle = layer.getView(R.id.basic_ui_tv_dialog_permission_title);
                        TextView tvDescription = layer.getView(R.id.basic_ui_tv_dialog_permission_description);
                        TextView tvNext = layer.getView(R.id.basic_ui_tv_dialog_permission_next);

                        int iconResId = getIconResId();
                        if (iconResId > 0) {
                            iv.setImageResource(iconResId);
                            iv.setVisibility(View.VISIBLE);
                        } else {
                            iv.setVisibility(View.GONE);
                        }
                        int titleResId = getTitleResId();
                        if (titleResId > 0) {
                            tvTitle.setText(titleResId);
                            tvTitle.setVisibility(View.VISIBLE);
                        } else {
                            tvTitle.setVisibility(View.GONE);
                        }
                        tvDescription.setText(getDescription());
                        if (isGoSetting) {
                            tvNext.setText(R.string.basic_ui_dialog_permission_next_go_setting);
                        } else {
                            tvNext.setText(R.string.basic_ui_dialog_permission_next);
                        }
                    }
                })
                .onClickToDismiss(new Layer.OnClickListener() {
                    @Override
                    public void onClick(Layer layer, View v) {
                        if (onCloseListener != null) {
                            onCloseListener.onResult(null);
                        }
                    }
                }, R.id.basic_ui_tv_dialog_permission_close)
                .onClickToDismiss(new Layer.OnClickListener() {
                    @Override
                    public void onClick(Layer layer, View v) {
                        if (onNextListener != null) {
                            onNextListener.onResult(null);
                        }
                    }
                }, R.id.basic_ui_tv_dialog_permission_next)
                .show();
    }

    private int getIconResId() {
        int resId = R.mipmap.basic_ui_dialog_permission_unknow;
        if (mGroupType == null) {
            return resId;
        }
        switch (mGroupType) {
            default:
                resId = R.mipmap.ic_launcher;
                break;
            case CALENDAR:
                resId = R.mipmap.basic_ui_dialog_permission_calendar;
                break;
            case CAMERA:
                resId = R.mipmap.basic_ui_dialog_permission_camera;
                break;
            case CONTACTS:
                resId = R.mipmap.basic_ui_dialog_permission_contacts;
                break;
            case LOCATION:
                resId = R.mipmap.basic_ui_dialog_permission_location;
                break;
            case MICROPHONE:
                resId = R.mipmap.basic_ui_dialog_permission_microphone;
                break;
            case PHONE:
                resId = R.mipmap.basic_ui_dialog_permission_phone;
                break;
            case SMS:
                resId = R.mipmap.basic_ui_dialog_permission_sms;
                break;
            case SENSORS:
                resId = R.mipmap.basic_ui_dialog_permission_sensors;
                break;
            case STORAGE:
                resId = R.mipmap.basic_ui_dialog_permission_storage;
                break;
            case INSTSLLAPK:
                resId = R.mipmap.ic_launcher;
                break;
            case OVERLAY:
                resId = R.mipmap.ic_launcher;
                break;
            case SETTING:
                resId = R.mipmap.ic_launcher;
                break;
            case NOTIFICATIONSHOW:
                resId = R.mipmap.ic_launcher;
                break;
            case NOTIFICATIONASSESS:
                resId = R.mipmap.ic_launcher;
                break;
        }
        return resId;
    }

    private int getTitleResId(){
        int resId = R.string.basic_ui_dialog_permission_title_unknow;
        if (mGroupType == null) {
            return resId;
        }
        switch (mGroupType) {
            default:
                resId = R.string.basic_ui_dialog_permission_overlay_title;
                break;
            case CALENDAR:
                resId = R.string.basic_ui_dialog_permission_title_calendar;
                break;
            case CAMERA:
                resId = R.string.basic_ui_dialog_permission_title_camera;
                break;
            case CONTACTS:
                resId = R.string.basic_ui_dialog_permission_title_contacts;
                break;
            case LOCATION:
                resId = R.string.basic_ui_dialog_permission_title_location;
                break;
            case MICROPHONE:
                resId = R.string.basic_ui_dialog_permission_title_microphone;
                break;
            case PHONE:
                resId = R.string.basic_ui_dialog_permission_title_phone;
                break;
            case SMS:
                resId = R.string.basic_ui_dialog_permission_title_sms;
                break;
            case SENSORS:
                resId = R.string.basic_ui_dialog_permission_title_sensors;
                break;
            case STORAGE:
                resId = R.string.basic_ui_dialog_permission_title_storage;
                break;
            case INSTSLLAPK:
                resId = R.string.basic_ui_dialog_permission_install_title;
                break;
            case OVERLAY:
                resId = R.string.basic_ui_dialog_permission_overlay_title;
                break;
            case SETTING:
                resId = R.string.basic_ui_dialog_permission_setting_title;
                break;
            case NOTIFICATIONSHOW:
                resId = R.string.basic_ui_dialog_permission_notificationShow_title;
                break;
            case NOTIFICATIONASSESS:
                resId = R.string.basic_ui_dialog_permission_notificationAccess_title;
                break;

        }
        return resId;
    }

    private String getDescription() {
        String description = null;
        String descriptionRes;
        if (isGoSetting) {
            descriptionRes = mContext.getResources().getString(R.string.basic_ui_dialog_permission_description_go_setting);
        } else {
            descriptionRes = mContext.getResources().getString(R.string.basic_ui_dialog_permission_description);
        }
        int titleResId = getTitleResId();
      if(titleResId == R.string.basic_ui_dialog_permission_install_title){//安装应用
          description = mContext.getResources().getString(R.string.basic_ui_dialog_permission_install);
      }else if(titleResId == R.string.basic_ui_dialog_permission_overlay_title){//悬浮窗
          description = mContext.getResources().getString(R.string.basic_ui_dialog_permission_overlay);
      }else if(titleResId == R.string.basic_ui_dialog_permission_setting_title){//设置
          description = mContext.getResources().getString(R.string.basic_ui_dialog_permission_setting);
      }else if(titleResId == R.string.basic_ui_dialog_permission_notificationShow_title){//显示通知
          description = mContext.getResources().getString(R.string.basic_ui_dialog_permission_notificationShow);
      }else if(titleResId == R.string.basic_ui_dialog_permission_notificationAccess_title){//访问通知
            description = mContext.getResources().getString(R.string.basic_ui_dialog_permission_notificationAccess);
        }else{
          if (titleResId <= 0) {
              description = String.format(descriptionRes, mContext.getResources().getString(R.string.basic_ui_dialog_permission_description_title_holder));
          } else {
              description = String.format(descriptionRes, mContext.getResources().getString(titleResId));
          }
      }
        Log.d("getDescription","--"+ description);
        return description;
    }

    public enum GroupType {
        /**
         * 权限组类别，根据类别显示不同的图标和提示语
         */
        CALENDAR,
        CAMERA,
        CONTACTS,
        LOCATION,
        MICROPHONE,
        PHONE,
        SMS,
        SENSORS,
        STORAGE,
        INSTSLLAPK,//安装未知权限
        OVERLAY,//悬浮窗权限
        SETTING,//设置权限
        NOTIFICATIONSHOW,//显示通知权限
        NOTIFICATIONASSESS,//访问通知权限
    }
}
