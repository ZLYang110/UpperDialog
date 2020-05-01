package com.zlyandroid.upperdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zlylib.upperdialog.Upper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                Upper.dialog(MainActivity.this)
                .contentView(R.layout.dialog_normal)
                .backgroundDimDefault()
                .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                .show();
    }
}
