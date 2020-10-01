package com.awisme.coronaway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashSet;
import java.util.Set;

public class RelaxActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_relax);
        setupViews();
        findViewById(R.id.link1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                click_btn("https://www.youtube.com/watch?v=1aPyoklnNCY&t=28s");
            }
        });

        findViewById(R.id.link2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                click_btn("https://www.youtube.com/watch?v=q76bMs-NwRk&t=174s");
            }
        });

        findViewById(R.id.link3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                click_btn("https://www.youtube.com/watch?v=O-6f5wQXSu8");
            }
        });

    }

    public void click_btn(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void setupViews() {
        // Bottom navigation setup
        bottomNavView = findViewById(R.id.bottomNavView);
        Fragment fragNavHost = getSupportFragmentManager().findFragmentById(R.id.fragNavHost);
        navController = NavHostFragment.findNavController(fragNavHost);
        NavigationUI.setupWithNavController(bottomNavView, navController);

        // AppBar setup
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.casesFragment);
        topLevelDestinations.add(R.id.relaxFragment);
        topLevelDestinations.add(R.id.quarantineFragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }


}