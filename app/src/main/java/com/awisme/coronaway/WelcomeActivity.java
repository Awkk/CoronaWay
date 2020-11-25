package com.awisme.coronaway;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
//    TextView welcometext1 = findViewById(R.id.welcometext_1);
//    TextView welcometext2 = findViewById(R.id.welcometext_2);
//    TextView welcometext3 = findViewById(R.id.welcometext_3);
//    TextView welcometext4 = findViewById(R.id.welcometext_4);
//    TextView welcometext5 = findViewById(R.id.welcometext_5);
//    TextView welcometext6 = findViewById(R.id.welcometext_6);
//    ImageButton welcomeBtn1 = findViewById(R.id.welcomeBtn_Next);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
//        welcometext2.startAnimation(aniFade);
//        welcometext3.startAnimation(aniFade);
//        welcometext4.startAnimation(aniFade);
//        welcometext5.startAnimation(aniFade);
//        welcometext6.startAnimation(aniFade);
//        welcomeBtn1.startAnimation(aniFade);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
/*
animation.setAnimationListener(new AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            wave.startAnimation(animation1);

        }
    });
 */

}