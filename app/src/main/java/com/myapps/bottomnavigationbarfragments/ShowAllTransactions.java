package com.myapps.bottomnavigationbarfragments;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowAllTransactions extends AppCompatActivity {
    DBHelper DB;
    String name[];
    int money[];
    String noValues[];
    int nameIndex;
    int moneyIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_transactions);
        name=new String[100];
        noValues=new String[1];
        moneyIndex=-1;
        nameIndex=-1;
        ListView l = findViewById(R.id.list);
        DB=new DBHelper(this);
        noValues[0]="No payments till now";
        Cursor c=DB.selectAll();
        if(c==null)
        {
            ArrayAdapter<String> arr;
            arr
                    = new ArrayAdapter<String>(
                    this,
                    R.layout.support_simple_spinner_dropdown_item,
                    noValues);
            l.setAdapter(arr);
        }
        else
        {

            while(c.moveToNext()){
                nameIndex++;
                name[nameIndex]=c.getString(0)+"  "+"paid " +"rs "+ c.getInt(1);
                //Toast.makeText(getApplicationContext(), name[nameIndex], Toast.LENGTH_SHORT).show();


            }
            String ans[]=new String[nameIndex+1];
            for (int i=0;i<=nameIndex;i++)
            {
                ans[i]=name[i];
            }
            ArrayAdapter<String> arr;
            arr
                    = new ArrayAdapter<String>(
                    this,
                    R.layout.support_simple_spinner_dropdown_item,
                    ans);
            l.setAdapter(arr);


        }

    }
}