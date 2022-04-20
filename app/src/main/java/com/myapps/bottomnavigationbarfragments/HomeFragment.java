package com.myapps.bottomnavigationbarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {


    DataBaseHelper dataBaseHelper;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        dataBaseHelper = new DataBaseHelper(getContext());
        int a = dataBaseHelper.getCredit();
        int b = dataBaseHelper.getDebit();
        TextView textView = view.findViewById(R.id.testTextview);
        textView.setText(String.valueOf(a-b));

        Toast.makeText(requireActivity().getApplicationContext(), String.valueOf(a), Toast.LENGTH_SHORT).show();

        return view;
    }
}