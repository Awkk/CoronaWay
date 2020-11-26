package com.awisme.coronaway;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.os.Looper.getMainLooper;
import static androidx.core.content.ContextCompat.getSystemService;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class QuarantineFragment extends Fragment implements View.OnClickListener {

    //myRef firebase
    DatabaseReference databaseUsers;
    FirebaseAuth fAuth;

    public QuarantineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseUsers = FirebaseDatabase.getInstance().getReference();
    }

    TextView tvStatus;
    TextView tvDayStart;
    Button btn;
    Button beginBtn;
    Button popBtn;
    Button btnRestart;
    List<CheckBox> checkboxes;
    String userid;

    //declare boolean
    boolean clicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_quarantine, container, false);

        checkboxes = new ArrayList<>();

        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox1));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox2));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox3));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox4));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox5));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox6));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox7));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox8));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox9));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox10));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox11));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox12));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox13));
        checkboxes.add((CheckBox) rootView.findViewById(R.id.checkBox14));

        tvStatus = rootView.findViewById(R.id.tv_status);
        tvDayStart = rootView.findViewById(R.id.tv_dayStart);

        btn = (Button) rootView.findViewById(R.id.symptoms_btn);
        btn.setOnClickListener(this);

        beginBtn = (Button) rootView.findViewById(R.id.lets_begin);
        beginBtn.setOnClickListener(startClick);

        popBtn = (Button) rootView.findViewById(R.id.message_of_day_btn);
        popBtn.setOnClickListener(popClick);

        rootView.findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        final TextView date = (TextView) rootView.findViewById(R.id.date_placeholder);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userid = user.getUid();


        btnRestart = (Button) rootView.findViewById(R.id.btn_restart);
        btnRestart.setOnClickListener(restartClick);


        databaseUsers.child("users").child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    beginBtn.setVisibility(View.GONE);
                    Posting posting = dataSnapshot.getValue(Posting.class);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
                    Date startDate;
                    try {
                        startDate = formatter.parse(posting.getDate());
                        long diffInMillis = Math.abs(Calendar.getInstance().getTimeInMillis() - startDate.getTime());
                        int diffInDays = (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

                        tvDayStart.setText("Started on " + formatter.format(startDate));

                        if (diffInDays >= 14) {
                            diffInDays = 14;
                            tvStatus.setText("Congratulations! Your Quarantine is Over!");
                            btnRestart.setVisibility(View.VISIBLE);
                        } else {
                            tvStatus.setText("You're on Day: " + (diffInDays + 1));
                            btnRestart.setVisibility(View.GONE);
                        }

                        for (int i = 0; i < diffInDays; i++) {
                            checkboxes.get(i).setChecked(true);
                        }
                        for (int i = 13; i >= diffInDays; i--) {
                            checkboxes.get(i).setChecked(false);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                } else {
                    beginBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Current date settext counter
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                date.setText(new SimpleDateFormat("MMM-dd HH:mm:ss", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        return rootView;
    }

    //restart quarantine cycle click
    public View.OnClickListener restartClick = new View.OnClickListener() {
        public void onClick(View v) {
            saveSessionToFirebase();
            btnRestart.setVisibility(View.GONE);
        }
    };

    //popup window click
    public View.OnClickListener popClick = new View.OnClickListener() {
        public void onClick(View v) {
            onButtonShowPopupWindowClick(v);
        }

    };


    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken


        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(50);
        }
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }


    //Clicking on quarantine info fragment
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(QuarantineFragment.this.getActivity(), QInfoActivity.class);
        startActivity(intent);


    }

    //clicking on begin quarantine button
    public View.OnClickListener startClick = new View.OnClickListener() {
        public void onClick(View v) {
            saveSessionToFirebase();

        }
    };

    private void saveSessionToFirebase() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
        Date date = new Date();
        String strDate = dateFormat.format(date).toString();

        String id = databaseUsers.push().getKey();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = user.getUid();

        String email = user.getEmail();

        Boolean pressed = true;

        Posting post = new Posting();

        post.setDate(strDate);
        post.setName(userid);
        post.setEmail(email);
        post.setPressed(pressed);

        try {
            databaseUsers.child("users").child(userid).setValue(post);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "error while inserting", Toast.LENGTH_LONG).show();
        }
        beginBtn.setVisibility(View.INVISIBLE);

    }

    public void startAlert() {
//        final Button button = buttons[2]; // replace with a button from your own UI
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent i) {

                context.unregisterReceiver(this); // this == BroadcastReceiver, not Activity
            }
        };

        getActivity().registerReceiver(receiver, new IntentFilter("Day passed!"));

        PendingIntent pintent = PendingIntent.getBroadcast(getActivity(), 0, new Intent("Day passed!"), 0);
        AlarmManager manager = (AlarmManager) (getActivity().getSystemService(Context.ALARM_SERVICE));

        // set alarm to fire 5 sec (1000*5) from now (SystemClock.elapsedRealtime())
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 100 * 5, pintent);
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }

}
