package com.awisme.coronaway;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class RelaxVideoActivity2 extends YouTubeBaseActivity {
    YouTubePlayerView mYoutubePlayerView;
    Button btn;
    YouTubePlayer.OnInitializedListener mOnInit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube2);
        btn = (Button) findViewById(R.id.button);
        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay2);
        mOnInit = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadPlaylist("PLYF1hruGwol5YczgQxDNZig4avyWMUaWv");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(),mOnInit);
            }
        });

    }
}