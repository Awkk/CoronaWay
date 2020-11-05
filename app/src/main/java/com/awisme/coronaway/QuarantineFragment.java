package com.awisme.coronaway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.os.Looper.getMainLooper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuarantineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuarantineFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuarantineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuarantineFragment.
     */
    // TODO: Rename and change types and number of parameters
    @SuppressLint("ResourceAsColor")
    public static QuarantineFragment newInstance(String param1, String param2) {
        QuarantineFragment fragment = new QuarantineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    Button btn;
    Button beginBtn;


    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;
    CheckBox checkBox8;
    CheckBox checkBox9;
    CheckBox checkBox10;
    CheckBox checkBox11;
    CheckBox checkBox12;
    CheckBox checkBox13;
    CheckBox checkBox14;

    //declare boolean
    boolean clicked=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_quarantine, container, false);

        btn = (Button) rootView.findViewById(R.id.symptoms_btn);
        btn.setOnClickListener(this);


        beginBtn = (Button) rootView.findViewById(R.id.lets_begin);
        beginBtn.setOnClickListener(startClick);

        final TextView date  = (TextView) rootView.findViewById(R.id.date_placeholder);


        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(1);

        // This schedule a task to run every 5 seconds for testing:
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (clicked) {
                    doTheActualJobWhenButtonClicked();
                }
            }
        }, 0, 10, TimeUnit.SECONDS);

        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                date.setText(new SimpleDateFormat("HH:mm", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        checkBox1 = (CheckBox) rootView.findViewById(R.id.checkBox1);
        checkBox2= (CheckBox) rootView.findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) rootView.findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) rootView.findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) rootView.findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox) rootView.findViewById(R.id.checkBox6);
        checkBox7 = (CheckBox) rootView.findViewById(R.id.checkBox7);
        checkBox8 = (CheckBox) rootView.findViewById(R.id.checkBox8);
        checkBox9 = (CheckBox) rootView.findViewById(R.id.checkBox9);
        checkBox10 = (CheckBox) rootView.findViewById(R.id.checkBox10);
        checkBox11 = (CheckBox) rootView.findViewById(R.id.checkBox11);
        checkBox12 = (CheckBox) rootView.findViewById(R.id.checkBox12);
        checkBox13 = (CheckBox) rootView.findViewById(R.id.checkBox13);
        checkBox14 = (CheckBox) rootView.findViewById(R.id.checkBox14);

        return rootView;

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(QuarantineFragment.this.getActivity(), QInfoActivity.class);
        startActivity(intent);


    }

    public View.OnClickListener startClick = new View.OnClickListener() {
        public void onClick(View v) {
            //change boolean value
            clicked=true;
            beginBtn.setVisibility(View.INVISIBLE);
            doTheActualJobWhenButtonClicked();

        }

    };

    private void doTheActualJobWhenButtonClicked() {
        if(!checkBox1.isChecked()) {
            checkBox1.setChecked(true);
        }
        else if(!checkBox2.isChecked() && checkBox1.isChecked()) {
            checkBox2.setChecked(true);
        }
        else if(!checkBox3.isChecked() && checkBox2.isChecked()) {
            checkBox3.setChecked(true);
        }
        else if(!checkBox4.isChecked() && checkBox3.isChecked()) {
            checkBox4.setChecked(true);
        }
        else if(!checkBox5.isChecked() && checkBox4.isChecked()) {
            checkBox5.setChecked(true);
        }
        else if(!checkBox6.isChecked() && checkBox5.isChecked()) {
            checkBox6.setChecked(true);
        }
        else if(!checkBox7.isChecked() && checkBox6.isChecked()) {
            checkBox7.setChecked(true);
        }
        else if(!checkBox8.isChecked() && checkBox7.isChecked()) {
            checkBox8.setChecked(true);
        }
        else if(!checkBox9.isChecked() && checkBox8.isChecked()) {
            checkBox9.setChecked(true);
        }
        else if(!checkBox10.isChecked() && checkBox9.isChecked()) {
            checkBox10.setChecked(true);
        }
        else if(!checkBox11.isChecked() && checkBox10.isChecked()) {
            checkBox11.setChecked(true);
        }
        else if(!checkBox12.isChecked() && checkBox11.isChecked()) {
            checkBox12.setChecked(true);
        }
        else if(!checkBox13.isChecked() && checkBox12.isChecked()) {
            checkBox13.setChecked(true);

        }else if(!checkBox14.isChecked() && checkBox13.isChecked()) {
            checkBox14.setChecked(true);
        }
    }
}
