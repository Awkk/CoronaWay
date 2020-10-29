package com.awisme.coronaway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {
    private List<Country> countryList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    public CountryListAdapter(List<Country> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countryList.get(position);

        View view = holder.view;
        ImageView flag = view.findViewById(R.id.iv_icon_flag);
        TextView tvCountryName = view.findViewById(R.id.tv_countryName);
        tvCountryName.setText(country.getCountry());

        String url = "https://www.countryflags.io/" + country.getCountryCode() + "/flat/32.png";
        Picasso.get().load(url).into(flag);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }


}
