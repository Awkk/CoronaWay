package com.awisme.coronaway;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CountryListAdapter extends ArrayAdapter<Country> {
    private Context context;
    private List<Country> countryList;

    public CountryListAdapter(Context context, List<Country> countryList) {
        super(context, R.layout.country_list_layout, countryList);
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Country country = countryList.get(position);
        View listViewItem = View.inflate(context,R.layout.country_list_layout,null);

        ImageView flag = listViewItem.findViewById(R.id.iv_icon_flag);
        String url = "https://www.countryflags.io/" + country.getCountryCode() + "/flat/32.png";
        Picasso.get().load(url).into(flag);

        TextView tvCountryName = listViewItem.findViewById(R.id.tv_countryName);
        tvCountryName.setText(country.getCountry());

        return listViewItem;
    }
}
