package com.miti.leitner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.miti.leitner.utils.utils;

import static com.miti.leitner.utils.utils.MyPREFERENCES;

public class login extends AppCompatActivity
{
    private static final int PICK_FILE_REQUEST = 1111;
    private TextView txt;
    private TextInputEditText name;
    private Button login;
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/IRAN Sans Regular.ttf");

        txt = findViewById(R.id.tv);
        name = findViewById(R.id.login_tv);
        login = findViewById(R.id.btn_login);
        txt.setTypeface(face);
        name.setTypeface(face);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("name",name.getText().toString());
                editor.apply();


                utils.NAME = sharedpreferences.getString("name",null);

                startActivity(new Intent(login.this,main.class));
                finish();
            }
        });


    }

}
