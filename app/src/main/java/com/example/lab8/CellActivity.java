package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CellActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell);
        img = findViewById(R.id.animationView);
    }

    public void onClickStart(View view) {
        img.setBackgroundResource(R.drawable.sitting_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.start();
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}