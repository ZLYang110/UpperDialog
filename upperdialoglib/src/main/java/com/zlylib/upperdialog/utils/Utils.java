package com.zlylib.upperdialog.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zlylib.upperdialog.manager.DecorLayer;
import com.zlylib.upperdialog.view.ContainerLayout;


/**
 * @author zhangliyang
 * @date 2018/10/25
 */
public final class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    public static void init(Context context) {
        Utils.context = context;

    }

    public static Context getAppContext() {
        if (context == null) {
            throw new RuntimeException("Utils未在Application中初始化");
        }
        return context;
    }
    public static <T> T requireNonNull(T obj, String msg) {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
        return obj;
    }

    public static <T> T requireNonNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static float floatRange01(float value) {
        return floatRange(value, 0F, 1F);
    }

    public static float floatRange(float value, float min, float max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    static int intRange(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 从当前上下文获取Activity
     */
    public static Activity getActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof Activity) {
                return (Activity) baseContext;
            }
        }
        return null;
    }

    public static Bitmap snapshot(FrameLayout decor,
                           ImageView iv,
                           float scale,
                           DecorLayer.LevelLayout currLevelLayout,
                           ContainerLayout currContainerLayout) {
        int w = iv.getWidth();
        int h = iv.getHeight();
        int oW = (int) (w / scale);
        int oH = (int) (h / scale);
        Bitmap bitmap = Bitmap.createBitmap(oW, oH, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.save();
        int[] locationRootView = new int[2];
        decor.getLocationOnScreen(locationRootView);
        int[] locationBackground = new int[2];
        iv.getLocationOnScreen(locationBackground);
        int x = locationBackground[0] - locationRootView[0];
        int y = locationBackground[1] - locationRootView[1];
        canvas.scale(1 / scale, 1 / scale);
        canvas.translate(x / scale, y / scale);
        if (decor.getBackground() != null) {
            decor.getBackground().draw(canvas);
        }
        out:
        for (int i = 0; i < decor.getChildCount(); i++) {
            View decorChildAt = decor.getChildAt(i);
            if (decorChildAt instanceof DecorLayer.LayerLayout) {
                DecorLayer.LayerLayout layerLayout = (DecorLayer.LayerLayout) decorChildAt;
                for (int j = 0; j < layerLayout.getChildCount(); j++) {
                    View layerChildAt = layerLayout.getChildAt(j);
                    if (layerChildAt instanceof DecorLayer.LevelLayout) {
                        DecorLayer.LevelLayout levelLayout = (DecorLayer.LevelLayout) layerChildAt;
                        if (levelLayout == currLevelLayout) {
                            for (int k = 0; k < levelLayout.getChildCount(); k++) {
                                View containerLayout = levelLayout.getChildAt(k);
                                if (containerLayout == currContainerLayout) {
                                    break out;
                                } else {
                                    containerLayout.draw(canvas);
                                }
                            }
                            break out;
                        } else {
                            levelLayout.draw(canvas);
                        }
                    } else {
                        break out;
                    }
                }
                break;
            } else {
                decorChildAt.draw(canvas);
            }
        }
        canvas.restore();

        return bitmap;
    }

    public static void transparent(Activity activity) {
        final Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

   public static void getViewSize(final View view,final Runnable runnable) {
        requireNonNull(view);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (view.getViewTreeObserver().isAlive()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                }
                runnable.run();
            }
        });
    }



}
