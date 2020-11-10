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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VaristGuravActivity extends AppCompatActivity {
    BodyAdapter bodyAdapter;
    List<Body> bodyList = new ArrayList();
    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.fragment_varist_gurav);
        getSupportActionBar().hide();
        intiview();
        setData();
    }

    private void intiview() {
        recyclerView =  findViewById(R.id.rv_body);
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
//        List<Body> list = bodyList;
//        Body body = new Body("cIf 3/S3I", "ivXvna4 ramdas gurv", "7984207082", "", "");
//        list.add(body);
//        List<Body> list2 = bodyList;
//        Body body2 = new Body("A@y9", "illaka.t prxram gurv", "6352942291", "", "");
//        list2.add(body2);
//        List<Body> list3 = bodyList;
//        Body body3 = new Body("]p-A@y9", "iwmrav ramdas gurv", "9924987073", "", "");
//        list3.add(body3);
//        List<Body> list4 = bodyList;
//        Body body4 = new Body("Seak/e3rI", "Baapurav 7bulal gurv", "9427881056", "", "");
//        list4.add(body4);
//        List<Body> list5 = bodyList;
//        Body body5 = new Body("Jao-ske/3rIe", "idnex nanawa] gurv", "9687627290", "", "");
//        list5.add(body5);
//        List<Body> list6 = bodyList;
//        Body body6 = new Body("ko8a@y9", "nrexrav gorq gurv", "9428455370", "", "");
//        list6.add(body6);
//        List<Body> list7 = bodyList;
//        Body body7 = new Body("AoDI3r", "Axok ihralal gurv", "9427187643", "", "");
//        list7.add(body7);
//        List<Body> list8 = bodyList;
//        Body body8 = new Body("0rIya pmuq vra7a zon", "rvINd/ m2ukr gurv", "9726974559", "", "");
//        list8.add(body8);
//        List<Body> list9 = bodyList;
//        Body body9 = new Body("0rIya pmuq puna-jolva", "Xaa.taram gulabrav gurv", "9537172455", "", "");
//        list9.add(body9);
//        List<Body> list10 = bodyList;
//        Body body10 = new Body("0rIya pmuq ]2na-paDe.sra", "mhex m6ukr gurv", "9824209749", "", "");
//        list10.add(body10);
//        List<Body> list11 = bodyList;
//        Body body11 = new Body("0rIya pmuq weStan-scIn", "", "", "", "");
//        list11.add(body11);
//        List<Body> list12 = bodyList;
//        Body body12 = new Body("0rIya pmuq A#va,ADajn,ktargam", "mnohr sonu devre", "9913315401", "", "");
//        list12.add(body12);
//        List<Body> list13 = bodyList;
//        Body body13 = new Body("0rIya pmuq DI.DolI zon", "p/wakr rajaram gurv", "6351583663", "", "");
//        list13.add(body13);
//        List<Body> list14 = bodyList;
//        Body body14 = new Body("0rIya pmuq lIba.yt", "Aru` hirc.Nd/ gurv", "9724191055", "", "");
//        list14.add(body14);
//        List<Body> list15 = bodyList;
//        Body body15 = new Body("Salagar", "gopal prxram gurv", "9106277569", "", "");
//        list15.add(body15);
//        List<Body> list16 = bodyList;
//        Body body16 = new Body("Salagar", "motIlal kaxIna4 gurv", "9925756244", "", "");
//        list16.add(body16);
//        List<Body> list17 = bodyList;
//        Body body17 = new Body("Salagar", "ritlal kaxIna4 gurv", "8866541626", "", "");
//        list17.add(body17);
//        List<Body> list18 = bodyList;
//        Body body18 = new Body("Salagar", "muku.Nd suklal gurv", "9427827376", "", "");
//        list18.add(body18);
//        List<Body> list19 = bodyList;
//        Body body19 = new Body("Salagar", "Axok p.DIt gurv", "9904572347", "", "");
//        list19.add(body19);
//        List<Body> list20 = bodyList;
//        Body body20 = new Body("Salagar", "p/kaxc/.d/ ihralal gurv", "", "", "");
//        list20.add(body20);
        //getData();


        if (CommonObject.isNetworkConnected(this)) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "vrI*# gurv");
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
