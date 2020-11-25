package com.awisme.coronaway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
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
    /**
     * Called when the activity is first created.
     */
    private TextView date;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qinfo);

        date = (TextView) findViewById(R.id.date_placeholder1);

        // Current date settext counter
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                date.setText(new SimpleDateFormat("MMM-dd HH:mm:ss", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        TextView t1 = (TextView)findViewById((R.id.tv_Contact));
        t1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t2 = (TextView)findViewById((R.id.tv_selfAssess));
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t3 = (TextView)findViewById((R.id.tv_prevent));
        t3.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t4 = (TextView)findViewById((R.id.tv_treat));
        t4.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t5 = (TextView)findViewById((R.id.tv_Common));
        t5.setMovementMethod(LinkMovementMethod.getInstance());
    }

}