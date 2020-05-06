# UpperDialog

Android轻量级弹窗。

自定义Dialog弹窗，自定义大小和位置，进出场方式。链式调用，自由扩展。


[GitHub主页](https://github.com/ZLYang110/UpperDialog)

[简书](https://www.jianshu.com/p/8c7b2ebd892a)

[Demo下载](https://github.com/ZLYang110/UpperDialog/raw/master/app/release/app-release.apk)





# 简介
- anylayer
- 同时兼容support和androidx
- 实现几种常用效果
- LoadingDialog 加载
- TipDialog 弹窗提示
- ListDialog 底部列表弹出
- Upper 自定义加载方式
   - 占用区域不会超过当前Activity避免导航栏遮挡
   - 支持自定义大小和显示位置
   - 支持自定义数据绑定
   - 支持自定义进出场动画
   - 支持自定义背景颜色/图片/高斯模糊
   - 支持在Activity的onCreate生命周期弹出
   - 支持从ApplicationContext中弹出
   - 支持拖拽关闭
   - 支持不拦截外部事件
- Toast效果
   - 支持自定义图标和文字
   - 支持自定义显示时长
   - 支持自定义位置
   - 支持自定义背景资源和颜色
   - 支持自定义透明度
   - 支持自定义进出场动画
- Popup效果
   - 拥有Dialog效果特性
   - 支持跟随目标View移动






# 说明

# 运行截图
<img src="https://github.com/ZLYang110/UpperDialog/raw/master/screenshot/Screenshot_20200506_115850_com.zlyandroid.upperdi.jpg" width = "180" height = "300" alt="图片名称"/> <img src="https://github.com/ZLYang110/UpperDialog/raw/master/screenshot/Screenshot_20200506_120027_com.zlyandroid.upperdi.jpg" width = "180" height = "300" alt="图片名称" /> <img src="https://github.com/ZLYang110/UpperDialog/raw/master/screenshot/Screenshot_20200506_115850_com.zlyandroid.upperdi.jpg" width = "180" height = "300" alt="图片名称"/> <img src="https://github.com/ZLYang110/UpperDialog/raw/master/screenshot/Screenshot_20200506_120035_com.zlyandroid.upperdi.jpg" width = "180" height = "300" alt="下载"  />
<img src="https://github.com/ZLYang110/UpperDialog/raw/master/screenshot/Screenshot_20200506_110015_com.zlyandroid.upperdi.jpg" width = "180" height = "300" alt="图片名称"/> <img src="https://github.com/ZLYang110/UpperDialog/raw/master/screenshot/Screenshot_20200506_110022_com.zlyandroid.upperdi.jpg" width = "180" height = "300" alt="图片名称"/> <img src="https://github.com/ZLYang110/UpperDialog/raw/master/screenshot/Screenshot_20200506_110027_com.zlyandroid.upperdi.jpg" width = "180" height = "300" alt="图片名称" />

# 使用说明

## 集成

![](https://img.shields.io/badge/Downloads%20Week-655-green) ![](https://img.shields.io/badge/Downloads%20Month-2.4K-blue)

- ### 添加jitpack库

```java
// build.gradle(Project:)
allprojects {
    repositories {
        ...
            maven { url 'https://www.jitpack.io' }
    }
}
```

- ### 添加依赖

```groovy
// build.gradle(Module:)
dependencies {

   implementation 'com.github.ZLYang110:UpperDialog:1.0'
}
```

### LoadingDialog


```java

  //初始化
  dialog = DialogUtils.createLoadingDialog(this, "请稍后...");

  //显示
  dialog.show();

  //关闭
  dialog.dismiss();

```

### TipDialog


```java


  TipDialog.with(getActivity())
           .message("确定不给个star么？")
           .onYes(new SimpleCallback<Void>() {
           @Override
           public void onResult(Void data) {

               }
           })
           .show();

```

### ListDialog


```java

/**
 * 正常弹出
 */
  List<String> lisStr=new ArrayList<>();
  lisStr.add("选项1");
  lisStr.add("选项2");
  lisStr.add("选项3");
  lisStr.add("选项4");

  ListDialog.with(SettingActivity.this)
            .cancelable(true)
//          .title("列表动画")
            .datas(lisStr)
            .currSelectPos(1)
            .listener(new ListDialog.OnItemSelectedListener() {
            @Override
            public void onSelect(String data, int pos) {
            Log.d("selectStr",lisStr.get(pos));
               }
             })
           .show();

```



### Upper


```java

/**
 * 正常弹出
 */
  Upper.dialog(DialogActivity.this)
       .contentView(R.layout.dialog_normal)
       .backgroundDimDefault()
       .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
       .show();

/**
 * 显示上方
 */
  Upper.dialog(DialogActivity.this)
       .contentView(R.layout.dialog_match_width)
       .avoidStatusBar(true)
       .backgroundDimDefault()
       .gravity(Gravity.TOP)
       .dragDismiss(DragLayout.DragStyle.Top)
       .onClickToDismiss(R.id.fl_dialog_no)
       .show();
/**
 * 显示下方
 */
   Upper.dialog(DialogActivity.this)
        .contentView(R.layout.dialog_list)
        .backgroundDimDefault()
        .gravity(Gravity.BOTTOM)
        .dragDismiss(DragLayout.DragStyle.Bottom)
        .onClickToDismiss(R.id.fl_dialog_no)
        .show();



```


### Toast


```java

/**
 * 正常弹出
 */
   ToastUtils.show(ToastActivity.this,"轻量级浮层弹窗");



 /**
  * 自定义效果
  */
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


```

说明

如果报错如下

```java

java.io.IOException: Permission denied

```
查看是否有网络权限然后在AndroidManifest.xml中application添加


```java

 android:requestLegacyExternalStorage="true"

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

# 更新日志

1.1
----

  - 添加Toast弹窗

1.0
----

  - dialog轻量级封装库

