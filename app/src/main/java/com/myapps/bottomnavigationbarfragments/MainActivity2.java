package com.myapps.bottomnavigationbarfragments;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    Button new_trip;
    Button existing_trip;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new_trip=findViewById(R.id.button);
        existing_trip=findViewById(R.id.button2);
        DB=new DBHelper(this);
        new_trip.setOnClickListener(view -> {
            DB.delete();
            Intent i=new Intent(getApplicationContext(),Home.class);
            startActivity(i);

        });
        existing_trip.setOnClickListener(view -> {
            Intent i=new Intent(getApplicationContext(),Home.class);
            startActivity(i);
        });

    }
}