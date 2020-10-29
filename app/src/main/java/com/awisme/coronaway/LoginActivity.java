package com.awisme.coronaway;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    ImageButton mLoginBtn;
    TextView mCreateBtn;
    ProgressBar loginProgressBar;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail= findViewById(R.id.login_email);
        mPassword= findViewById(R.id.login_password);
        progressBar = findViewById(R.id.progress_bar);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.login_btn);
        mCreateBtn = findViewById(R.id.no_account_signup);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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




                //register the user in firebase
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            }

        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
    }
}