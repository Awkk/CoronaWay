package com.awisme.coronaway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.awisme.coronaway.ui.login.LoginActivity;

public class SignUpActivity extends Activity {     /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);
        }

    public void sign_in_redirect(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void sign_in_btn(View view) {
        Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}

