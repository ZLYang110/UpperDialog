package com.zlyandroid.upperdialog.utils;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import com.zlyandroid.upperdialog.utils.dialog.PermissionDialog;
import com.zlylib.mypermissionlib.MyPermission;
import com.zlylib.mypermissionlib.RequestInterceptor;
import com.zlylib.mypermissionlib.RequestListener;
import com.zlylib.mypermissionlib.RuntimeRequester;
import com.zlylib.upperdialog.listener.SimpleCallback;

import java.io.File;
import java.util.Arrays;


/**
 * @author zhangliyang
 * GitHub: https://github.com/ZLYang110
 */
public class PermissionUtils {

    public static final class PermissionGroup {
        static final String[] CALENDAR = new String[]{
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR
        };
        static final String[] CAMERA = new String[]{
                Manifest.permission.CAMERA
        };
        static final String[] CONTACTS = new String[]{
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.GET_ACCOUNTS
        };
        static final String[] LOCATION = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        static final String[] MICROPHONE = new String[]{
                Manifest.permission.RECORD_AUDIO
        };
        static final String[] PHONE;
        static final String[] SENSORS;
        static final String[] SMS = new String[]{
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH,
                Manifest.permission.RECEIVE_MMS
        };
        static final String[] STORAGE = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        static {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                PHONE = new String[]{
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.WRITE_CALL_LOG,
                        Manifest.permission.ADD_VOICEMAIL,
                        Manifest.permission.USE_SIP,
                        Manifest.permission.PROCESS_OUTGOING_CALLS,
                        Manifest.permission.READ_PHONE_NUMBERS,
                        Manifest.permission.ANSWER_PHONE_CALLS};
            } else {
                PHONE = new String[]{
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.WRITE_CALL_LOG,
                        Manifest.permission.ADD_VOICEMAIL,
                        Manifest.permission.USE_SIP,
                        Manifest.permission.PROCESS_OUTGOING_CALLS};
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                SENSORS = new String[]{Manifest.permission.BODY_SENSORS};
            } else {
                SENSORS = new String[0];
            }
        }
    }


    private static PermissionDialog.GroupType getGroupType(String permission) {
        if (contain(PermissionGroup.CALENDAR, permission)) {
            return PermissionDialog.GroupType.CALENDAR;
        }
        if (contain(PermissionGroup.CAMERA, permission)) {
            return PermissionDialog.GroupType.CAMERA;
        }
        if (contain(PermissionGroup.CONTACTS, permission)) {
            return PermissionDialog.GroupType.CONTACTS;
        }
        if (contain(PermissionGroup.LOCATION, permission)) {
            return PermissionDialog.GroupType.LOCATION;
        }
        if (contain(PermissionGroup.MICROPHONE, permission)) {
            return PermissionDialog.GroupType.MICROPHONE;
        }
        if (contain(PermissionGroup.PHONE, permission)) {
            return PermissionDialog.GroupType.PHONE;
        }
        if (contain(PermissionGroup.SENSORS, permission)) {
            return PermissionDialog.GroupType.SENSORS;
        }
        if (contain(PermissionGroup.SMS, permission)) {
            return PermissionDialog.GroupType.SMS;
        }
        if (contain(PermissionGroup.STORAGE, permission)) {
            return PermissionDialog.GroupType.STORAGE;
        }
        if (contain(PermissionGroup.STORAGE, permission)) {
            return PermissionDialog.GroupType.STORAGE;
        }

        return null;
    }

    private static boolean contain(String[] group, String permission) {
        return Arrays.asList(group).contains(permission);
    }

    public static RuntimeRequester request(RequestListener listener, Context context, int code, String... permissions) {
        return MyPermission.with(context)
                .runtime(code)
                .permissions(permissions)
                .onBeforeRequest(new RequestInterceptor<String>() {
                    @Override
                    public void intercept(@NonNull String data, @NonNull RequestInterceptor.Executor executor) {
                        // TODO 在每个权限申请之前调用，多次回调。可弹窗向用户说明下面将进行某个权限的申请。
                        // processor有两个方法，必须调用其一，否则申请流程终止。
                        PermissionDialog.GroupType groupType = getGroupType(data);
                        PermissionDialog.with(context)
                                .setGoSetting(false)
                                .setGroupType(groupType)
                                .setOnNextListener(new SimpleCallback<Void>() {
                                    @Override
                                    public void onResult(Void data) {
                                        executor.execute();
                                    }
                                })
                                .setOnCloseListener(new SimpleCallback<Void>() {
                                    @Override
                                    public void onResult(Void data) {
                                        executor.cancel();
                                    }
                                })
                                .show();
                    }
                })
                .onBeenDenied(new RequestInterceptor<String>() {
                    @Override
                    public void intercept(@NonNull String data, @NonNull Executor executor) {
                        // TODO 在每个权限被拒后调用，多次回调。可弹窗向用户说明为什么需要该权限，否则用户可能在下次申请勾选不再提示。
                        // processor有两个方法，必须调用其一，否则申请流程终止。
                        PermissionDialog.GroupType groupType = getGroupType(data);
                        PermissionDialog.with(context)
                                .setGoSetting(false)
                                .setGroupType(groupType)
                                .setOnNextListener(new SimpleCallback<Void>() {
                                    @Override
                                    public void onResult(Void data) {
                                        executor.execute();
                                    }
                                })
                                .setOnCloseListener(new SimpleCallback<Void>() {
                                    @Override
                                    public void onResult(Void data) {
                                        executor.cancel();
                                    }
                                })
                                .show();
                    }
                })
                .onGoSetting(new RequestInterceptor<String>() {
                    @Override
                    public void intercept(@NonNull String data, @NonNull Executor executor) {
                        // TODO 在每个权限永久被拒后调用（即用户勾选不再提示），多次回调。可弹窗引导用户前往设置打开权限，调用executor.execute()会自动跳转设置。
                        // processor有两个方法，必须调用其一，否则申请流程将终止。
                        PermissionDialog.GroupType groupType = getGroupType(data);
                        PermissionDialog.with(context)
                                .setGoSetting(true)
                                .setGroupType(groupType)
                                .setOnNextListener(new SimpleCallback<Void>() {
                                    @Override
                                    public void onResult(Void data) {
                                        executor.execute();
                                    }
                                })
                                .setOnCloseListener(new SimpleCallback<Void>() {
                                    @Override
                                    public void onResult(Void data) {
                                        executor.cancel();
                                    }
                                })
                                .show();
                    }
                })
                .request(listener);
    }

    /**
     * 安装未知权限
     */
    public static Void requestInstall(RequestListener listener, Context context, String apkFile) {
        return MyPermission.with(context).install(new File(apkFile)).onWithoutPermission(new RequestInterceptor<File>() {
            @Override
            public void intercept(@NonNull File data, @NonNull Executor executor) {
                PermissionDialog.with(context)
                        .setGoSetting(true)
                        .setGroupType(PermissionDialog.GroupType.INSTSLLAPK)
                        .setOnNextListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.execute();
                            }
                        })
                        .setOnCloseListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.cancel();
                            }
                        })
                        .show();
            }
        }).request(listener);
    }

    /**
     * 悬浮窗权限
     */
    public static Void requestOverlay(RequestListener listener, Context context) {
        return MyPermission.with(context).overlay().onWithoutPermission(new RequestInterceptor<Void>() {
            @Override
            public void intercept(@NonNull Void data, @NonNull Executor executor) {
                PermissionDialog.with(context)
                        .setGoSetting(true)
                        .setGroupType(PermissionDialog.GroupType.OVERLAY)
                        .setOnNextListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.execute();
                            }
                        })
                        .setOnCloseListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.cancel();
                            }
                        })
                        .show();
            }
        }).request(listener);
    }
    /**
     * 设置权限
     */
    public static Void requestSetting(RequestListener listener, Context context) {
        return MyPermission.with(context).setting().onWithoutPermission(new RequestInterceptor<Void>() {
            @Override
            public void intercept(@NonNull Void data, @NonNull Executor executor) {
                PermissionDialog.with(context)
                        .setGoSetting(true)
                        .setGroupType(PermissionDialog.GroupType.OVERLAY)
                        .setOnNextListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.execute();
                            }
                        })
                        .setOnCloseListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.cancel();
                            }
                        })
                        .show();
            }
        }).request(listener);
    }

    /**
     * 显示通知权限
     */
    public static Void requestNotificationShow(RequestListener listener, Context context) {
        return MyPermission.with(context).notificationShow().onWithoutPermission(new RequestInterceptor<Void>() {
            @Override
            public void intercept(@NonNull Void data, @NonNull Executor executor) {
                PermissionDialog.with(context)
                        .setGoSetting(true)
                        .setGroupType(PermissionDialog.GroupType.OVERLAY)
                        .setOnNextListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.execute();
                            }
                        })
                        .setOnCloseListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.cancel();
                            }
                        })
                        .show();
            }
        }).request(listener);
    }


    /**
     * 访问通知权限
     */
    public static Void requestNotificationAccess(RequestListener listener, Context context) {
        return MyPermission.with(context).notificationAccess().onWithoutPermission(new RequestInterceptor<Void>() {
            @Override
            public void intercept(@NonNull Void data, @NonNull Executor executor) {
                PermissionDialog.with(context)
                        .setGoSetting(true)
                        .setGroupType(PermissionDialog.GroupType.OVERLAY)
                        .setOnNextListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.execute();
                            }
                        })
                        .setOnCloseListener(new SimpleCallback<Void>() {
                            @Override
                            public void onResult(Void data) {
                                executor.cancel();
                            }
                        })
                        .show();
            }
        }).request(listener);
    }
}
