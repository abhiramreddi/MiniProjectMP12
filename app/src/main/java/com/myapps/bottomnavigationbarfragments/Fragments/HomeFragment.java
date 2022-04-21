package com.myapps.bottomnavigationbarfragments.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.myapps.bottomnavigationbarfragments.LedgerDOA.DataBaseHelper;
import com.myapps.bottomnavigationbarfragments.MainActivity2;
import com.myapps.bottomnavigationbarfragments.R;

import java.util.Arrays;


public class HomeFragment extends Fragment {


    DataBaseHelper dataBaseHelper;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView imageView = view.findViewById(R.id.button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), MainActivity2.class);
                startActivity(in);

            }
        });

        ImageView imageView1 = view.findViewById(R.id.button1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new VisualizeFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, fragment).commit();
            }
        });


        dataBaseHelper = new DataBaseHelper(getContext());
        int a = dataBaseHelper.getCredit();
        int b = dataBaseHelper.getDebit();
        TextView textView = view.findViewById(R.id.testTextview);
        textView.setText(String.valueOf(a - b));
        TextView textView1 = view.findViewById(R.id.balance_Status);
        int unicode = 0x1F9D0;
        if (a - b <= 0)
            textView1.setText("low balance " + String.valueOf(Character.toChars(unicode)));
        else
            textView1.setText("");
        return view;
    }
}