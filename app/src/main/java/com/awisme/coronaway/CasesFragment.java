package com.awisme.coronaway;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CasesFragment extends Fragment {
    private static final String DEFAULT_COUNTRY = "Canada";
    private TextView provinceTotal, provinceRecovered, provinceDeath;
    private TextView countryTotal, countryRecovered, countryDeath;
    private TextView worldTotal, worldRecovered, worldDeath;
    private JSONObject summary;

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
//        provinceTotal = view.findViewById(R.id.tv_provinceTotalCasesNum);
//        provinceRecovered = view.findViewById(R.id.tv_provinceRecoveredNum);
//        provinceDeath = view.findViewById(R.id.tv_provinceDeathsNum);

        countryTotal = view.findViewById(R.id.tv_countryTotalCasesNum);
        countryRecovered = view.findViewById(R.id.tv_countryRecoveredNum);
        countryDeath = view.findViewById(R.id.tv_countryDeathsNum);

        worldTotal = view.findViewById(R.id.tv_worldTotalCasesNum);
        worldRecovered = view.findViewById(R.id.tv_worldRecoveredNum);
        worldDeath = view.findViewById(R.id.tv_worldDeathsNum);

        covidApiRequest();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        Fragment mapsFragment = new MapsFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.map_fragment_container, mapsFragment).commit();
    }

    private void setCountry(String countryName) {
        try {
            JSONArray allCountries = summary.getJSONArray("Countries");
            for (int i = 0; i < allCountries.length(); i++) {
                JSONObject country = allCountries.getJSONObject(i);
                if (country.getString("Country").equals(countryName)) {
                    countryTotal.setText(country.getString("TotalConfirmed"));
                    countryRecovered.setText(country.getString("TotalRecovered"));
                    countryDeath.setText(country.getString("TotalDeaths"));
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void covidApiRequest() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://api.covid19api.com/summary";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    summary = response;
                    worldTotal.setText(response.getJSONObject("Global").getString("TotalConfirmed"));
                    worldRecovered.setText(response.getJSONObject("Global").getString("TotalRecovered"));
                    worldDeath.setText(response.getJSONObject("Global").getString("TotalDeaths"));

                    setCountry(DEFAULT_COUNTRY);
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

}



















