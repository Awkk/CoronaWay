package com.awisme.coronaway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RelaxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelaxFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton link1;
    private ImageButton link2;
    private ImageButton link3;

    public RelaxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RelaxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RelaxFragment newInstance(String param1, String param2) {
        RelaxFragment fragment = new RelaxFragment();
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



    public void click_btn(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_relax, container, false);



}
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        link1 = (ImageButton) getView().findViewById(R.id.link1);
        link2 = (ImageButton) getView().findViewById(R.id.link2);
        link3 = (ImageButton) getView().findViewById(R.id.link3);


        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click_btn("https://www.youtube.com/watch?v=1aPyoklnNCY&t=28s");
            }
        });

        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_btn("https://www.youtube.com/watch?v=q76bMs-NwRk&t=174s");
            }
        });

        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_btn("https://www.youtube.com/watch?v=O-6f5wQXSu8");
            }
        });
    }




}