package com.gurav.samaj.surat.Activitys;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.gurav.samaj.surat.Model.Body;
import com.gurav.samaj.surat.Adapters.BodyAdapter;
import com.gurav.samaj.surat.Model.UserModel;
import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YuvaMitraActivity extends AppCompatActivity {
    BodyAdapter bodyAdapter;
    List<Body> bodyList = new ArrayList();
    RecyclerView rcvarishta;
    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.fragment_yuva);
        getSupportActionBar().hide();
        intiview();
        setData();
    }

    private void intiview() {
        rcvarishta =  findViewById(R.id.rv_body);
        llnonet = findViewById(R.id.ll_nodata_nonet);
        errInfo = findViewById(R.id.tv_err_info);
        progressBar = findViewById(R.id.pb_data_loader);
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
//        try {
//            if (bodyList != null) {
//                bodyList.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = bodyList;
//        Body body = new Body("A@y9", "c.du rmex gurv", "8469399072", "", "");
//        list.add(body);
//        List<Body> list2 = bodyList;
//        Body body2 = new Body("]p-A@y9", "ivxal inlk.# gurv", "9537336625", "", "");
//        list2.add(body2);
//        List<Body> list3 = bodyList;
//        Body body3 = new Body("Seak/e3rI", "p/xa.t m2ukr gurv", "9898412412", "", "");
//        list3.add(body3);
//        List<Body> list4 = bodyList;
//        Body body4 = new Body("ko8a@y9", "idlIp m2ukr gurv", "9898841688", "", "");
//        list4.add(body4);
//        List<Body> list5 = bodyList;
//        Body body5 = new Body("Jao-ske/3rIe", "rahul illaka.t gurv", "8160779151", "", "");
//        list5.add(body5);
//        List<Body> list6 = bodyList;
//        Body body6 = new Body("AoDI3r", "ikr` bbn gurv", "9879173303", "", "");
//        list6.add(body6);
//        List<Body> list7 = bodyList;
//        Body body7 = new Body("Salagar", "Aan.drav m2ukr gurv", "8140817343", "", "");
//        list7.add(body7);
//        List<Body> list8 = bodyList;
//        Body body8 = new Body("Salagar", ")aneXvr gorq gurv", "6355797675", "", "");
//        list8.add(body8);
//        List<Body> list9 = bodyList;
//        Body body9 = new Body("Salagar", "kElax muril2r gurv", "9723357413", "", "");
//        list9.add(body9);
//        List<Body> list10 = bodyList;
//        Body body10 = new Body("Salagar", ")aneXvr yadvrav gurv", "7490003165", "", "");
//        list10.add(body10);
//        List<Body> list11 = bodyList;
//        Body body11 = new Body("Salagar", "AnIl p/wakr gurv", "9825658849", "", "");
//        list11.add(body11);
//        List<Body> list12 = bodyList;
//        Body body12 = new Body("kayRkar`I", "prex n.dlal gurv", "8347855602", "", "");
//        list12.add(body12);
//        List<Body> list13 = bodyList;
//        Body body13 = new Body("kayRkar`I", "wupeNd/ rmex gurv", "9898887648", "", "");
//        list13.add(body13);
//        List<Body> list14 = bodyList;
//        Body body14 = new Body("kayRkar`I", "ivjy inlk.# gurv", "9909777496", "", "");
//        list14.add(body14);
//        List<Body> list15 = bodyList;
//        Body body15 = new Body("kayRkar`I", "mnoj n$4u gurv", "", "", "");
//        list15.add(body15);
//        List<Body> list16 = bodyList;
//        Body body16 = new Body("kayRkar`I", "mukex devIdas gurv", "9904637071", "", "");
//        list16.add(body16);
//        List<Body> list17 = bodyList;
//        Body body17 = new Body("kayRkar`I", "p/kax tukaram gurv", "9276827969", "", "");
//        list17.add(body17);
//        adp_varishta = new BodyAdapter(bodyList, this);
//        rcvarishta.setLayoutManager(new LinearLayoutManager(this, 1, false));
//        rcvarishta.setItemAnimator(new DefaultItemAnimator());
//        rcvarishta.setAdapter(adp_varishta);

        if (CommonObject.isNetworkConnected(this)) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "yuva im5 m.;l");
            NetworkHelper networkHelper = new NetworkHelper(this, SGSApplicaiton.BASEURL);
            networkHelper.sendRequest("/reader/getuser.php", headers, new NetworkCallback() {
                @Override
                public void onNetworkResult(String response) {
                    //TODO : perform on success

//                String masterData;
//                masterData = "{\"products\":" + response + "}";
//                Log.d("TAG", "Response:: " + masterData);
                    Log.d("TAG", "Response:: " + response);
                    Gson gson = new Gson();
                    JSONArray arr = null;



                    CommonObject.userModel = gson.fromJson(response, UserModel.class);

                    if (CommonObject.userModel.users.size() > 0) {
                        rcvarishta.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users);
                    } else {
                        rcvarishta.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        errInfo.setText("No Data Found");
                        llnonet.setVisibility(View.VISIBLE);
                    }


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();
                    rcvarishta.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    errInfo.setText("No Data Found.");
                    llnonet.setVisibility(View.VISIBLE);
                }
            }, this);
        } else {
            rcvarishta.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            errInfo.setText("No Internet Connection.");
            llnonet.setVisibility(View.VISIBLE);
        }
    }
    private void getData(List<UsersData> users) {
        bodyAdapter = new BodyAdapter(users, this);
        rcvarishta.setLayoutManager(new LinearLayoutManager(this));
        rcvarishta.setItemAnimator(new DefaultItemAnimator());
        rcvarishta.setAdapter(bodyAdapter);
    }
}
