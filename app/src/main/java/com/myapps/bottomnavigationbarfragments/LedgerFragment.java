package com.myapps.bottomnavigationbarfragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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

    AutoCompleteTextView Category, Type;
    ArrayAdapter<String> adapterCategories, adapterTypes;
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

    public static final String DATABASE_NAME = "Ledger.db";
    SQLiteDatabase mDatabase;

    String category;
    String dateForTextView;


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
                category = adapterView.getItemAtPosition(i).toString();
            }
        });

        tvDate = view.findViewById(R.id.tvDatePicker);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateForTextView = day + "/" + (month + 1) + "/" + year;
                        tvDate.setText(dateForTextView);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();

            }
        });

        tvTime = view.findViewById(R.id.tvTimePicker);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        tHour = hourOfDay;
                        tMinute = minute;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0, 0, 0, tHour, tMinute);
                        tvTime.setText(DateFormat.format("hh:mm aa", calendar));

                    }
                }, 12, 0, false);

                timePickerDialog.updateTime(tHour, tMinute);
                timePickerDialog.show();
            }
        });


        mDatabase = requireActivity().openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        createEmployeeTable();


        add = view.findViewById(R.id.outlinedButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = typeForSql;
                int amount = Integer.parseInt(Objects.requireNonNull(amountView.getText()).toString());
                String desc = Objects.requireNonNull(descView.getText()).toString();
                String categ = category;
                String date = dateForTextView;
                String time = tvTime.getText().toString();


                if (type.isEmpty() || desc.isEmpty() || categ.isEmpty() || date.isEmpty() || time.isEmpty() || amount == 0) {

                    Toast.makeText(requireActivity().getApplicationContext(), "Fil the form", Toast.LENGTH_SHORT).show();

                } else {

                    String insertSQL = "INSERT INTO Record \n" +
                            "(Type, Amount, Description, Category, Date, Time)\n" +
                            "VALUES \n" +
                            "(?, ?, ?, ?, ?, ?);";

                    //using the same method execsql for inserting values
                    //this time it has two parameters
                    //first is the sql string and second is the parameters that is to be binded with the query
                    mDatabase.execSQL(insertSQL, new String[]{type, String.valueOf(amount), desc, categ, date, time});

                    Toast.makeText(requireActivity().getApplicationContext(), "Great! Record Added", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;
    }

    private void createEmployeeTable() {
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS Record " +
                "(\n" +
                "    id INTEGER NOT NULL CONSTRAINT record_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    Amount int NOT NULL,\n" +
                "    Type varchar(20) NOT NULL,\n" +
                "    Description varchar(200) NOT NULL,\n" +
                "    Category varchar(20) NOT NULL,\n" +
                "    Date varchar(20) NOT NULL,\n" +
                "    Time Varchar(20) NOT NULL\n" +

                ");"

        );
    }
}