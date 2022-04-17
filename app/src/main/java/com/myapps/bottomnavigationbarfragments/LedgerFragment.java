package com.myapps.bottomnavigationbarfragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.sql.Array;
import java.util.Objects;


public class LedgerFragment extends Fragment {



    public LedgerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ledger, container, false);

        AutoCompleteTextView Category, Type;
        ArrayAdapter<String> adapterCategories, adapterTypes;
        String[] categories = {"FOOD", "SHOPPING","PHONE", "HEALTH", "GROCERIES", "TRAVEL", "FUEL", "EDUCATION", "ELECTRICITY", "BILLS", "HOUSING", "OTHER"};

        Category = view.findViewById(R.id.category);
        adapterCategories = new ArrayAdapter<String>(getContext(), R.layout.list_item, categories);
        Category.setAdapter(adapterCategories);
        Category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager in = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "ITEM : "+item, Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }
}