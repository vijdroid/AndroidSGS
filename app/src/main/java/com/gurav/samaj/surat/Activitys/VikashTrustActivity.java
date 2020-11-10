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
import com.gurav.samaj.surat.Adapters.BodyAdapter;
import com.gurav.samaj.surat.Model.Body;
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

public class VikashTrustActivity extends AppCompatActivity {
    BodyAdapter bodyAdapter;
    List<Body> bodyList = new ArrayList();
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_vikash);
        getSupportActionBar().hide();
        intiview();
        setData();
    }



    private void intiview() {
        recyclerView = findViewById(R.id.rv_body);
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
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });
    }


    private void setData() {
//        List<Body> list = this.bodyList;
//        Body body = new Body("cIf 3/S3I", "kElax muril2r gurv", "9723357413", "", "");
//        list.add(body);
//        List<Body> list2 = this.bodyList;
//        Body body2 = new Body("A@y9", "motIlal kaxIna4 gurv", "9925756244", "", "");
//        list2.add(body2);
//        List<Body> list3 = this.bodyList;
//        Body body3 = new Body("]p-A@y9", "p/xa.t Axok gurv", "9638058047", "", "");
//        list3.add(body3);
//        List<Body> list4 = this.bodyList;
//        Body body4 = new Body("Seak/e3rI", "ikr` bbn gurv", "9879173303", "", "");
//        list4.add(body4);
//        List<Body> list5 = this.bodyList;
//        Body body5 = new Body("Jao-ske/3rIe", "p/xa.t m2ukr gurv", "9898412412", "", "");
//        list5.add(body5);
//        List<Body> list6 = this.bodyList;
//        Body body6 = new Body("ko8a@y9", "ivjy inlk.# gurv", "9909777496", "", "");
//        list6.add(body6);
//        List<Body> list7 = this.bodyList;
//        Body body7 = new Body("AoDI3r", "AnIl p/wakr gurv", "9825658849", "", "");
//        list7.add(body7);
//        List<Body> list8 = this.bodyList;
//        Body body8 = new Body("0rIya 3/S3I", "c.du rmex gurv", "8469399072", "", "");
//        list8.add(body8);
//        List<Body> list9 = this.bodyList;
//        Body body9 = new Body("0rIya 3/S3I", "idlIp m2ukr gurv", "9898841688", "", "");
//        list9.add(body9);
//        List<Body> list10 = this.bodyList;
//        Body body10 = new Body("0rIya 3/S3I", "Aan.drav m2ukr gurv", "8140817343", "", "");
//        list10.add(body10);
//        List<Body> list11 = this.bodyList;
//        Body body11 = new Body("0rIya 3/S3I", "nreNd/ ivXvna4 gurv", "9979861797", "", "");
//        list11.add(body11);
//        List<Body> list12 = this.bodyList;
//        Body body12 = new Body("0rIya 3/S3I", "rivNd/ prxram gurv", "8128005335", "", "");
//        list12.add(body12);
//        List<Body> list13 = this.bodyList;
//        Body body13 = new Body("0rIya 3/S3I", "Ganex rmex gurv", "9978223221", "", "");
//        list13.add(body13);
//        List<Body> list14 = this.bodyList;
//        Body body14 = new Body("0rIya 3/S3I", "ramc.d punmc.d gurv", "9725883988", "", "");
//        list14.add(body14);
//        List<Body> list15 = this.bodyList;
//        Body body15 = new Body("p/is²I p/muq", "prex n.dlal gurv", "8347855602", "", "");
//        list15.add(body15);
//        List<Body> list16 = this.bodyList;
//        Body body16 = new Body("slagar", "", "", "", "");
//        list16.add(body16);
//        List<Body> list17 = this.bodyList;
//        Body body17 = new Body("slagar", "", "", "", "");
//        list17.add(body17);
//        List<Body> list18 = this.bodyList;
//        Body body18 = new Body("slagar", "", "", "", "");
//        list18.add(body18);
//        List<Body> list19 = this.bodyList;
//        Body body19 = new Body("slagar", "", "", "", "");
//        list19.add(body19);
//        List<Body> list20 = this.bodyList;
//        Body body20 = new Body("slagar", "", "", "", "");
//        list20.add(body20);
//        List<Body> list21 = this.bodyList;
//        Body body21 = new Body("slagar", "", "", "", "");
//        list21.add(body21);
//        getData();

        if (CommonObject.isNetworkConnected(this)) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "ivkas 3/S3");
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
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users);
                    } else {
                        recyclerView.setVisibility(View.GONE);
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
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    errInfo.setText("No Data Found.");
                    llnonet.setVisibility(View.VISIBLE);
                }
            }, this);
        } else {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            errInfo.setText("No Internet Connection.");
            llnonet.setVisibility(View.VISIBLE);
        }
    }

    private void getData(List<UsersData> users) {
        bodyAdapter = new BodyAdapter(users, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bodyAdapter);
    }
}
