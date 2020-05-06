package com.zlylib.upperdialog.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import com.zlylib.upperdialog.utils.Utils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangliyang
 * GitHub: https://github.com/ZLYang110
 */
public final class ActivityHolder implements Application.ActivityLifecycleCallbacks {

    private static ActivityHolder INSTANCE = null;

    private final Application mApplication;
    private final List<Activity> mActivityStack = new LinkedList<>();

    private ActivityHolder(Application application) {
        mApplication = Utils.requireNonNull(application, "application == null");
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new ActivityHolder(application);
        }
    }

    public static Application getApplication() {
        if (INSTANCE == null) {
            return null;
        }
        return INSTANCE.mApplication;
    }

    public static Activity getActivity(Class<Activity> clazz) {
        Utils.requireNonNull(clazz, "clazz == null");
        if (INSTANCE == null) {
            return null;
        }
        if (INSTANCE.mActivityStack.isEmpty()) {
            return null;
        }
        final int size = INSTANCE.mActivityStack.size();
        for (int i = size - 1; i >= 0; i--) {
            Activity activity = INSTANCE.mActivityStack.get(i);
            if (TextUtils.equals(clazz.getName(), activity.getClass().getName())) {
                return activity;
            }
        }
        return null;
    }

    public static Activity getCurrentActivity() {
        if (INSTANCE == null) {
            return null;
        }
        if (INSTANCE.mActivityStack.isEmpty()) {
            return null;
        }
        return INSTANCE.mActivityStack.get(INSTANCE.mActivityStack.size() - 1);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityStack.remove(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }
}
