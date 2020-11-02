package ca.bcit.testing3;

import android.content.Context;
import android.content.Intent;
//import android.support.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.ArrayList;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    ArrayList<DataSetList> arrayList;
    Context context;
    Button button;


    public YoutubeAdapter(ArrayList<DataSetList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public YoutubeViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.video_per_row,viewGroup,false);
        return new YoutubeViewHolder(view);



    }

    @Override
    public void onBindViewHolder( YoutubeViewHolder youtubeViewHolder, int i) {

        final DataSetList current = arrayList.get(i);

        youtubeViewHolder.webView.loadUrl(current.getLink());
        youtubeViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,VideoFullScreen.class);
                i.putExtra("link",current.getLink());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}


