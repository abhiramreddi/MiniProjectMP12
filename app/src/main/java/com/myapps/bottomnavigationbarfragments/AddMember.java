package com.myapps.bottomnavigationbarfragments;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMember extends AppCompatActivity {
    DBHelper DB;
    EditText name;
    Button addMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        DB = new DBHelper(this);
        name = findViewById(R.id.editTextTextPersonName2);
        addMember = findViewById(R.id.button5);
        addMember.setOnClickListener(view -> {
            String tableName = name.getText().toString();
            if (tableName.length() > 0) {
                Cursor cursor = DB.selectAll(tableName);
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Member already existed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                boolean a = DB.insert(tableName);
                if (a) {
                    Toast.makeText(getApplicationContext(), tableName + " Added", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}