package com.miti.leitner.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miti.leitner.MainActivity;
import com.miti.leitner.R;
import com.miti.leitner.utils.utils;

import static com.miti.leitner.LearnActivity.CHOOSE_FRAGMENT;
import static com.miti.leitner.utils.utils.MyPREFERENCES;

public class QuizFragment extends Fragment
{
    SharedPreferences sharedpreferences;

    private TextView text_ask,text_nums;
    private EditText answer;
    private Button accept;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz,container,false);

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        accept = view.findViewById(R.id.btn_ask);
        text_ask = view.findViewById(R.id.text_ask);
        text_nums = view.findViewById(R.id.texts_number);
        answer = view.findViewById(R.id.answer_ask);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/IRAN Sans Regular.ttf");

        accept.setTypeface(typeface);
        text_nums.setTypeface(typeface);
        answer.setTypeface(typeface);
        text_ask.setTypeface(typeface);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("quiz","true");
                editor.apply();
                utils.quiz = "true";

                ChooseFragment savedchoosefragment = (ChooseFragment) getActivity().getSupportFragmentManager().findFragmentByTag(CHOOSE_FRAGMENT);

                if (savedchoosefragment == null && utils.quiz.equals("true") && utils.choose.equals("false"))
                {

                    ChooseFragment fragment = new ChooseFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.placeHolder, fragment, CHOOSE_FRAGMENT);
                    fragmentTransaction.commit();

                }
            }
        });



        return view;
    }
}
