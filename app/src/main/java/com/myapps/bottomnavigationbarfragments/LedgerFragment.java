package com.myapps.bottomnavigationbarfragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;


public class LedgerFragment extends Fragment {


    public LedgerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    AutoCompleteTextView Category, Type;
    ArrayAdapter<String> adapterCategories, adapterTypes;
    String[] categories = {"FOOD", "SHOPPING", "PHONE", "HEALTH", "GROCERIES", "TRAVEL", "FUEL", "EDUCATION", "ELECTRICITY", "BILLS", "HOUSING", "OTHER"};
    SingleSelectToggleGroup type;

    TextInputEditText amountView;
    TextInputEditText descView;
    String typeForSql;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ledger, container, false);

        // working with the type | credit or debit
        type = view.findViewById(R.id.Demo2);
        type.setOnCheckedChangeListener(new SingleSelectToggleGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(SingleSelectToggleGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.credit:
                        typeForSql = "CREDIT";
                        break;
                    case R.id.debit:
                        typeForSql = "DEBIT";
                        break;
                }
            }
        });

        amountView = view.findViewById(R.id.inputAmount);
        descView = view.findViewById(R.id.inputDesc);

        Category = view.findViewById(R.id.category);
        adapterCategories = new ArrayAdapter<String>(getContext(), R.layout.list_item, categories);
        Category.setAdapter(adapterCategories);
        Category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager in = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "ITEM : " + item, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}