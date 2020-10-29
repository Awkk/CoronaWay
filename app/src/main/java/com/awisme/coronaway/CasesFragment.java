package com.awisme.coronaway;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CasesFragment extends Fragment {
    private static final String DEFAULT_COUNTRY = "Canada";
    private ImageView flag;
    private TextView countryLabel;
    private TextView countryTotal, countryRecovered, countryDeath, newCountryTotal, newCountryRecovered, newCountryDeath;
    private TextView worldTotal, worldRecovered, worldDeath, newWorldTotal, newWorldRecovered, newWorldDeath;
    private JSONObject summary;
    private RequestQueue queue;

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
        try {
            JSONArray allCountries = summary.getJSONArray("Countries");
            for (int i = 0; i < allCountries.length(); i++) {
                JSONObject country = allCountries.getJSONObject(i);
                if (country.getString("Country").equals(countryName)) {
                    String url = "https://www.countryflags.io/" + country.getString("CountryCode") + "/flat/32.png";

                    Picasso.get().load(url).into(flag);

                    countryLabel.setText(countryName);

                    countryTotal.setText(formatCaseNum(country.getInt("TotalConfirmed"), 1000, "K"));
                    countryRecovered.setText(formatCaseNum(country.getInt("TotalRecovered"), 1000, "K"));
                    countryDeath.setText(formatCaseNum(country.getInt("TotalDeaths"), 1000, "K"));

                    newCountryTotal.setText(getString(R.string.new_case_text_view, country.getString("NewConfirmed")));
                    newCountryRecovered.setText(getString(R.string.new_case_text_view, country.getString("NewRecovered")));
                    newCountryDeath.setText(getString(R.string.new_case_text_view, country.getString("NewDeaths")));
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
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

}



















