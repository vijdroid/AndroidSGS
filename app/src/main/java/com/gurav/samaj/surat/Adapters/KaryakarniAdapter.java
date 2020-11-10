package com.gurav.samaj.surat.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Widgets.NumberToCurrencyExamples;

import java.util.List;

public class KaryakarniAdapter extends RecyclerView.Adapter<KaryakarniAdapter.MyViewHolder> {
    Activity activity;
    /* access modifiers changed from: private */
    public List<UsersData> moviesList;
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
        }
    }

    public KaryakarniAdapter(List<UsersData> moviesList, Activity activity2) {
        this.moviesList = moviesList;
        this.activity = activity2;
        this.builder = new Builder(activity2);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.body_layout, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.odha.setText(getodha(moviesList.get(position).amount));

        holder.amount.setText(activity.getResources().getString(R.string.Rs)+NumberToCurrencyExamples.getAmount(Double.valueOf(moviesList.get(position).amount)));
        holder.name.setText((moviesList.get(position).dname));
        if (moviesList.get(position).dphone.equals("0000000000")) {
            holder.phone.setText("");
        } else {
            holder.phone.setText((moviesList.get(position).dphone));
        }
        holder.phone.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                try {
                    if (!(moviesList.get(position).dphone.equals(""))) {
                        builder.setMessage("Are you sure you want to call ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callIntent = new Intent("android.intent.action.DIAL");
                                StringBuilder sb = new StringBuilder();
                                sb.append("tel:");
                                sb.append((moviesList.get(position).dphone));
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

    private String getodha(String amount) {
        StringBuilder sb = new StringBuilder();
        sb.append("getodha: ");
        sb.append(amount);
        Log.d("TAg", sb.toString());
        if (amount != null) {
            try {
                if (!amount.equals("")) {
                    int am = Integer.parseInt(amount);
                    if (am >= 11000 && am <=20000) {
                        return "AajIvn swasd";
                    }
                    if (am >= 21000 && am<=30000) {
                        return "kaym Sv=pI Aa&y data benr lagel";
                    }
                    if (am >= 31000 && am<=50000) {
                        return "kaym Sv=pI ANndan data Aa`I AajIvn sTkar kela ja{l ";
                    }
                    if (am >= 51000) {
                        return "kaym Sv=pI ANndan data Aa`I AajIvn p/muq paVhu`e ";
                    }
//                    if (am != 101000) {
//                        return "";
//                    }
                    return "kaym Sv=pI ANndan data Aa`I AajIvn p/muq paVhu`e ";
                }
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }

    public int getItemCount() {
        return this.moviesList.size();
    }
}
