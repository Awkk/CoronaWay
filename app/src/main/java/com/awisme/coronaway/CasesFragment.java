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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
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
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CasesFragment extends Fragment {
    private static final String DEFAULT_COUNTRY = "Canada";
    private ImageView flag;
    private TextView countryLabel;
    private TextView countryTotal, countryRecovered, countryDeath, newCountryTotal, newCountryRecovered, newCountryDeath;
    private TextView worldTotal, worldRecovered, worldDeath, newWorldTotal, newWorldRecovered, newWorldDeath;
    private RequestQueue queue;
    private List<Country> countryList;
    private MapsFragment mapsFragment;

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
        countryLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectCountryDialog();
            }
        });

        countryList = new ArrayList<>();

        covidApiSummaryRequest();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapsFragment = new MapsFragment();
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
                mapsFragment.changeLocation(country);

                String url = "https://www.countryflags.io/" + country.getCountryCode() + "/flat/32.png";

                Picasso.get().load(url).into(flag);

                countryLabel.setText(countryName);

                countryTotal.setText(formatCaseNum(country.getTotalConfirmed()));
                countryRecovered.setText(formatCaseNum(country.getTotalRecovered()));
                countryDeath.setText(formatCaseNum(country.getTotalDeaths()));

                newCountryTotal.setText(getString(R.string.new_case_text_view, String.valueOf(country.getNewConfirmed())));
                newCountryRecovered.setText(getString(R.string.new_case_text_view, String.valueOf(country.getNewRecovered())));
                newCountryDeath.setText(getString(R.string.new_case_text_view, String.valueOf(country.getNewDeaths())));
                break;
            }
        }
    }


    private void covidApiSummaryRequest() {
        queue = Volley.newRequestQueue(getContext());
        String url = "https://api.covid19api.com/summary";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject global = response.getJSONObject("Global");
                    worldTotal.setText(formatCaseNum(global.getInt("TotalConfirmed")));
                    worldRecovered.setText(formatCaseNum(global.getInt("TotalRecovered")));
                    worldDeath.setText(formatCaseNum(global.getInt("TotalDeaths")));

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

    private String formatCaseNum(int num) {
        double caseNum = num;
        String unit = null;
        if (num / 1000.0 > 999) {
            caseNum = num / 1000000.0;
            unit = "M";
        } else if (num > 999) {
            caseNum = num / 1000.0;
            unit = "K";
        }

        return unit == null ? String.valueOf((int) caseNum) : String.format(Locale.getDefault(), "%.2f", caseNum) + unit;
    }

    private void showSelectCountryDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.select_country_dialog, null);

        SearchView svCountry = dialogView.findViewById(R.id.sv_countryList);
        final TextView tvNoResult = dialogView.findViewById(R.id.tv_no_result);
        RecyclerView rvCountryList = dialogView.findViewById(R.id.rv_countryList);
        final CountryListAdapter adapter = new CountryListAdapter(countryList);

        rvCountryList.setAdapter(adapter);
        rvCountryList.setLayoutManager(new LinearLayoutManager(getContext()));

        dialogBuilder.setView(dialogView);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        adapter.setListener(new CountryListAdapter.Listener() {
            @Override
            public void onClick(String countryName) {
                setCasesByCountry(countryName);
                alertDialog.dismiss();
            }
        });

        svCountry.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                filterList(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }

            private void filterList(String country) {
                List<Country> filterList = new ArrayList<>();
                for (Country c : countryList) {
                    if (c.getCountry().toLowerCase().contains(country.toLowerCase().trim())) {
                        filterList.add(c);
                    }
                }
                adapter.setCountryList(filterList);
                adapter.notifyDataSetChanged();

                if(filterList.size()==0){
                    tvNoResult.setText(getResources().getString(R.string.no_result_found));
                }else{
                    tvNoResult.setText("");
                }
            }
        });
    }


}



















