package com.myapps.bottomnavigationbarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class VisualizeFragment extends Fragment {


    public int[] vals = DataBaseHelper.values;


    public VisualizeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_visualize, container, false);


            try {

                PieChart chart = view.findViewById(R.id.piechart);
                String[] categs = {"Food", "Shopping", "Phone", "Health", "Groceries", "Travel", "Fuel", "Education", "Electricity", "Bills", "Housing", "Other"};

                ArrayList<PieEntry> barEntries = new ArrayList<>();
                for(int i=0;i<12;i++){
                    if(vals[i] != 0){
                        barEntries.add(new PieEntry(vals[i], categs[i]));
                    }
                }
//                barEntries.add(new PieEntry(vals[0], "Food"));
//                barEntries.add(new PieEntry(vals[1], "Shopping"));
//                barEntries.add(new PieEntry(vals[2], "Phone"));
//                barEntries.add(new PieEntry(vals[3], "Health"));
//                barEntries.add(new PieEntry(vals[4], "Groceries"));
//                barEntries.add(new PieEntry(vals[5], "Travel"));
//                barEntries.add(new PieEntry(vals[6], "Fuel"));
//                barEntries.add(new PieEntry(vals[7], "Education"));
//                barEntries.add(new PieEntry(vals[8], "Electricity"));
//                barEntries.add(new PieEntry(vals[9], "Bills"));
//                barEntries.add(new PieEntry(vals[10], "Housing"));
//                barEntries.add(new PieEntry(vals[11], "Others"));

                PieDataSet barDataSet = new PieDataSet(barEntries, "");
                PieData theData = new PieData(barDataSet);

                chart.setData(theData);

                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                chart.animateXY(1000, 1000);
            } catch (NullPointerException nullPointerException){
                Toast.makeText(requireActivity().getApplicationContext(), "Try loading transactions", Toast.LENGTH_SHORT).show();
            }

        return view;
    }
}