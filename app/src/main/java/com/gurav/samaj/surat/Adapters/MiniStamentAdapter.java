package com.gurav.samaj.surat.Adapters;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gurav.samaj.surat.Model.AccountsData;
import com.gurav.samaj.surat.R;

import java.util.List;

public class MiniStamentAdapter extends RecyclerView.Adapter<MiniStamentAdapter.MyViewHolder> {
    Activity activity;
    /* access modifiers changed from: private */
    public List<AccountsData> moviesList;
    Builder builder;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtsttitle;
        public TextView txtstcredit;
        public TextView txtstdebit;
        public LinearLayout llstament;

        public MyViewHolder(View view) {
            super(view);
            txtstdebit = (TextView) view.findViewById(R.id.txt_st_debit);
            txtsttitle = (TextView) view.findViewById(R.id.txt_st_title);
            txtstcredit = (TextView) view.findViewById(R.id.txt_st_credit);
            llstament = view.findViewById(R.id.ll_stament);
        }
    }

    public MiniStamentAdapter(List<AccountsData> moviesList, Activity activity2) {
        this.moviesList = moviesList;
        this.activity = activity2;
        this.builder = new Builder(activity2);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stament, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtsttitle.setText(moviesList.get(position).acc_title);
        holder.txtstcredit.setText(activity.getResources().getString(R.string.Rs)+moviesList.get(position).acc_credit);
        holder.txtstdebit.setText(activity.getResources().getString(R.string.Rs)+moviesList.get(position).acc_debit);
        if(position%2==0)
        {
            holder.llstament.setBackgroundColor(activity.getResources().getColor(R.color.even));
        }else {
            holder.llstament.setBackgroundColor(activity.getResources().getColor(R.color.odd));
        }


//        holder.odha.setText(getodha(moviesList.get(position).amount));
//
//        holder.amount.setText(activity.getResources().getString(R.string.Rs)+NumberToCurrencyExamples.getAmount(Double.valueOf(moviesList.get(position).amount)));
//        holder.name.setText((moviesList.get(position).dname));
//        if (moviesList.get(position).dphone.equals("0000000000")) {
//            holder.phone.setText("");
//        } else {
//            holder.phone.setText((moviesList.get(position).dphone));
//        }

    }


    public int getItemCount() {
        return this.moviesList.size();
    }
}
