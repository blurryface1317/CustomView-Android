package com.sew.customviews.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sew.customviews.activities.views.CustomView;
import com.sew.customviews.R;

public class MainActivity extends AppCompatActivity {

    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = findViewById(R.id.custom);
//
//        customView.setOnValueChangeListener(new CustomView.OnValuesClickListener() {
//            @Override
//            public void OnValueChange(int oldValue, int newValue) {
//                Toast.makeText(MainActivity.this, "old Value: " + customView.getInitialValue() + "New " + customView.getCurrentValue(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}

