package com.miti.leitner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miti.leitner.DataBase.DatabaseAccess;
import com.miti.leitner.Model.NewWord;
import com.miti.leitner.utils.utils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import static com.miti.leitner.utils.utils.MyPREFERENCES;
import static com.miti.leitner.utils.utils.dark;

public class main extends AppCompatActivity {
    SharedPreferences sp;

    boolean exit = false;
    NotificationBadge notificationBadge;
    private CardView learn, new_words, words, setting, connect;
    private TextView learntxt, new_wordstxt, wordstxt, settingtxt, connecttxt, words_num;
    private RelativeLayout main_lay;
    private ImageView toolbar_img;
    private Toolbar main_toolbar;
    public static final int SETTING_REQUEST_CODE = 1234;
    public static final int LEARNING_REQUEST_CODE = 12345;
    public static final int NEW_WORD_REQUEST_CODE = 1000;
    List<NewWord> newWords;


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        exit = false;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        db();

        learn = findViewById(R.id.learn);
        new_words = findViewById(R.id.new_word);
        words = findViewById(R.id.words);
        setting = findViewById(R.id.setting);
        connect = findViewById(R.id.connect);
        main_lay = findViewById(R.id.main_lay);
        main_toolbar = findViewById(R.id.toolbar);
        toolbar_img = findViewById(R.id.toolbar_img);
        notificationBadge = findViewById(R.id.badge);
        learntxt = findViewById(R.id.learn_txt);
        connecttxt = findViewById(R.id.connect_txt);
        new_wordstxt = findViewById(R.id.new_word_txt);
        wordstxt = findViewById(R.id.old_word_txt);
        settingtxt = findViewById(R.id.setting_txt);
        words_num = findViewById(R.id.words_num);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/IRAN Sans Regular.ttf");

        darking();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = this.getLayoutInflater().inflate(R.layout.activity_connect,null,false);
        TextView email = view.findViewById(R.id.Email);
        TextView linkedin = view.findViewById(R.id.linkedin);
        TextView version = view.findViewById(R.id.version);
        email.setTypeface(face);
        linkedin.setTypeface(face);
        version.setTypeface(face);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(main.this, LearnActivity.class), LEARNING_REQUEST_CODE);
            }
        });

        new_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(main.this, AddWords.class),NEW_WORD_REQUEST_CODE);
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                dialog.dismiss();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(main.this, Setting.class), SETTING_REQUEST_CODE);
            }
        });

        learntxt.setTypeface(face);
        connecttxt.setTypeface(face);
        new_wordstxt.setTypeface(face);
        settingtxt.setTypeface(face);
        wordstxt.setTypeface(face);
        words_num.setTypeface(face);

        notificationBadge.setAnimationEnabled(true);
        notificationBadge.setText(utils.badge);

        words_num.setText(utils.words_num);


    }

    @Override
    public void onBackPressed() {
        if (exit) {
            super.onBackPressed();
            System.exit(0);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        }, 3000);
        this.exit = true;

        Toast.makeText(this, "Please Click BACK To Exit", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_REQUEST_CODE) {
            darking();
        } else if (requestCode == LEARNING_REQUEST_CODE) {

        }
        else if (requestCode == NEW_WORD_REQUEST_CODE)
        {

        }
    }

    private void darking() {
        if (dark.equals("1")) {
            main_lay.setBackgroundColor(getResources().getColor(R.color.dark_main_bg));
            toolbar_img.setImageResource(R.drawable.bar_dark);
            main_toolbar.setBackgroundColor(getResources().getColor(R.color.dark_toolbar));
        } else if (dark.equals("0")) {
            main_lay.setBackgroundColor(getResources().getColor(R.color.main_bg));
            toolbar_img.setImageResource(R.drawable.bar2);
            main_toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar));
        }
    }

    void db()
    {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        int i =0;
        List<String> ls = databaseAccess.getman(1,15);

        while (i <ls.size())
        {
            Log.d("listt", ls.get(i));
            i++;
        }
        databaseAccess.close();
    }
}
