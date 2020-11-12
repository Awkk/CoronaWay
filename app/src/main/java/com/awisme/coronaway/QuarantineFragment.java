package com.awisme.coronaway;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.os.Looper.getMainLooper;

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

    //myRef firebase
    DatabaseReference databaseUsers;
    FirebaseAuth fAuth;

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

        databaseUsers = FirebaseDatabase.getInstance().getReference();


    }


    Button btn;
    Button beginBtn;
    Button popBtn;


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

        popBtn = (Button) rootView.findViewById(R.id.message_of_day_btn);
        popBtn.setOnClickListener(popClick);

        final TextView date  = (TextView) rootView.findViewById(R.id.date_placeholder);

       //**********************
        //Does not work at the moment, not sure how to make button go invisible by checking if user exists(and user would exist after pressing the button once)
        databaseUsers.child("users").child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    beginBtn.setVisibility(View.INVISIBLE);
                } else {
                    beginBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
        Saving data to firebase as TIMESTAMP

        public class YourModelClass {
        //private fields
        private Map<String, String> timestamp;

        public YourModelClass() {}

        //public setters and getters for the fields

        public void setTimestamp(Map<String, String> timeStamp) {this.timestamp= timestamp;}
        public Map<String, String> getTimestamp() {return timestamp;}
        }

        Map map = new HashMap();
        map.put("timestamp", ServerValue.TIMESTAMP);
        ref.child("yourNode").updateChildren(map);


        To retrieve data, as type long

        public static String getTimeDate(long timestamp){
            try{
                DateFormat dateFormat = getDateTimeInstance();
                Date netDate = (new Date(timestamp));
                return dateFormat.format(netDate);
            } catch(Exception e) {
                return "date";
            }

       Alarm Manager
https://developer.android.com/training/scheduling/alarms
// Hopefully your alarm will have a lower frequency than this!

        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
        SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
        AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);

    }
         */
//
//        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(1);
//
//        // This schedule a task to run every 5 seconds for testing:
//        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                if (clicked) {
//                    doTheActualJobWhenButtonClicked();
//                }
//            }
//        }, 0, 10, TimeUnit.SECONDS);

        /*
        getContinueExistingPeriodicTasksAfterShutdownPolicy()
        Gets the policy on whether to continue executing existing periodic tasks even when this executor has been shutdown.

         */

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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

        try{
            databaseUsers.child("users").child(userid).setValue(post);
        }
        catch(Exception e){
            Toast.makeText(getActivity(),"error while inserting",Toast.LENGTH_LONG).show();
        }
        beginBtn.setVisibility(View.INVISIBLE);
    }
}
