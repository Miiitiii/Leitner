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

import com.miti.leitner.R;
import com.miti.leitner.utils.utils;

import static com.miti.leitner.LearnActivity.LEARN_FRAGMENT;
import static com.miti.leitner.utils.utils.MyPREFERENCES;

public class ChooseFragment extends Fragment
{

    private Button btn_15,btn_20,btn_25,btn_beginner,btn_normal,btn_hard,accept;
    SharedPreferences sharedpreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final int[] nums = {0};
        final int[] grade = {0};

        final View view = inflater.inflate(R.layout.fragment_choose , container , false);
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/IRAN Sans Regular.ttf");

        btn_15 = view.findViewById(R.id.btn_15);
        btn_20 = view.findViewById(R.id.btn_20);
        btn_25 = view.findViewById(R.id.btn_25);
        btn_beginner = view.findViewById(R.id.beginner);
        btn_hard = view.findViewById(R.id.hard);
        btn_normal = view.findViewById(R.id.normal);
        accept = view.findViewById(R.id.accept_btn);

        btn_15.setTypeface(typeface);
        btn_20.setTypeface(typeface);
        btn_25.setTypeface(typeface);
        btn_beginner.setTypeface(typeface);
        btn_normal.setTypeface(typeface);
        btn_hard.setTypeface(typeface);
        accept.setTypeface(typeface);

        btn_15.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btn_15.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg_dark));
            btn_20.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));
            btn_25.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));
            nums[0] = 15;
        }
    });
        btn_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_20.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg_dark));
                btn_15.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));
                btn_25.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));

                nums[0] = 20;
            }
        });
        btn_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_25.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg_dark));
                btn_20.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));
                btn_15.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));

                nums[0] = 25;
            }
        });

        btn_beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_beginner.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg_dark));
                btn_normal.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));
                btn_hard.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));

                grade[0] = 1;
            }
        });
        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_normal.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg_dark));
                btn_beginner.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));
                btn_hard.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));

                grade[0] = 2;
            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_hard.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg_dark));
                btn_normal.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));
                btn_beginner.setBackground(getActivity().getResources().getDrawable(R.drawable.choose_num_bg));

                grade[0] = 3;
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                editor.putString("choose","true");
                editor.apply();
                utils.choose = "true";
                utils.WORDS = nums[0];
                utils.grade = grade[0];

                LearnFragment savedlearnfragment = (LearnFragment)getActivity().getSupportFragmentManager().findFragmentByTag(LEARN_FRAGMENT);

                if (savedlearnfragment == null && utils.choose.equals("true"))
                {
                    LearnFragment fragment = new LearnFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.placeHolder, fragment, LEARN_FRAGMENT);
                    fragmentTransaction.commit();
                }

            }
        });


        return view;
    }
}
