package com.awisme.coronaway;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends Activity {     /**
         * Called when the activity is first created.
         */

        EditText mFullName, mEmail, mPassword;
        ImageButton mRegisterBtn;
        TextView mLoginBtn;;
        FirebaseAuth fAuth;
        ProgressBar progressBar;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            mFullName = findViewById(R.id.signup_fullname);
            mEmail = findViewById(R.id.signup_email);
            mPassword = findViewById(R.id.signup_password);
            mRegisterBtn = findViewById(R.id.signup_btn_go);
            mLoginBtn = findViewById(R.id.have_account_login);

            fAuth = FirebaseAuth.getInstance();
            progressBar = findViewById(R.id.progress_bar);

            if(fAuth.getCurrentUser() != null) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            mRegisterBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String email = mEmail.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();

                    if(TextUtils.isEmpty(email)) {
                        mEmail.setError(("Email is Required"));
                        return;
                    }

                    if(TextUtils.isEmpty(password)){
                        mPassword.setError(("Password is Required"));
                        return;
                    }

                    if(password.length() < 6) {
                        mPassword.setError("Password must be >= 6 characters");
                        return;
                    }


                    progressBar.setVisibility(View.VISIBLE);

                    //register the user in firebase
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();


                            }
                        }
                    });

                }

            });
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

