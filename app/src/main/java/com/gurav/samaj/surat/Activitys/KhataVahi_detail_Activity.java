package com.gurav.samaj.surat.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.gurav.samaj.surat.Model.KhataVahi;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class KhataVahi_detail_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView tamont, debit, credit, txtdengiamt, txtintrestamt, txtvyajamt,
            txtletpamt, txtformincome, txttloanamt, txttexp, txtinttotalamt, txtstatment, txteiamount,txtFound;
    ProgressBar progressBar;
    LinearLayout llnonet, lldetail;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khata_vahi_detail_);
        getSupportActionBar().hide();
        intView();
        setData();
    }


    private void intView() {
        tamont = findViewById(R.id.tv_final_amount);
        credit = findViewById(R.id.tv_cedit_amount);
        debit = findViewById(R.id.tv_debite_amount);
        txtdengiamt = findViewById(R.id.txt_dengi_amt);
        txtintrestamt = findViewById(R.id.txt_intrest_amt);
        txtvyajamt = findViewById(R.id.txt_vyaj_amt);
        txtletpamt = findViewById(R.id.txt_letp_amt);
        txtformincome = findViewById(R.id.txt_form_income);
        txttloanamt = findViewById(R.id.txt_tloan_amt);
        txttexp = findViewById(R.id.txt_texp);
        txtinttotalamt = findViewById(R.id.txt_inttotal_amt);
        txtstatment = findViewById(R.id.txt_statment);
        txteiamount = findViewById(R.id.txt_ei_amt);
        txtFound = findViewById(R.id.txt_found);


        llnonet = findViewById(R.id.ll_nodata_nonet);
        errInfo = findViewById(R.id.tv_err_info);
        progressBar = findViewById(R.id.pb_progress);
        lldetail = findViewById(R.id.ll_detail);

        txtstatment.setOnClickListener(this);

        swipeRefreshLayout = findViewById(R.id.sw_refesh);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                // To keep animation for 4 seconds
                setData();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });

    }

    private void setData() {

        if (CommonObject.isNetworkConnected(KhataVahi_detail_Activity.this)) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("token", "4df05645gh6456565h546fg65bg6545");
            NetworkHelper networkHelper = new NetworkHelper(KhataVahi_detail_Activity.this, SGSApplicaiton.BASEURL);
            networkHelper.sendRequest("/reader/getHomeDetail.php", headers, new NetworkCallback() {
                @Override
                public void onNetworkResult(String response) {
                    //TODO : perform on success

//                String masterData;
//                masterData = "{\"products\":" + response + "}";
//                Log.d("TAG", "Response:: " + masterData);
                    Log.d("TAG", "Response:: " + response);
                    lldetail.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    llnonet.setVisibility(View.GONE);
                    Gson gson = new Gson();
                    CommonObject.khataVahi = gson.fromJson(response, KhataVahi.class);
                    if (CommonObject.khataVahi.status.equals("success")) {

                        try {
                            credit.setText(CommonObject.khataVahi.Accounts.get(0).credit);
                            debit.setText(CommonObject.khataVahi.Accounts.get(0).debite);
                            tamont.setText(CommonObject.khataVahi.Accounts.get(0).total);

                            txtdengiamt.setText(CommonObject.khataVahi.DengiDetail.get(0).total_dengi);
                            txtformincome.setText(CommonObject.khataVahi.FormIncome.get(0).fincamout);
                            txtintrestamt.setText(CommonObject.khataVahi.Interest.get(0).mudal);
                            txtvyajamt.setText(CommonObject.khataVahi.Interest.get(0).vyaj);
                            txtletpamt.setText(CommonObject.khataVahi.Interest.get(0).letpat);
                            txtinttotalamt.setText(CommonObject.khataVahi.Interest.get(0).total);
                            txtformincome.setText(CommonObject.khataVahi.FormIncome.get(0).fincamout);
                            txttloanamt.setText(CommonObject.khataVahi.Loan.get(0).totalloan);
                            txttexp.setText(CommonObject.khataVahi.ExtraExp.get(0).ExtraExp);
                            txtFound.setText(CommonObject.khataVahi.ExtraFound.get(0).ExtraFound);
                            txteiamount.setText((CommonObject.khataVahi.ExtraIncome.get(0).ExtraIncome == null) ? "0" : CommonObject.khataVahi.ExtraIncome.get(0).ExtraIncome);
                        } catch (Exception e) {
                            lldetail.setVisibility(View.GONE);
                            errInfo.setText("No Data Found.");
                            llnonet.setVisibility(View.VISIBLE);
                        }

                    } else {
                        lldetail.setVisibility(View.GONE);
                        errInfo.setText("No Data Found.");
                        llnonet.setVisibility(View.VISIBLE);
                    }

                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();
                    progressBar.setVisibility(View.GONE);
                    lldetail.setVisibility(View.GONE);
                    errInfo.setText("No Data Found.");
                    llnonet.setVisibility(View.VISIBLE);
                }
            }, KhataVahi_detail_Activity.this);
        } else {
            lldetail.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            errInfo.setText("No Internet Connection.");
            llnonet.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_statment:
                startActivity(new Intent(this, MiniStatmentActivity.class));

                break;
        }
    }

    public void showAlldendar(View view) {
        startActivity(new Intent(this, CreditDendarActivity.class));
    }

    public void showAllInteres(View view) {
    }

    public void showAllLoaners(View view) {
        startActivity(new Intent(this, LonerListActivity.class));
    }


    public void showExp(View view) {
        startActivity(new Intent(this, ExtraExpActivity.class));
    }

    public void showExtraIncome(View view) {
        startActivity(new Intent(this, ExtraIncomeActivity.class));
    }

    public void showFondIncome(View view) {
        startActivity(new Intent(this, ShikshaFundActivity.class));
    }
}
