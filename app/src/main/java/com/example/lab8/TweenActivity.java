package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class TweenActivity extends AppCompatActivity {

    ImageView img;
    Button btnDissapeared;
    private Timer imgFallImg;
    int ticks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        btnDissapeared = findViewById(R.id.btn_Dissapeared);
        imgFallImg = new Timer();
        imgFallImg.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (ticks < 5500)
                    ticks++;
                else
                    ticks = 0;
            }
        }, 0, 1);

        img = findViewById(R.id.animationView);
        img.setImageResource(R.drawable.klee);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fall_animation);
        img.startAnimation(anim);
    }

    public void onClickTurn(View view) {
        //просчитывается текущее положение картинки по Y
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float height = size.y;
        float currentYPosition = height*ticks/5500;
        img.setY(currentYPosition-128);
        img.clearAnimation();
        /*
        * Производится анимация поворота в движении
        * Требую дополнительную 5 за то,
        * что движение начинается (почти) с того
        * места, где в тот момент была картинка
        * во время нажатия кнопки
        * */
        ViewPropertyAnimatorListener animatorListener = new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {}

            @Override
            public void onAnimationEnd(View view) {
                //после первого завершения анимация отменяется
                //и проигрывается стандартная анимация падения
                view.setRotation(0);
                view.animate().cancel();
                view.clearAnimation();
                img.setY(0); ticks = 0;
                Animation anim = AnimationUtils.loadAnimation(view.getContext(), R.anim.fall_animation);
                img.startAnimation(anim);
            }

            @Override
            public void onAnimationCancel(View view) {}
        };
        ViewCompat.animate(img).setListener(animatorListener).rotation(270).setDuration(1300)
                .translationY(height).setDuration(2500).start();
    }

    public void onClickDisappear(View view) {
        btnDissapeared.animate().cancel();
        btnDissapeared.clearAnimation();
        if (btnDissapeared.getVisibility() == View.VISIBLE) {
            ViewPropertyAnimatorListener animatorListener = new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {}
                @Override
                public void onAnimationEnd(View view) {
                    btnDissapeared.setVisibility(View.INVISIBLE);
                    btnDissapeared.animate().cancel();
                    btnDissapeared.clearAnimation();
                }
                @Override
                public void onAnimationCancel(View view) {}
            };
            ViewCompat.animate(btnDissapeared).setListener(animatorListener).alpha(0).setDuration(1200).start();
        }
        else {
            ViewPropertyAnimatorListener animatorListener = new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {
                    btnDissapeared.setVisibility(View.VISIBLE);
                    btnDissapeared.setAlpha(0);
                }
                @Override
                public void onAnimationEnd(View view) {
                    btnDissapeared.animate().cancel();
                    btnDissapeared.clearAnimation();
                }
                @Override
                public void onAnimationCancel(View view) {}
            };
            ViewCompat.animate(btnDissapeared).setListener(animatorListener).alpha(1).setDuration(1200).start();
        }
    }

    public void onClickAllAnimation(View view) {
        onClickDisappear(view);
        onClickTurn(view);
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}