package com.awisme.coronaway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
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