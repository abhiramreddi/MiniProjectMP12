package com.myapps.bottomnavigationbarfragments.Fragments;

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
import com.myapps.bottomnavigationbarfragments.LedgerDOA.DataBaseHelper;
import com.myapps.bottomnavigationbarfragments.R;

import java.util.ArrayList;

public class VisualizeFragment extends Fragment {

    public int[] vals;

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
            DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
            dataBaseHelper.getAll();
            vals = DataBaseHelper.values;


            PieChart chart = view.findViewById(R.id.piechart);
            String[] categs = {"Food", "Shopping", "Phone", "Health", "Groceries", "Travel", "Fuel", "Education", "Electricity", "Bills", "Housing"};

            ArrayList<PieEntry> barEntries = new ArrayList<>();
            for (int i = 0; i < 11; i++) {
                if (vals[i] != 0) {
                    barEntries.add(new PieEntry(vals[i], categs[i]));
                }
            }

            PieDataSet barDataSet = new PieDataSet(barEntries, "");
            PieData theData = new PieData(barDataSet);
            chart.setData(theData);

            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            chart.animateXY(1000, 1000);
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(requireActivity().getApplicationContext(), "Try loading transactions", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

}