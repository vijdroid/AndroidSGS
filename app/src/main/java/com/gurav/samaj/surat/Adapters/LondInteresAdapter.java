package com.gurav.samaj.surat.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gurav.samaj.surat.Model.LoanderIntrestData;
import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Widgets.NumberToCurrencyExamples;
import com.gurav.samaj.surat.datetimeutils.DateTimeStyle;
import com.gurav.samaj.surat.datetimeutils.DateTimeUtils;

import java.util.List;

public class LondInteresAdapter extends RecyclerView.Adapter<LondInteresAdapter.MyViewHolder> {
    Activity activity;
    /* access modifiers changed from: private */
    public List<LoanderIntrestData> moviesList;
    Builder builder;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mudal;
        public TextView vyaj;
        public TextView letpay;
        public TextView tdate;


        public MyViewHolder(View view) {
            super(view);
            this.mudal = (TextView) view.findViewById(R.id.tv_imudal);
            this.vyaj = (TextView) view.findViewById(R.id.tv_vyaj);
            this.letpay = (TextView) view.findViewById(R.id.tv_letpay);
            this.tdate = (TextView) view.findViewById(R.id.tv_idate);
        }
    }

    public LondInteresAdapter(List<LoanderIntrestData> moviesList, Activity activity2) {
        this.moviesList = moviesList;
        this.activity = activity2;
        this.builder = new Builder(activity2);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_interse_layout, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tdate.setText(DateTimeUtils.formatWithStyle(moviesList.get(position).updated_date, DateTimeStyle.MEDIUM));
        holder.mudal.setText(activity.getResources().getString(R.string.Rs)+ NumberToCurrencyExamples.getAmount(Double.valueOf(moviesList.get(position).lint_emi_amount)));
        holder.vyaj.setText(activity.getResources().getString(R.string.Rs)+ NumberToCurrencyExamples.getAmount(Double.valueOf(moviesList.get(position).int_amount)));
        holder.letpay.setText(activity.getResources().getString(R.string.Rs)+ NumberToCurrencyExamples.getAmount(Double.valueOf(moviesList.get(position).int_let_pay)));

    }


    public int getItemCount() {
        return this.moviesList.size();
    }
}
