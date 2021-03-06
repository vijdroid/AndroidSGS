package com.gurav.samaj.surat.Activitys;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.gurav.samaj.surat.Adapters.CreditDengiAdapter;
import com.gurav.samaj.surat.Model.AccountsData;
import com.gurav.samaj.surat.Model.CraditDengiModel;
import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CreditDendarActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    LinearLayout llnonet, lldetail;
    TextView errInfo, todate, fromdate;
    DatePickerDialog picker;
    RecyclerView recyclerView;
    String todatesql = "", fromdatesql = "";
    CreditDengiAdapter creditDengiAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_dendar);
        getSupportActionBar().hide();
        initView();
        setData(false);
    }

    private void initView() {
        llnonet = findViewById(R.id.ll_nodata_nonet);
        errInfo = findViewById(R.id.tv_err_info);
        progressBar = findViewById(R.id.pb_progress);
        lldetail = findViewById(R.id.ll_detail);
        recyclerView = findViewById(R.id.rc_stament);
        fromdate = findViewById(R.id.txt_from_date);
        todate = findViewById(R.id.txt_to_date);

        fromdate.setOnClickListener(this);
        todate.setOnClickListener(this);

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
                setData(false);
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = df.format(c);
                todate.setText(formattedDate);
                fromdate.setText(formattedDate);
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);
        todate.setText(formattedDate);
        fromdate.setText(formattedDate);
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
                picker = new DatePickerDialog(CreditDendarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                fromdatesql = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            }
                        }, fyear, fmonth, fday);
                picker.show();
                break;
            case R.id.txt_to_date:
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CreditDendarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                todate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                todatesql = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                setData(true);
                            }
                        }, year, month, day);
                picker.show();
                break;
        }
    }

    private void setData(boolean b) {

        if (CommonObject.isNetworkConnected(CreditDendarActivity.this)) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("token", "4df05645gh6456565h546fg65bg6545");
            if (b) {
                headers.put("todate", fromdatesql);
                headers.put("fromdate", todatesql);
            }
            NetworkHelper networkHelper = new NetworkHelper(CreditDendarActivity.this, SGSApplicaiton.BASEURL);
            networkHelper.sendRequest("/reader/getCreditDendar.php", headers, new NetworkCallback() {
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
                    CommonObject.craditDengiModel = gson.fromJson(response, CraditDengiModel.class);
                    if (CommonObject.craditDengiModel.users != null) {
                        if (CommonObject.craditDengiModel.users.size() > 0) {
                            getData(CommonObject.craditDengiModel.users);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.GONE);
                            errInfo.setText("No Data Found.");
                            llnonet.setVisibility(View.VISIBLE);
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
            }, CreditDendarActivity.this);
        } else {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            errInfo.setText("No Internet Connection.");
            llnonet.setVisibility(View.VISIBLE);
        }
    }

    private void getData(List<UsersData> users) {
        List<String> temp=new ArrayList<>();
        List<UsersData> tempuserdata=new ArrayList<>();



        for(UsersData usersData: users){
            if(!temp.contains(usersData.did)){
                temp.add(usersData.did);
                tempuserdata.add(new UsersData(usersData.dname,usersData.dphone,usersData.did,usersData.updated_date,getAmount(usersData.did),usersData.amount));
            }
        }
        creditDengiAdapter = new CreditDengiAdapter(tempuserdata, CreditDendarActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreditDendarActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(creditDengiAdapter);
    }


    private String getAmount(String did) {
        int amt=0;
        for(UsersData usersData: CommonObject.craditDengiModel.users){

            if(usersData.did.equals(did))
            {
                amt=amt+Integer.parseInt(usersData.cd_amount);
            }
        }
        return String.valueOf(amt);
    }
}
