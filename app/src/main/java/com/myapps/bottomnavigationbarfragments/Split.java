package com.myapps.bottomnavigationbarfragments;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Split extends AppCompatActivity {

    String ans[];
    int ansIndex;
    StringBuffer s;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        ansIndex=-1;
        ans=new String[100];
        s=new StringBuffer();
        DBHelper DB = new DBHelper(this);
        Cursor cursor=DB.selectAll();
        if(cursor==null){

            ListView l=findViewById(R.id.list);
            String noValues[]={"No payments to split"};
            ArrayAdapter<String> arr;
            arr
                    = new ArrayAdapter<String>(
                    this,
                    R.layout.support_simple_spinner_dropdown_item,
                    noValues);
            l.setAdapter(arr);
            return;

        }

        int size=cursor.getCount();

        int sum=0;
        while(cursor.moveToNext()){
            sum=sum+cursor.getInt(1);
        }

        String lessPeople[] =new String[100];
        String morePeople[]=new String[100];
        double lessMoney[]=new double[100];
        double moreMoney[]=new double[100];


        double perUnit = Math.round((sum/(double)size)*100.0)/100.0;

        int lessPeopleIndex = 0;
        int morePeopleIndex = 0;
        int moreMoneyIndex = 0;
        int lessMoneyIndex = 0;
        cursor.moveToPosition(-1);

        while (cursor.moveToNext()) {

            if(cursor.getInt(1)<perUnit)
            {
                lessPeople[lessPeopleIndex]=cursor.getString(0);

                lessPeopleIndex++;
                lessMoney[lessMoneyIndex]=(double)cursor.getInt(1);
                lessMoneyIndex++;
            }
            else if(cursor.getInt(1)>perUnit)
            {

                moreMoney[moreMoneyIndex]=(double)cursor.getInt(1);
                morePeople[moreMoneyIndex]=cursor.getString(0);
                moreMoneyIndex++;
                morePeopleIndex++;

            }

        }
        //  Toast.makeText(getApplicationContext(), String.valueOf(morePeople[0]), Toast.LENGTH_SHORT).show();
        if(lessPeopleIndex==0){
            ListView l = findViewById(R.id.list);
            ArrayAdapter<String> arr;
            String hi[]=new String[1];
            hi[0]="No splitting required";
            arr
                    = new ArrayAdapter<String>(
                    this,
                    R.layout.support_simple_spinner_dropdown_item,
                    hi);
            l.setAdapter(arr);
            //Toast.makeText(getApplicationContext(), "vg", Toast.LENGTH_SHORT).show();
            return;

        }


        int pointer=0;
        int fuck=lessMoneyIndex;
        for(int i=0;i<fuck;i++)
        {
            double send=perUnit-lessMoney[i];
            send=Math.round(send*100.0)/100.0;
            double recieve=moreMoney[pointer]-perUnit;
            recieve=Math.round(recieve*100.0)/100.0;
//            Toast.makeText(getApplicationContext(), String.valueOf(send), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(), String.valueOf(recieve), Toast.LENGTH_SHORT).show();
            while(true){
                if(send>recieve) {
                    lessMoney[i] = lessMoney[i] + recieve;
                    //Toast.makeText(getApplicationContext(), String.valueOf(lessMoney[i]), Toast.LENGTH_SHORT).show();
                    moreMoney[pointer] = perUnit;

                    store(lessPeople[i],recieve,morePeople[pointer]);
                    // Toast.makeText(getApplicationContext(), lessPeople[i]+" owes "+morePeople[pointer]+" rs "+recieve, Toast.LENGTH_SHORT).show();
                    pointer=pointer+1;
                    send=send-recieve;
                    send=Math.round(send*100.0)/100.0;
                    if(send<0.2)
                        break;
                    recieve=moreMoney[pointer]-perUnit;
                    recieve=Math.round(recieve*100.0)/100.0;





                }
                else if(send<recieve){
                    moreMoney[pointer]=moreMoney[pointer]-send;
                    lessMoney[i]=lessMoney[i]+send;
                    store(lessPeople[i],send,morePeople[pointer]);
                    //  Toast.makeText(getApplicationContext(), lessPeople[i]+" owes "+morePeople[pointer]+" rs "+send, Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                {
                    moreMoney[pointer]=moreMoney[pointer]-send;
                    lessMoney[i]=lessMoney[i]+send;
                    store(lessPeople[i],send,morePeople[pointer]);
                    //  Toast.makeText(getApplicationContext(), lessPeople[i]+" owes "+morePeople[pointer]+" rs "+send, Toast.LENGTH_SHORT).show();
                    pointer++;
                    break;
                }
            }
        }
        display();

    }
    public void store(String a,double amount,String b)
    {
        ansIndex++;
        ans[ansIndex]=a+" owes "+ amount+" rs to "+b;

        //Toast.makeText(getApplicationContext(),s.toString(),Toast.LENGTH_SHORT).show();

    }
    public  void display(){
        String hi[]=new String[ansIndex+1];
        for (int i=0;i<=ansIndex;i++)
        {
            hi[i]=ans[i];
        }

        ListView l = findViewById(R.id.list);
        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                hi);
        l.setAdapter(arr);

    }
}




