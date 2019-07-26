package com.miti.leitner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.miti.leitner.Model.NewWord;
import com.miti.leitner.utils.utils;

public class AddWords extends AppCompatActivity {

    RelativeLayout add_word_lay;
    Button beginner, normal, hard, add_btn;
    TextInputEditText english_txt_new, persian_txt_new;
    private String[] grade = {null};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        add_word_lay = findViewById(R.id.add_word_lay);
        beginner = findViewById(R.id.beginner_word);
        normal = findViewById(R.id.normal_word);
        hard = findViewById(R.id.hard_word);
        add_btn = findViewById(R.id.add_btn);
        english_txt_new = findViewById(R.id.english_txt_new);
        persian_txt_new = findViewById(R.id.persian_txt_new);

        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beginner.setBackground(getResources().getDrawable(R.drawable.choose_num_bg_dark));
                normal.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));
                hard.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));

                grade[0] = "beginner";
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                normal.setBackground(getResources().getDrawable(R.drawable.choose_num_bg_dark));
                beginner.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));
                hard.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));

                grade[0] = "normal";
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hard.setBackground(getResources().getDrawable(R.drawable.choose_num_bg_dark));
                normal.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));
                beginner.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));

                grade[0] = "hard";
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddWords.this, "کلمه جدید اضافه شد", Toast.LENGTH_SHORT).show();
                utils.newWords.add(new NewWord(persian_txt_new.getText().toString(),english_txt_new.getText().toString(),grade[0]));
                persian_txt_new.setText(null);
                english_txt_new.setText(null);
                beginner.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));
                normal.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));
                hard.setBackground(getResources().getDrawable(R.drawable.choose_num_bg));

            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
