package com.gurav.samaj.surat.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;

import java.util.List;

public class BodyAdapter extends RecyclerView.Adapter<BodyAdapter.MyViewHolder> {
    Activity activity;
    /* access modifiers changed from: private */
    public List<UsersData> moviesList;
    Builder builder;
    RequestOptions rq=new RequestOptions();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount;
        public TextView name;
        public TextView odha;
        public TextView phone;
        public ImageView avtar;

        public MyViewHolder(View view) {
            super(view);
            this.odha = (TextView) view.findViewById(R.id.tv_odha);
            this.name = (TextView) view.findViewById(R.id.tv_name);
            this.amount = (TextView) view.findViewById(R.id.tv_amount);
            this.phone = (TextView) view.findViewById(R.id.tv_phone);
            this.avtar =view.findViewById(R.id.profile_avatar);
        }
    }

    public BodyAdapter(List<UsersData> moviesList, Activity activity2) {
        this.moviesList = moviesList;
        this.activity = activity2;
        this.builder = new Builder(activity2);
        rq.error(R.drawable.ic_boy);
        rq.placeholder(R.drawable.ic_boy);
    }

    public BodyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BodyAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.body_layout, parent, false));
    }

    public void onBindViewHolder(BodyAdapter.MyViewHolder holder, final int position) {
        holder.odha.setText(moviesList.get(position).oname);
        if (moviesList.get(position).phone.equals("0000000000")) {
            holder.phone.setText("");
        } else {
            holder.phone.setText(moviesList.get(position).phone);
        }
        holder.amount.setText("");
        holder.name.setText(moviesList.get(position).name);
//        Glide.with(activity)
//                .applyDefaultRequestOptions(rq).load(CommonObject.BASE_PROFILE+moviesList.get(position).name+".jpg").into(holder.avtar);
        holder.phone.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                try {
                    if (!(moviesList.get(position).phone.equals(""))) {
                        builder.setMessage("Are you sure you want to call ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callIntent = new Intent("android.intent.action.DIAL");
                                StringBuilder sb = new StringBuilder();
                                sb.append("tel:");
                                sb.append(moviesList.get(position).phone);
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


    }

    public int getItemCount() {
        return moviesList.size();
    }
}