package com.bit.countries;

import android.app.Activity;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;

import java.io.InputStream;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<country> countryList;
    private Activity context;
    private  CountryDatabase database;
    public DataAdapter(List<country> data, MainActivity mainActivity) {
        this.context = mainActivity;
        this.countryList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        country data = countryList.get(position);
        database = CountryDatabase.getInstance(context);
        holder.name.setText("Name : " + data.getName());
        holder.capital.setText("Capital : " + data.getCapital());
        holder.region.setText("Region : " + data.getRegion());
        holder.subregion.setText("Subregion : " + data.getSubregion());
        holder.population.setText("Population : " + data.getPopulation());
        holder.borders.setText("Borders : " + data.getBorders());
        holder.languages.setText("Languages : " + data.getLanguages());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,capital,region,subregion,population,borders,languages;
        SVGImageView flag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.country);
            capital =itemView.findViewById(R.id.capital);
            region =itemView.findViewById(R.id.region);
            subregion =itemView.findViewById(R.id.subregion);
            population =itemView.findViewById(R.id.population);
            borders =itemView.findViewById(R.id.borders);
            languages =itemView.findViewById(R.id.languages);
            flag =itemView.findViewById(R.id.flag);


        }


    }
}
