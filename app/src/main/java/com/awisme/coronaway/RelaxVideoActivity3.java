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

public class RelaxVideoActivity3 extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<DataSetList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube3);
        recyclerView = findViewById(R.id.recyclerview3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<DataSetList>();

        DataSetList dataSetList = new DataSetList("https://www.youtube.com/embed/inpok4MKVLM");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/U9YKY7fdwyg");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/aEqlQvczMJQ");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/69Dr4omavCk");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/z6X5oEIg6Ak");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/z6X5oEIg6Ak");
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
//public class RelaxVideoActivity3 extends YouTubeBaseActivity {
//    YouTubePlayerView mYoutubePlayerView;
//    Button btn;
//    YouTubePlayer.OnInitializedListener mOnInit;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube3);
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