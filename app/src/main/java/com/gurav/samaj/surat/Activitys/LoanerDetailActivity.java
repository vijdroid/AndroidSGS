package com.gurav.samaj.surat.Activitys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gurav.samaj.surat.Adapters.CreditDengiAdapter;
import com.gurav.samaj.surat.Adapters.LoanerListAdapter;
import com.gurav.samaj.surat.Adapters.LondInteresAdapter;
import com.gurav.samaj.surat.Model.LoanderIntrestData;
import com.gurav.samaj.surat.Model.LonderDetailModel;
import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.datetimeutils.DateTimeStyle;
import com.gurav.samaj.surat.datetimeutils.DateTimeUtils;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import java.util.HashMap;
import java.util.List;

public class LoanerDetailActivity extends AppCompatActivity {
    String uid = "";
    ProgressBar progressBar;
    LinearLayout llnonet, lldetail;
    TextView errInfo;
    RecyclerView recyclerView;
    LondInteresAdapter londInteresAdapter;
    TextView name, phone, month, amount, jf_name, jf_phone, js_name, js_phone,addres,mdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaner_detail);
        getSupportActionBar().hide();
        initView();
        getIndata();
    }

    private void getIndata() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("uid");

            getData(value);
            //The key argument here must match that used in the other activity
        }
    }

    private void initView() {
        llnonet = findViewById(R.id.ll_nodata_nonet);
        errInfo = findViewById(R.id.tv_err_info);
        progressBar = findViewById(R.id.pb_progress);
        lldetail = findViewById(R.id.ll_detail);
        recyclerView = findViewById(R.id.rc_stament);

        name = findViewById(R.id.tv_name);
        phone = findViewById(R.id.tv_phone);
        month = findViewById(R.id.tv_month);
        amount = findViewById(R.id.tv_amount);
        jf_name = findViewById(R.id.tv_jf_name);
        jf_phone = findViewById(R.id.tv_jf_phone);
        js_name = findViewById(R.id.tv_js_name);
        js_phone = findViewById(R.id.tv_js_phone);
        addres = findViewById(R.id.address);
        mdate = findViewById(R.id.tvmdate);

    }

    private void getData(String value) {
        if (CommonObject.isNetworkConnected(LoanerDetailActivity.this)) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("token", "4df05645gh6456565h546fg65bg6545");
            headers.put("user_id", value);

            NetworkHelper networkHelper = new NetworkHelper(LoanerDetailActivity.this, SGSApplicaiton.BASEURL);
            networkHelper.sendRequest("/reader/getLoanerDetail.php", headers, new NetworkCallback() {
                @Override
                public void onNetworkResult(String response) {

                    Log.d("TAG", "Response:: " + response);
                    lldetail.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    llnonet.setVisibility(View.GONE);
                    //TODO : perform on success

//                String masterData;
//                masterData = "{\"products\":" + response + "}";
//                Log.d("TAG", "Response:: " + masterData);


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                    Gson gson = new Gson();
                    CommonObject.londerDetailModel = gson.fromJson(response, LonderDetailModel.class);
                    if (CommonObject.londerDetailModel.status.equals("success")) {

                        if (CommonObject.londerDetailModel.LoanerUser != null) {
                            if (CommonObject.londerDetailModel.LoanerUser.size() > 0) {
                                name.setText(CommonObject.londerDetailModel.LoanerUser.get(0).loan_name);
                                amount.setText(getResources().getString(R.string.Rs)+CommonObject.londerDetailModel.LoanerUser.get(0).loan_amount);
                                phone.setText(CommonObject.londerDetailModel.LoanerUser.get(0).loan_phone);
                                month.setText(CommonObject.londerDetailModel.LoanerUser.get(0).loan_month);
                                jf_name.setText(CommonObject.londerDetailModel.LoanerUser.get(0).fnomi_name);
                                jf_phone.setText(CommonObject.londerDetailModel.LoanerUser.get(0).fnomi_phone);
                                js_name.setText(CommonObject.londerDetailModel.LoanerUser.get(0).snomi_name);
                                js_phone.setText(CommonObject.londerDetailModel.LoanerUser.get(0).snomi_phone);
                                addres.setText(CommonObject.londerDetailModel.LoanerUser.get(0).loan_address);
                                mdate.setText(DateTimeUtils.formatWithStyle( CommonObject.londerDetailModel.LoanerUser.get(0).updated_date, DateTimeStyle.FULL));
                            }


                            if (CommonObject.londerDetailModel.LoanderIntrest.size() > 0) {

                                getData(CommonObject.londerDetailModel.LoanderIntrest);

                            }
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        lldetail.setVisibility(View.GONE);
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
                    lldetail.setVisibility(View.GONE);
                    errInfo.setText("No Data Found.");
                    llnonet.setVisibility(View.VISIBLE);

                }
            }, LoanerDetailActivity.this);
        } else {
            lldetail.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            errInfo.setText("No Internet Connection.");
            llnonet.setVisibility(View.VISIBLE);
        }
    }

    private void getData(List<LoanderIntrestData> users) {
        londInteresAdapter = new LondInteresAdapter(users, LoanerDetailActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(LoanerDetailActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(londInteresAdapter);
    }
}
