package com.gurav.samaj.surat.Adapters;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gurav.samaj.surat.Model.StatusData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Widgets.NumberToCurrencyExamples;
import com.gurav.samaj.surat.datetimeutils.DateTimeStyle;
import com.gurav.samaj.surat.datetimeutils.DateTimeUtils;

import java.util.List;

public class TotalShikhaAdapter extends RecyclerView.Adapter<TotalShikhaAdapter.MyViewHolder> {
    Activity activity;
    /* access modifiers changed from: private */
    public List<StatusData> moviesList;
    Builder builder;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount;
        public TextView name;
        public TextView odha;
        public TextView phone;


        public MyViewHolder(View view) {
            super(view);
            this.odha = (TextView) view.findViewById(R.id.tv_odha);
            this.name = (TextView) view.findViewById(R.id.tv_name);
            this.amount = (TextView) view.findViewById(R.id.tv_amount);
            this.phone = (TextView) view.findViewById(R.id.tv_phone);
            this.phone.setVisibility(View.GONE);
        }
    }

    public TotalShikhaAdapter(List<StatusData> moviesList, Activity activity2) {
        this.moviesList = moviesList;
        this.activity = activity2;
        this.builder = new Builder(activity2);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.total_exp_body_layout, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.odha.setText(DateTimeUtils.formatWithStyle(moviesList.get(position).update_date, DateTimeStyle.MEDIUM));

        holder.amount.setText(activity.getResources().getString(R.string.Rs)+ NumberToCurrencyExamples.getAmount(Double.valueOf(moviesList.get(position).sf_amount)));
        holder.name.setText((moviesList.get(position).sf_title));
    }


    public int getItemCount() {
        return this.moviesList.size();
    }
}
