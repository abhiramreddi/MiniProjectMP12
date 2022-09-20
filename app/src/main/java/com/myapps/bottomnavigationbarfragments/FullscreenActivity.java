package com.myapps.bottomnavigationbarfragments;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.myapps.bottomnavigationbarfragments.LedgerDOA.MainActivity;

public class FullscreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        new Handler().postDelayed(() -> startActivity(new Intent(FullscreenActivity.this,MainActivity.class)), 3000);
    }
}