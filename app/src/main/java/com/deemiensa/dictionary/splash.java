package com.deemiensa.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


public class splash extends AppCompatActivity {
    private Handler mHandler = new Handler();
    dictionarySQLiteDatabase dbBackend ;
    private RelativeLayout animateLogo;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        animateLogo =(RelativeLayout) findViewById(R.id.animateLogo);

      dbBackend = new dictionarySQLiteDatabase(this);

      show_animation();

      mHandler.postDelayed(new Runnable() {
            public void run() {
                doStuff();
            }
      }, 5000);
    }

    private void doStuff() {
       /* Intent intent = new Intent(this, main.class);
        startActivity(intent);
        finish();*/

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            // show start activity

            startActivity(new Intent(splash.this, onBoarding.class));
            this.finish();
            // Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG)
            //     .show();
        } else {

            startActivity(new Intent(splash.this, main.class));
            this.finish();
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

    }

    public void show_animation()
    {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);

        animateLogo.startAnimation(show);

        show.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                animateLogo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animateLogo.clearAnimation();

            }

        });

    }
}
