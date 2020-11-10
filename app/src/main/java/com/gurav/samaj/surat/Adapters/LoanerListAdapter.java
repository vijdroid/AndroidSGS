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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.gurav.samaj.surat.Activitys.LoanerDetailActivity;
import com.gurav.samaj.surat.Model.StatusData;
import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Widgets.NumberToCurrencyExamples;
import com.gurav.samaj.surat.datetimeutils.DateTimeStyle;
import com.gurav.samaj.surat.datetimeutils.DateTimeUtils;

import java.util.List;

public class LoanerListAdapter extends RecyclerView.Adapter<LoanerListAdapter.MyViewHolder> {
    Activity activity;
    /* access modifiers changed from: private */
    public List<StatusData> moviesList;
    Builder builder;
    

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount;
        public TextView name;
        public TextView odha;
        public TextView phone;
        public LinearLayout llroot;


        public MyViewHolder(View view) {
            super(view);
            this.odha = (TextView) view.findViewById(R.id.tv_odha);
            this.name = (TextView) view.findViewById(R.id.tv_name);
            this.amount = (TextView) view.findViewById(R.id.tv_amount);
            this.phone = (TextView) view.findViewById(R.id.tv_phone);
            this.llroot =view.findViewById(R.id.ll_main_root);
        }
    }

    public LoanerListAdapter(List<StatusData> moviesList, Activity activity2) {
        this.moviesList = moviesList;
        this.activity = activity2;
        this.builder = new Builder(activity2);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.readit_dengi_body_layout, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.odha.setText(DateTimeUtils.formatWithStyle( moviesList.get(position).updated_date, DateTimeStyle.MEDIUM));

        holder.amount.setText(activity.getResources().getString(R.string.Rs)+ NumberToCurrencyExamples.getAmount(Double.valueOf(moviesList.get(position).loan_amount)));
        holder.name.setText((moviesList.get(position).loan_name));
        if (moviesList.get(position).loan_phone.equals("0000000000")) {
            holder.phone.setText("");
        } else {
            holder.phone.setText((moviesList.get(position).loan_phone));
        }
        holder.phone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (!(moviesList.get(position).loan_phone.equals(""))) {
                        builder.setMessage("Are you sure you want to call ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callIntent = new Intent("android.intent.action.DIAL");
                                StringBuilder sb = new StringBuilder();
                                sb.append("tel:");
                                sb.append((moviesList.get(position).loan_phone));
                                callIntent.setData(Uri.parse(sb.toString()));
                                activity.startActivity(callIntent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Call Alert");
                        alert.show();
                    }
                } catch (Exception e) {
                }
            }
        });
        
        holder.llroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, LoanerDetailActivity.class).putExtra("uid",moviesList.get(position).loan_id));
            }
        });
        
        

    }


    public int getItemCount() {
        return this.moviesList.size();
    }
}
