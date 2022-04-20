package com.myapps.bottomnavigationbarfragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;

import java.util.Calendar;
import java.util.Objects;


public class LedgerFragment extends Fragment {


    public LedgerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    AutoCompleteTextView Category;
    ArrayAdapter<String> adapterCategories;
    String[] categories = {"FOOD", "SHOPPING", "PHONE", "HEALTH", "GROCERIES", "TRAVEL", "FUEL", "EDUCATION", "ELECTRICITY", "BILLS", "HOUSING", "OTHER"};
    SingleSelectToggleGroup type;
    TextView tvDate, tvTime;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    int tHour, tMinute;

    TextInputEditText amountView;
    TextInputEditText descView;
    String typeForSql;

    Button add;

    String category;
    String dateForTextView;


    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ledger, container, false);

        // working with the type | credit or debit
        type = view.findViewById(R.id.Demo2);
        type.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.credit:
                    typeForSql = "CREDIT";
                    break;
                case R.id.debit:
                    typeForSql = "DEBIT";
                    break;
            }
        });

        amountView = view.findViewById(R.id.inputAmount);
        descView = view.findViewById(R.id.inputDesc);

        Category = view.findViewById(R.id.category);
        adapterCategories = new ArrayAdapter<>(getContext(), R.layout.list_item, categories);
        Category.setAdapter(adapterCategories);
        Category.setOnItemClickListener((adapterView, view1, i, l) -> {
            InputMethodManager in = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view1.getApplicationWindowToken(), 0);
            category = adapterView.getItemAtPosition(i).toString();
        });

        tvDate = view.findViewById(R.id.tvDatePicker);
        tvDate.setOnClickListener(view12 -> {
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(getActivity(), (datePicker, year, month, day) -> {
                dateForTextView = day + "/" + (month + 1) + "/" + year;
                tvDate.setText(dateForTextView);
            }, year, month, dayOfMonth);
            datePickerDialog.show();

        });

        tvTime = view.findViewById(R.id.tvTimePicker);
        tvTime.setOnClickListener(view13 -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), (timePicker, hourOfDay, minute) -> {
                tHour = hourOfDay;
                tMinute = minute;

                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0, tHour, tMinute);
                tvTime.setText(DateFormat.format("hh:mm aa", calendar));

            }, 12, 0, false);

            timePickerDialog.updateTime(tHour, tMinute);
            timePickerDialog.show();
        });




        add = view.findViewById(R.id.outlinedButton);
        add.setOnClickListener(view14 -> {
            String type = typeForSql;
            String amount = Objects.requireNonNull(amountView.getText()).toString();
            String desc = Objects.requireNonNull(descView.getText()).toString();
            String categ = category;
            String date = dateForTextView;
            String time = tvTime.getText().toString();

            try {
                if(type.length() == 0 || amount.length() == 0 || desc.length() == 0 || categ.length() == 0 || date.length() == 0|| time.length() == 0){
                    Toast.makeText(getActivity(), "Fill all the fields", Toast.LENGTH_SHORT).show();

                }else{
                    TransactionModel transactionModel;

                    try {
                        transactionModel = new TransactionModel(-1, Integer.parseInt(amount), desc, categ, type, date, time);
                    } catch (Exception e) {
                        transactionModel = new TransactionModel(-1, 0, "not specified", "not specified", "not specified", "not specified", "not specifies");
                    }

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                    boolean success = dataBaseHelper.addOne(transactionModel);
                    if (success)
                        Toast.makeText(getActivity(), "Data Added Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Data Not Added", Toast.LENGTH_SHORT).show();
                }
            }catch (NullPointerException nullPointerException){
                Toast.makeText(requireActivity().getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            }

        });


        return view;
    }


}