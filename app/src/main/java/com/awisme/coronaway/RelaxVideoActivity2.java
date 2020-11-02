package com.awisme.coronaway;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RelaxVideoActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<DataSetList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube2);
        recyclerView = findViewById(R.id.recyclerview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<DataSetList>();

        DataSetList dataSetList = new DataSetList("https://www.youtube.com/embed/SSctqjzFjJ8");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/IT0dmLL0PE8");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/d2CWGfusNnU");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/xnWEM-XivGs");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/-PnEDnCusYA");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/xOrQ2xqsUIM");
        arrayList.add(dataSetList);


        YoutubeAdapter youtubeAdapter = new YoutubeAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(youtubeAdapter);

    }
}
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.google.android.youtube.player.YouTubeBaseActivity;
//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerView;
//
//public class RelaxVideoActivity2 extends YouTubeBaseActivity {
//    YouTubePlayerView mYoutubePlayerView;
//    Button btn;
//    YouTubePlayer.OnInitializedListener mOnInit;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube2);
//        btn = (Button) findViewById(R.id.button);
//        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay2);
//        mOnInit = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//
//                youTubePlayer.loadPlaylist("PLYF1hruGwol5YczgQxDNZig4avyWMUaWv");
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        };
//
//        btn.setOnClickListener(new View.OnClickListener(){
//
//            public void onClick(View view){
//                mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(),mOnInit);
//            }
//        });
//
//    }
//}