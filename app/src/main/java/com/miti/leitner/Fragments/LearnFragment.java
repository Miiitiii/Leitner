package com.miti.leitner.Fragments;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.miti.leitner.DataBase.DatabaseAccess;
import com.miti.leitner.Flip.AnimationFactory;
import com.miti.leitner.Model.NewWord;
import com.miti.leitner.R;
import com.miti.leitner.main;
import com.miti.leitner.utils.utils;

import java.util.List;

public class LearnFragment extends Fragment
{
    Button button;
    ViewFlipper viewFlipper;
    TextView English,Persian,numText1,numText2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_learn , container , false);

        viewFlipper = view.findViewById(R.id.flipper);
        button = view.findViewById(R.id.accp_btn);
        English = view.findViewById(R.id.english_text);
        Persian = view.findViewById(R.id.persian_txt);
        numText1 = view.findViewById(R.id.texts_number1);
        numText2 = view.findViewById(R.id.texts_number2);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();
        final List<NewWord> ls = databaseAccess.getWords(utils.grade,utils.WORDS);
        databaseAccess.close();
        final String nums = "1/"+ ls.size();

        English.setText(ls.get(0).getEnglishMeaning());
        Persian.setText(ls.get(0).getPersianMeaning());
        numText1.setText(nums);
        numText2.setText(nums);
        final int[] i = {0};
        final int[] j = {2};

        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationFactory.flipTransition(viewFlipper, AnimationFactory.FlipDirection.RIGHT_LEFT,600);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                final String nums1 = j[0] +"/"+ ls.size();
                if (button.getText().toString().equals("بعدی"))
                {
                    j[0]++;
                    i[0]++;
                    English.setText(ls.get(i[0]).getEnglishMeaning());
                    Persian.setText(ls.get(i[0]).getPersianMeaning());
                    numText1.setText(nums1);
                    numText2.setText(nums1);

                    if (j[0]-1 == ls.size())
                    {
                        button.setText("اتمام");
                    }
                }
                else if (button.getText().toString().equals("اتمام"))
                {
                    Intent intent = new Intent();
                    getActivity().setResult(Activity.RESULT_OK,intent);
                    getActivity().finish();
                }

            }
        });

        return view;
    }
}
