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

public class RelaxVideoActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<DataSetList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<DataSetList>();

        DataSetList dataSetList = new DataSetList("https://www.youtube.com/embed/R5w5moM9kDM");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/MUKOEbleojc");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/E8Y4OZUIHys");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/LYyiyzluy1w");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/FIaptRWQjH0");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/09LzK15vFXg");
        arrayList.add(dataSetList);


        YoutubeAdapter youtubeAdapter = new YoutubeAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(youtubeAdapter);

    }
}

//public class RelaxVideoActivity extends YouTubeBaseActivity {
//    YouTubePlayerView mYoutubePlayerView;
//    Button btn;
//    YouTubePlayer.OnInitializedListener mOnInit;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
//        btn = (Button) findViewById(R.id.button);
//        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);
//        mOnInit = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//
//                youTubePlayer.loadPlaylist("PLCsWMr_w3doQ3XsevGeR-jAKsV6PeBZDm");
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