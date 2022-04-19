package com.myapps.bottomnavigationbarfragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HistoryFragment extends Fragment {

    ListView lv_Transactions;
    ArrayAdapter transactionsArrayAdapter;
    DataBaseHelper dataBaseHelper;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);


        lv_Transactions = view.findViewById(R.id.lvTransactions);
        dataBaseHelper = new DataBaseHelper(getContext());
        ShowPaymentsOnListView(dataBaseHelper);
        return view;
    }

    private void ShowPaymentsOnListView(DataBaseHelper dataBaseHelper2) {
        transactionsArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataBaseHelper2.getAll());
        lv_Transactions.setAdapter(transactionsArrayAdapter);
    }


}