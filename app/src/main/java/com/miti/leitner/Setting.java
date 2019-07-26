package com.miti.leitner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.miti.leitner.utils.utils;

import static com.miti.leitner.utils.utils.MyPREFERENCES;

public class Setting extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    Window window;

    private Switch night;

    TextView night_txt;

    RelativeLayout setting_lay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        night = findViewById(R.id.night_switch);
        setting_lay = findViewById(R.id.setting_lay);
        night_txt = findViewById(R.id.night_txt);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/IRAN Sans Regular.ttf");
        night_txt.setTypeface(face);


        darking();
        if (utils.dark.equals("1"))
            night.setChecked(true);
        else night.setChecked(false);

        night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    utils.dark = "1";
                    sharedpreferences.edit().putString("dark", "1").apply();
                    darking();
                } else {
                    utils.dark = "0";
                    sharedpreferences.edit().putString("dark", "0").apply();
                    darking();
                }
            }
        });

    }

    void darking() {
        if (utils.dark.equals("1")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(Setting.this, R.color.dark_toolbar));
            }
            setting_lay.setBackgroundColor(getResources().getColor(R.color.dark_main_bg));
        } else if (utils.dark.equals("0")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(Setting.this, R.color.toolbar));
            }
            setting_lay.setBackgroundColor(getResources().getColor(R.color.main_bg));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

}
