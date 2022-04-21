package com.myapps.bottomnavigationbarfragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    Button addMember,pay,showAllTransactions,split;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addMember=findViewById(R.id.add);
        pay=findViewById(R.id.pay);
        showAllTransactions=findViewById(R.id.transactions);
        split=findViewById(R.id.split);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), AddMember.class);
                startActivity(i);


            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Pay.class);
                startActivity(i);
            }
        });
        showAllTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ShowAllTransactions.class);
                startActivity(i);
            }
        });
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Split.class);
                startActivity(i);
            }
        });

    }
}