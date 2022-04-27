package com.myapps.bottomnavigationbarfragments;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pay extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] courses;
    EditText amount;
    String name;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Spinner spin = findViewById(R.id.spinner);

        spin.setOnItemSelectedListener(this);
        save=findViewById(R.id.button3);
        DBHelper DB=new DBHelper(this);
        Cursor c=DB.selectAll();
        if(c==null)
        {
            Toast.makeText(getApplicationContext(),"Add members to pay",Toast.LENGTH_SHORT).show();
            courses =new String[1];
            courses[0]="Select name";
            ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,courses);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(ad);

            return;
        }

        courses =new String[c.getCount()+1];
        courses[0]="Select name";
        for(int i=1;i<c.getCount()+1;i++){
            c.moveToNext();
            courses[i]=c.getString(0);
        }




        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        name=courses[i];

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void save(View view) {

        amount=findViewById(R.id.editTextTextPersonName);
        if(amount.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(),"Please enter amount",Toast.LENGTH_SHORT).show();
            return;}
        int money=Integer.parseInt(amount.getText().toString());
        DBHelper DB=new DBHelper(this);
        DB.add(money,name);

    }
}
