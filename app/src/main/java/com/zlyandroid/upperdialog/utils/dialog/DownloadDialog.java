package com.zlyandroid.upperdialog.utils.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zlyandroid.upperdialog.R;
import com.zlyandroid.upperdialog.utils.DownloadUtils;
import com.zlyandroid.upperdialog.utils.FileUtils;
import com.zlylib.upperdialog.Upper;
import com.zlylib.upperdialog.manager.Layer;
import com.zlylib.upperdialog.utils.ResUtils;
import com.zlylib.upperdialog.utils.Utils;

import java.io.File;

/**
 * 版本更新弹窗
 *
 * @author zhangliyang
 * @date 2018/8/6-上午9:17
 */
public class DownloadDialog {

    private final Activity mActivity;
    private Layer upperLayer = null;
    private final boolean isForce;
    private boolean isAutoInstall = true;

    private ProgressBar progressBar;
    private TextView tvProgress;
    private TextView tvApkSize;
    private TextView tvState;
    private File mApk;

    public static DownloadDialog with(Activity activity, boolean isForce, String url) {
        Utils.init(activity);
        return new DownloadDialog(activity, isForce, url);
    }

    private DownloadDialog(Activity activity, boolean isForce, String url) {
        Utils.init(activity);
        this.mActivity = activity;
        this.isForce = isForce;
        showDialog();
        startDownload(url);
    }

    public DownloadDialog setAutoInstall(boolean autoInstall) {
        isAutoInstall = autoInstall;
        return this;
    }

    private void startDownload(String url) {
        DownloadUtils.download(url, new DownloadUtils.DownloadListener() {
            @Override
            public void onPreExecute() {
                preDownload();
            }

            @Override
            public void onDownloadLength(final int length) {
                if (tvApkSize != null) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvApkSize.setText(FileUtils.formatSize(length));
                        }
                    });
                }
            }

            @Override
            public void onProgressUpdate(int progress) {
                setProgress(progress);
            }

            @Override
            public void onPostExecute(File apk) {
                mApk = apk;
                if (isAutoInstall && tvState != null) {
                    tvState.performClick();
                }
            }


        });
    }

    private void showDialog() {
        upperLayer = Upper.dialog(mActivity)
                .contentView( R.layout.basic_ui_dialog_download)
                .gravity(Gravity.CENTER)
                .backgroundDimDefault()
                .cancelableOnTouchOutside(false)
                .cancelableOnClickKeyBack(false)
                .bindData(new Layer.DataBinder() {
                    @Override
                    public void bindData(Layer layer) {
                        progressBar = layer.getView( R.id.basic_ui_pb_dialog_download);
                        tvProgress = layer.getView( R.id.basic_ui_tv_dialog_download_progress);
                        tvApkSize = layer.getView( R.id.basic_ui_tv_dialog_download_apk_size);
                        tvState = layer.getView( R.id.basic_ui_tv_dialog_download_state);

                    }
                })
                .onClick(new Layer.OnClickListener() {
                    @Override
                    public void onClick(Layer layer, View v) {
                        if (mApk == null) {
                            return;
                        }
                        if (!isForce) {
                            dismiss();
                        }
                        DownloadUtils.installApk(mActivity, mApk);
                    }
                }, R.id.basic_ui_tv_dialog_download_state);
        upperLayer.show();
    }

    private void preDownload() {
        if (progressBar != null) {
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }
        if (tvApkSize != null) {
            tvApkSize.setText("0B");
        }
        if (tvProgress != null) {
            tvProgress.setText("0");
        }
        if (tvState != null) {
            tvState.setText(com.zlylib.upperdialog.R.string.basic_ui_dialog_download_state_downloading);
        }
    }

    private void setProgress(int progress) {
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
        if (tvProgress != null) {
            tvProgress.setText("" + progress);
        }
        if (progress >= 100) {
            if (tvState != null) {
                tvState.setText(com.zlylib.upperdialog.R.string.basic_ui_dialog_download_state_install);
                tvState.setTextColor(ResUtils.getColor(mActivity, com.zlylib.upperdialog.R.color.text_main));
            }
        }
    }

    private void dismiss() {
        if (upperLayer != null) {
            upperLayer.dismiss();
        }
    }
}
