package com.awisme.coronaway;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CasesFragment extends Fragment {
    private static final String DEFAULT_COUNTRY = "Canada";
    private ImageView flag;
    private TextView countryLabel;
    private TextView countryTotal, countryRecovered, countryDeath, newCountryTotal, newCountryRecovered, newCountryDeath;
    private TextView worldTotal, worldRecovered, worldDeath, newWorldTotal, newWorldRecovered, newWorldDeath;
    private JSONObject summary;
    private RequestQueue queue;
    private List<Country> countryList;

    public CasesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cases, container, false);

        flag = view.findViewById(R.id.iv_icon_flag);
        countryLabel = view.findViewById(R.id.tv_countryLabel);

        countryTotal = view.findViewById(R.id.tv_countryTotalCasesNum);
        countryRecovered = view.findViewById(R.id.tv_countryRecoveredNum);
        countryDeath = view.findViewById(R.id.tv_countryDeathsNum);

        newCountryTotal = view.findViewById(R.id.tv_countryTotalCasesNumNew);
        newCountryRecovered = view.findViewById(R.id.tv_countryRecoveredNumNew);
        newCountryDeath = view.findViewById(R.id.tv_countryDeathsNumNew);

        worldTotal = view.findViewById(R.id.tv_worldTotalCasesNum);
        worldRecovered = view.findViewById(R.id.tv_worldRecoveredNum);
        worldDeath = view.findViewById(R.id.tv_worldDeathsNum);

        newWorldTotal = view.findViewById(R.id.tv_worldTotalCasesNumNew);
        newWorldRecovered = view.findViewById(R.id.tv_worldRecoveredNumNew);
        newWorldDeath = view.findViewById(R.id.tv_worldDeathsNumNew);

        ImageButton selectCountry = view.findViewById(R.id.bt_selectCountry);
        selectCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectCountryDialog();
            }
        });

        countryList = new ArrayList<>();

        covidApiRequest();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment mapsFragment = new MapsFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.map_fragment_container, mapsFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (queue != null) {
            queue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
        }
    }

    private void setCasesByCountry(String countryName) {
        for (int i = 0; i < countryList.size(); i++) {
            Country country = countryList.get(i);
            if (country.getCountry().equals(countryName)) {
                String url = "https://www.countryflags.io/" + country.getCountryCode() + "/flat/32.png";

                Picasso.get().load(url).into(flag);

                countryLabel.setText(countryName);

                countryTotal.setText(formatCaseNum(country.getTotalConfirmed(), 1000, "K"));
                countryRecovered.setText(formatCaseNum(country.getTotalRecovered(), 1000, "K"));
                countryDeath.setText(formatCaseNum(country.getTotalDeaths(), 1000, "K"));

                newCountryTotal.setText(getString(R.string.new_case_text_view, String.valueOf(country.getNewConfirmed())));
                newCountryRecovered.setText(getString(R.string.new_case_text_view, String.valueOf(country.getNewRecovered())));
                newCountryDeath.setText(getString(R.string.new_case_text_view, String.valueOf(country.getNewDeaths())));
                break;
            }
        }
    }


    private void covidApiRequest() {
        queue = Volley.newRequestQueue(getContext());
        String url = "https://api.covid19api.com/summary";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    summary = response;
                    JSONObject global = response.getJSONObject("Global");
                    worldTotal.setText(formatCaseNum(global.getInt("TotalConfirmed"), 1000000, "M"));
                    worldRecovered.setText(formatCaseNum(global.getInt("TotalRecovered"), 1000000, "M"));
                    worldDeath.setText(formatCaseNum(global.getInt("TotalDeaths"), 1000000, "M"));

                    newWorldTotal.setText(getString(R.string.new_case_text_view, global.getString("NewConfirmed")));
                    newWorldRecovered.setText(getString(R.string.new_case_text_view, global.getString("NewRecovered")));
                    newWorldDeath.setText(getString(R.string.new_case_text_view, global.getString("NewDeaths")));


                    JSONArray allCountries = response.getJSONArray("Countries");
                    String s = allCountries.toString();

                    Type listType = new TypeToken<List<Country>>() {
                    }.getType();
                    countryList = new Gson().fromJson(s, listType);

                    setCasesByCountry(DEFAULT_COUNTRY);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);
    }

    private String formatCaseNum(int num, double unitValue, String unit) {
        double caseNum = num / unitValue;

        return String.format("%.2f", caseNum) + unit;
    }

    private void showSelectCountryDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.select_country_dialog, null);

        RecyclerView rvCountryList = dialogView.findViewById(R.id.rv_countryList);
        CountryListAdapter adapter = new CountryListAdapter(countryList);

        rvCountryList.setAdapter(adapter);
        rvCountryList.setLayoutManager(new LinearLayoutManager(getContext()));

        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Select a country");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

}



















