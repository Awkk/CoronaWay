package com.awisme.coronaway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.awisme.coronaway.MainActivity;
import com.awisme.coronaway.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.os.Looper.getMainLooper;

public class QInfoActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    final TextView date = (TextView)findViewById(R.id.date_placeholder1);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qinfo);

        // Current date settext counter
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                date.setText(new SimpleDateFormat("MMM-dd HH:mm:ss", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);
    }

}