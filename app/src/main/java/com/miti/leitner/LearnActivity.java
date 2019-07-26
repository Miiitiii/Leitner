package com.miti.leitner;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.miti.leitner.Fragments.ChooseFragment;
import com.miti.leitner.Fragments.LearnFragment;
import com.miti.leitner.Fragments.QuizFragment;
import com.miti.leitner.utils.utils;


public class LearnActivity extends AppCompatActivity
{
    public static final String QUIZ_FRAGMENT = "quiz_fragment";
    public static final String CHOOSE_FRAGMENT = "choose_fragment";
    public static final String LEARN_FRAGMENT = "learn_fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_layout);



        QuizFragment savedfragment = (QuizFragment) getSupportFragmentManager().findFragmentByTag(QUIZ_FRAGMENT);
        LearnFragment savedlearnfragment = (LearnFragment)getSupportFragmentManager().findFragmentByTag(LEARN_FRAGMENT);
        ChooseFragment savedchoosefragment = (ChooseFragment)getSupportFragmentManager().findFragmentByTag(CHOOSE_FRAGMENT);

        if (savedfragment == null && utils.quiz.equals("false") )
        {
            QuizFragment fragment = new QuizFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeHolder, fragment, QUIZ_FRAGMENT);
            fragmentTransaction.commit();

        }

        else if (savedchoosefragment == null && utils.quiz.equals("true") && utils.choose.equals("false"))
        {
            ChooseFragment fragment = new ChooseFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.placeHolder, fragment, CHOOSE_FRAGMENT);
            fragmentTransaction.commit();

        }
        else if (savedlearnfragment == null && utils.quiz.equals("true") && utils.choose.equals("true"))
        {
            LearnFragment fragment = new LearnFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.placeHolder, fragment, LEARN_FRAGMENT);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onBackPressed() {

    }
}
