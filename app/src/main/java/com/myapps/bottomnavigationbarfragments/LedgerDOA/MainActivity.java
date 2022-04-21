package com.myapps.bottomnavigationbarfragments.LedgerDOA;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myapps.bottomnavigationbarfragments.Fragments.HistoryFragment;
import com.myapps.bottomnavigationbarfragments.Fragments.HomeFragment;
import com.myapps.bottomnavigationbarfragments.Fragments.LedgerFragment;
import com.myapps.bottomnavigationbarfragments.Fragments.ProfileFragment;
import com.myapps.bottomnavigationbarfragments.Fragments.VisualizeFragment;
import com.myapps.bottomnavigationbarfragments.R;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, new HomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item ->

        {

            switch (item.getItemId()) {
                case R.id.home:
                    Fragment fragment1;
                    fragment1 = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, fragment1).commit();

                    break;
                case R.id.visualize:
                    Fragment fragment2;
                    fragment2 = new VisualizeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, fragment2).commit();

                    break;
                case R.id.ledger:
                    Fragment fragment3;
                    fragment3 = new LedgerFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, fragment3).commit();

                    break;
                case R.id.history:
                    Fragment fragment4;
                    fragment4 = new HistoryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, fragment4).commit();
                    break;
                case R.id.profile:
                    Fragment fragment5;
                    fragment5 = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer, fragment5).commit();
                    break;
            }


            return true;
        });
    }


}