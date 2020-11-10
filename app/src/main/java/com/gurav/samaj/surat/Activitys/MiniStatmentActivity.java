package com.gurav.samaj.surat.Activitys;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gurav.samaj.surat.Adapters.MiniStamentAdapter;
import com.gurav.samaj.surat.Model.AccountsData;
import com.gurav.samaj.surat.Model.MiniStatmentModel;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MiniStatmentActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    LinearLayout llnonet, lldetail;
    TextView errInfo, todate, fromdate, totalc, totald, total, lmmtotal,tvaccextraincome;
    DatePickerDialog picker;
    RecyclerView recyclerView;
    String todatesql = "", fromdatesql = "";
    MiniStamentAdapter miniStamentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_statment);
        getSupportActionBar().hide();
        initView();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);
        todate.setText(formattedDate);
        fromdate.setText(formattedDate);

    }

    private void initView() {
        llnonet = findViewById(R.id.ll_nodata_nonet);
        errInfo = findViewById(R.id.tv_err_info);
        progressBar = findViewById(R.id.pb_progress);
        lldetail = findViewById(R.id.ll_detail);
        recyclerView = findViewById(R.id.rc_stament);
        fromdate = findViewById(R.id.txt_from_date);
        todate = findViewById(R.id.txt_to_date);
        totalc = findViewById(R.id.tv_acc_ctotal);
        totald = findViewById(R.id.tv_acc_dtotal);
        total = findViewById(R.id.tv_acc_total);
        lmmtotal = findViewById(R.id.tv_acc_lmtotal);
        tvaccextraincome = findViewById(R.id.tv_acc_extra_income);

        fromdate.setOnClickListener(this);
        todate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_from_date:
                final Calendar fcldr = Calendar.getInstance();
                int fday = fcldr.get(Calendar.DAY_OF_MONTH);
                int fmonth = fcldr.get(Calendar.MONTH);
                int fyear = fcldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MiniStatmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                //  fromdatesql = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                                lmmtotal.setText("0");
                                total.setText("0");
                                totalc.setText("0");
                                totald.setText("0");

                                setData(year, (monthOfYear + 1), dayOfMonth);
                            }
                        }, fyear, fmonth, fday);
                picker.show();
                break;
            case R.id.txt_to_date:
                final Calendar cldr = Calendar.getInstance();
//                int day = cldr.get(Calendar.DAY_OF_MONTH);
//                int month = cldr.get(Calendar.MONTH);
//                int year = cldr.get(Calendar.YEAR);
//                // date picker dialog
//                picker = new DatePickerDialog(MiniStatmentActivity.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                todate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                                todatesql = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
////                                setData();
//                            }
//                        }, year, month, day);
//                picker.show();
                break;
        }
    }

    private void setData(int year, int month, int dayOfMonth) {

        if (CommonObject.isNetworkConnected(MiniStatmentActivity.this)) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("token", "4df05645gh6456565h546fg65bg6545");
            headers.put("year", String.valueOf(year));
            headers.put("month", String.valueOf(month));
            headers.put("day", String.valueOf(dayOfMonth));
//            headers.put("fromdate", todatesql);
            NetworkHelper networkHelper = new NetworkHelper(MiniStatmentActivity.this, SGSApplicaiton.BASEURL);
            networkHelper.sendRequest("/reader/getAccountDetail.php", headers, new NetworkCallback() {
                @Override
                public void onNetworkResult(String response) {

                    Log.d("TAG", "Response:: " + response);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    llnonet.setVisibility(View.GONE);
                    //TODO : perform on success

//                String masterData;
//                masterData = "{\"products\":" + response + "}";
//                Log.d("TAG", "Response:: " + masterData);


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                    Gson gson = new Gson();
                    CommonObject.miniStatmentModel = gson.fromJson(response, MiniStatmentModel.class);
                    if (CommonObject.miniStatmentModel.status.equals("success")) {
                        getData(CommonObject.miniStatmentModel.Accounts);

                        if (CommonObject.miniStatmentModel.AccountTotal != null) {
                            if (CommonObject.miniStatmentModel.AccountTotal.size() > 0) {
                                lmmtotal.setText(getResources().getString(R.string.Rs)+CommonObject.miniStatmentModel.LastMonthTotal);
                                tvaccextraincome.setText(getResources().getString(R.string.Rs)+CommonObject.miniStatmentModel.ExtraIncome);
                                total.setText(getResources().getString(R.string.Rs)+String.valueOf(Integer.parseInt(CommonObject.miniStatmentModel.AccountTotal.get(0).total) + Integer.parseInt(CommonObject.miniStatmentModel.LastMonthTotal)+Integer.parseInt(CommonObject.miniStatmentModel.ExtraIncome)));
                                totalc.setText(getResources().getString(R.string.Rs)+CommonObject.miniStatmentModel.AccountTotal.get(0).cradit);
                                totald.setText(getResources().getString(R.string.Rs)+CommonObject.miniStatmentModel.AccountTotal.get(0).debit);
                            }
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        errInfo.setText("No Data Found.");
                        llnonet.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
//                    // CommonObject.progress.dismiss();
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    errInfo.setText("No Data Found.");
                    llnonet.setVisibility(View.VISIBLE);

                }
            }, MiniStatmentActivity.this);
        } else {
            lldetail.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            errInfo.setText("No Internet Connection.");
            llnonet.setVisibility(View.VISIBLE);
        }
    }

    private void getData(List<AccountsData> users) {
        miniStamentAdapter = new MiniStamentAdapter(users, MiniStatmentActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MiniStatmentActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(miniStamentAdapter);


    }
}
