package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClickCellAnimationActivity(View view) {
        Intent intent = new Intent(this, CellActivity.class);
        startActivity(intent);
    }

    public void onClickTweenAnimationActivity(View view) {
        Intent intent = new Intent(this, TweenActivity.class);
        startActivity(intent);
    }
}