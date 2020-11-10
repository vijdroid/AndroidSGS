package com.gurav.samaj.surat.Activitys;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

public class SarvKaryaActivity extends AppCompatActivity {
    BodyAdapter bodyAdapter;
    BodyAdapter adp_mahila;
    BodyAdapter adp_shiksha;
    BodyAdapter adp_trust;
    BodyAdapter adp_varishta;
    BodyAdapter adp_viva;
    List<Body> bodycriket = new ArrayList();
    List<Body> bodymahil = new ArrayList();
    List<Body> bodyshik = new ArrayList();
    List<Body> bodytrust = new ArrayList();
    List<Body> bodyvat = new ArrayList();
    List<Body> bodyviva = new ArrayList();
    RecyclerView crickt;
    RecyclerView mahila;
    RecyclerView rcvarishta;
    RecyclerView shiksha;
    RecyclerView trust;
    RecyclerView viva;

    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_sarv_karya);
        getSupportActionBar().hide();
        rcvarishta = (RecyclerView) findViewById(R.id.rc_varisda);
        trust = (RecyclerView) findViewById(R.id.rc_trast);
        viva = (RecyclerView) findViewById(R.id.rc_vava);
        shiksha = (RecyclerView) findViewById(R.id.rc_shikshan);
        crickt = (RecyclerView) findViewById(R.id.rc_criket);
        mahila = (RecyclerView) findViewById(R.id.rc_mahila);
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
                collectDAta();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });
        collectDAta();
    }

    private void collectDAta() {

        setData();
        settrsut();
        setviva();
        setshika();
        setcrikt();
        setmahila();
    }

    private void setmahila() {
//        try {
//            if (bodymahil != null) {
//                bodymahil.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = bodymahil;
//        Body body = new Body("A@y9", "", "", "", "");
//        list.add(body);
//        List<Body> list2 = bodymahil;
//        Body body2 = new Body("]p-A@y9", "", "", "", "");
//        list2.add(body2);
//        List<Body> list3 = bodymahil;
//        Body body3 = new Body("Seak/e3rI", "", "", "", "");
//        list3.add(body3);
//        List<Body> list4 = bodymahil;
//        Body body4 = new Body("Jao-ske/3rIe", "", "", "", "");
//        list4.add(body4);
//        List<Body> list5 = bodymahil;
//        Body body5 = new Body("ko8a@y9", "", "", "", "");
//        list5.add(body5);
//        List<Body> list6 = bodymahil;
//        Body body6 = new Body("kmI3I p/muq", "", "", "", "");
//        list6.add(body6);
//        List<Body> list7 = bodymahil;
//        Body body7 = new Body("AoDI3r", "", "", "", "");
//        list7.add(body7);
//        adp_mahila = new BodyAdapter(bodymahil, this);
//        mahila.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        mahila.setItemAnimator(new DefaultItemAnimator());
//        mahila.setAdapter(adp_mahila);
        if (CommonObject.isNetworkConnected(SarvKaryaActivity.this)) {
          


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "sqI mihla m.;l");
            NetworkHelper networkHelper = new NetworkHelper(SarvKaryaActivity.this, SGSApplicaiton.BASEURL);
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
                        mahila.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,mahila);
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();
                    progressBar.setVisibility(View.GONE);
                }
            }, SarvKaryaActivity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setcrikt() {
//        try {
//            if (bodycriket != null) {
//                bodycriket.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = bodycriket;
//        Body body = new Body("A@y9", "Manoj gopal gurv", "9924562316", "", "");
//        list.add(body);
//        List<Body> list2 = bodycriket;
//        Body body2 = new Body("]p-A@y9", "", "", "", "");
//        list2.add(body2);
//        List<Body> list3 = bodycriket;
//        Body body3 = new Body("Seak/e3rI", "rahul lIlaka.t gurv", "8160779151", "", "");
//        list3.add(body3);
//        List<Body> list4 = bodycriket;
//        Body body4 = new Body("Jao-ske/3rIe", "", "", "", "");
//        list4.add(body4);
//        List<Body> list5 = bodycriket;
//        Body body5 = new Body("ko8a@y9", "Mahex mukuNd cOhan", "9924562316", "", "");
//        list5.add(body5);
//        List<Body> list6 = bodycriket;
//        Body body6 = new Body("AoDI3r", "", "", "", "");
//        list6.add(body6);
//        List<Body> list7 = bodycriket;
//        Body body7 = new Body("k/Ike3 p/muq", "", "", "", "");
//        list7.add(body7);
//        List<Body> list8 = bodycriket;
//        Body body8 = new Body("koc", "", "", "", "");
//        list8.add(body8);
//        List<Body> list9 = bodycriket;
//        Body body9 = new Body("slagar", "", "", "", "");
//        list9.add(body9);
//        List<Body> list10 = bodycriket;
//        Body body10 = new Body("slagar", "", "", "", "");
//        list10.add(body10);
//        adp_crickt = new BodyAdapter(bodycriket, this);
//        crickt.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        crickt.setItemAnimator(new DefaultItemAnimator());
//        crickt.setAdapter(adp_crickt);
        if (CommonObject.isNetworkConnected(SarvKaryaActivity.this)) {
          


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "k/Ike3 s.63n m.D;");
            NetworkHelper networkHelper = new NetworkHelper(SarvKaryaActivity.this, SGSApplicaiton.BASEURL);
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
                        crickt.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,crickt);
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();
                    progressBar.setVisibility(View.GONE);
                }
            }, SarvKaryaActivity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setshika() {
//        try {
//            if (bodyshik != null) {
//                bodyshik.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = bodyshik;
//        Body body = new Body("cIf 3/S3I", "ivXvna4 ramdas gurv", "7984207082", "", "");
//        list.add(body);
//        List<Body> list2 = bodyshik;
//        Body body2 = new Body("A@y9", "ritlal kaxIna4 gurv", "8866541626", "", "");
//        list2.add(body2);
//        List<Body> list3 = bodyshik;
//        Body body3 = new Body("]p-A@y9", "suinl kaxIna4 gurv", "9724437823", "", "");
//        list3.add(body3);
//        List<Body> list4 = bodyshik;
//        Body body4 = new Body("Seak/e3rI", ")aneXvr gorq gurv", "6355797675", "", "");
//        list4.add(body4);
//        List<Body> list5 = bodyshik;
//        Body body5 = new Body("Jao-ske/3rIe", "Aan.d sudam gurv", "9427647035", "", "");
//        list5.add(body5);
//        List<Body> list6 = bodyshik;
//        Body body6 = new Body("ko8a@y9", "rajeNd/ iwmrav gurv", "", "", "");
//        list6.add(body6);
//        List<Body> list7 = bodyshik;
//        Body body7 = new Body("AoDI3r", "p/Xaa.t nrex gurv", "", "", "");
//        list7.add(body7);
//        List<Body> list8 = bodyshik;
//        Body body8 = new Body("ivSvSt mD.;I", "iwqa ]Ttm gurv", "9898440041", "", "");
//        list8.add(body8);
//        List<Body> list9 = bodyshik;
//        Body body9 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list9.add(body9);
//        List<Body> list10 = bodyshik;
//        Body body10 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list10.add(body10);
//        List<Body> list11 = bodyshik;
//        Body body11 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list11.add(body11);
//        List<Body> list12 = bodyshik;
//        Body body12 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list12.add(body12);
//        List<Body> list13 = bodyshik;
//        Body body13 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list13.add(body13);
//        List<Body> list14 = bodyshik;
//        Body body14 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list14.add(body14);
//        List<Body> list15 = bodyshik;
//        Body body15 = new Body("slagar", "", "", "", "");
//        list15.add(body15);
//        List<Body> list16 = bodyshik;
//        Body body16 = new Body("slagar", "", "", "", "");
//        list16.add(body16);
//        List<Body> list17 = bodyshik;
//        Body body17 = new Body("slagar", "", "", "", "");
//        list17.add(body17);
//        List<Body> list18 = bodyshik;
//        Body body18 = new Body("slagar", "", "", "", "");
//        list18.add(body18);
//        List<Body> list19 = bodyshik;
//        Body body19 = new Body("slagar", "", "", "", "");
//        list19.add(body19);
//        List<Body> list20 = bodyshik;
//        Body body20 = new Body("slagar", "", "", "", "");
//        list20.add(body20);
//        adp_shiksha = new BodyAdapter(bodyshik, this);
//        shiksha.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        shiksha.setItemAnimator(new DefaultItemAnimator());
//        shiksha.setAdapter(adp_shiksha);
        if (CommonObject.isNetworkConnected(SarvKaryaActivity.this)) {
          


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "xI9` smItI");
            NetworkHelper networkHelper = new NetworkHelper(SarvKaryaActivity.this, SGSApplicaiton.BASEURL);
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
                        shiksha.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,shiksha);
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();
                    progressBar.setVisibility(View.GONE);

                }
            }, SarvKaryaActivity.this);
        } else {
            //rcvarishta.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
//            errInfo.setText("No Internet Connection.");
//            llnonet.setVisibility(View.VISIBLE);
        }
    }

    private void setviva() {
//        try {
//            if (bodyshik != null) {
//                bodyshik.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = bodyviva;
//        Body body = new Body("cIf 3/S3I", "mnohr sonu devre", "9913315401", "", "");
//        list.add(body);
//        List<Body> list2 = bodyviva;
//        Body body2 = new Body("A@y9", "gopal prxram gurv", "9106277569", "", "");
//        list2.add(body2);
//        List<Body> list3 = bodyviva;
//        Body body3 = new Body("]p-A@y9", "x.kr govId.rav gurv", "", "", "");
//        list3.add(body3);
//        List<Body> list4 = bodyviva;
//        Body body4 = new Body("Seak/e3rI", "", "", "", "");
//        list4.add(body4);
//        List<Body> list5 = bodyviva;
//        Body body5 = new Body("Jao-ske/3rIe", "", "", "", "");
//        list5.add(body5);
//        List<Body> list6 = bodyviva;
//        Body body6 = new Body("ko8a@y9", "muku.Nd suklal gurv", "9427827376", "", "");
//        list6.add(body6);
//        List<Body> list7 = bodyviva;
//        Body body7 = new Body("AoDI3r", "", "", "", "");
//        list7.add(body7);
//        List<Body> list8 = bodyviva;
//        Body body8 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list8.add(body8);
//        List<Body> list9 = bodyviva;
//        Body body9 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list9.add(body9);
//        List<Body> list10 = bodyviva;
//        Body body10 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list10.add(body10);
//        List<Body> list11 = bodyviva;
//        Body body11 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list11.add(body11);
//        List<Body> list12 = bodyviva;
//        Body body12 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list12.add(body12);
//        List<Body> list13 = bodyviva;
//        Body body13 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list13.add(body13);
//        List<Body> list14 = bodyviva;
//        Body body14 = new Body("ivSvSt mD.;I", "", "", "", "");
//        list14.add(body14);
//        List<Body> list15 = bodyviva;
//        Body body15 = new Body("Salagar", "", "", "", "");
//        list15.add(body15);
//        List<Body> list16 = bodyviva;
//        Body body16 = new Body("Salagar", "", "", "", "");
//        list16.add(body16);
//        List<Body> list17 = bodyviva;
//        Body body17 = new Body("Salagar", "", "", "", "");
//        list17.add(body17);
//        List<Body> list18 = bodyviva;
//        Body body18 = new Body("Salagar", "", "", "", "");
//        list18.add(body18);
//        List<Body> list19 = bodyviva;
//        Body body19 = new Body("Salagar", "", "", "", "");
//        list19.add(body19);
//        List<Body> list20 = bodyviva;
//        Body body20 = new Body("Salagar", "", "", "", "");
//        list20.add(body20);
//        adp_viva = new BodyAdapter(bodyviva, this);
//        viva.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        viva.setItemAnimator(new DefaultItemAnimator());
//        viva.setAdapter(adp_viva);
        if (CommonObject.isNetworkConnected(SarvKaryaActivity.this)) {
          


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "samuihk ivvah smItI");
            NetworkHelper networkHelper = new NetworkHelper(SarvKaryaActivity.this, SGSApplicaiton.BASEURL);
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
                        viva.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,viva);
                    } else {
                       // rcvarishta.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
//                        errInfo.setText("No Data Found");
//                        llnonet.setVisibility(View.VISIBLE);
                    }


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();

                    progressBar.setVisibility(View.GONE);

                }
            }, SarvKaryaActivity.this);
        } else {
           // rcvarishta.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
//            errInfo.setText("No Internet Connection.");
//            llnonet.setVisibility(View.VISIBLE);
        }
    }

    private void settrsut() {
//        try {
//            if (bodytrust != null) {
//                bodytrust.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = bodytrust;
//        Body body = new Body("cIf 3/S3I", "kElax muril2r gurv", "9723357413", "", "");
//        list.add(body);
//        List<Body> list2 = bodytrust;
//        Body body2 = new Body("A@y9", "motIlal kaxIna4 gurv", "9925756244", "", "");
//        list2.add(body2);
//        List<Body> list3 = bodytrust;
//        Body body3 = new Body("]p-A@y9", "p/Xaa.t Axok gurv", "9638058047", "", "");
//        list3.add(body3);
//        List<Body> list4 = bodytrust;
//        Body body4 = new Body("Seak/e3rI", "ikr` bbn gurv", "9879173303", "", "");
//        list4.add(body4);
//        List<Body> list5 = bodytrust;
//        Body body5 = new Body("Jao-ske/3rIe", "p/xa.t m2ukr gurv", "9898412412", "", "");
//        list5.add(body5);
//        List<Body> list6 = bodytrust;
//        Body body6 = new Body("ko8a@y9", "ivxal inlk.# gurv", "9537336625", "", "");
//        list6.add(body6);
//        List<Body> list7 = bodytrust;
//        Body body7 = new Body("AoDI3r", "AnIl p/wakr gurv", "9825658849", "", "");
//        list7.add(body7);
//        List<Body> list8 = bodytrust;
//        Body body8 = new Body("0rIya 3/S3I", "c.du rmex gurv", "8469399072", "", "");
//        list8.add(body8);
//        List<Body> list9 = bodytrust;
//        Body body9 = new Body("0rIya 3/S3I", "idlIp m2ukr gurv", "9898841688", "", "");
//        list9.add(body9);
//        List<Body> list10 = bodytrust;
//        Body body10 = new Body("0rIya 3/S3I", "Aan.drav m2ukr gurv", "8140817343", "", "");
//        list10.add(body10);
//        List<Body> list11 = bodytrust;
//        Body body11 = new Body("0rIya 3/S3I", "nreNd/ ivXvna4 gurv", "9979861797", "", "");
//        list11.add(body11);
//        List<Body> list12 = bodytrust;
//        Body body12 = new Body("0rIya 3/S3I", "rivNd/ prxram gurv", "8128005335", "", "");
//        list12.add(body12);
//        List<Body> list13 = bodytrust;
//        Body body13 = new Body("0rIya 3/S3I", "Ganex rmex gurv", "9978223221", "", "");
//        list13.add(body13);
//        List<Body> list14 = bodytrust;
//        Body body14 = new Body("0rIya 3/S3I", "ramc.d punmc.d gurv", "9725883988", "", "");
//        list14.add(body14);
//        List<Body> list15 = bodytrust;
//        Body body15 = new Body("slagar", "slagar", "", "", "");
//        list15.add(body15);
//        List<Body> list16 = bodytrust;
//        Body body16 = new Body("slagar", "slagar", "", "", "");
//        list16.add(body16);
//        List<Body> list17 = bodytrust;
//        Body body17 = new Body("slagar", "slagar", "", "", "");
//        list17.add(body17);
//        List<Body> list18 = bodytrust;
//        Body body18 = new Body("slagar", "slagar", "", "", "");
//        list18.add(body18);
//        List<Body> list19 = bodytrust;
//        Body body19 = new Body("slagar", "slagar", "", "", "");
//        list19.add(body19);
//        List<Body> list20 = bodytrust;
//        Body body20 = new Body("slagar", "slagar", "", "", "");
//        list20.add(body20);
//        adp_trust = new BodyAdapter(bodytrust, this);
//        trust.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        trust.setItemAnimator(new DefaultItemAnimator());
//        trust.setAdapter(adp_trust);
        if (CommonObject.isNetworkConnected(SarvKaryaActivity.this)) {
          


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "ivkas 3/S3");
            NetworkHelper networkHelper = new NetworkHelper(SarvKaryaActivity.this, SGSApplicaiton.BASEURL);
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
                        rcvarishta.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,trust);
                    } else {
                        //rcvarishta.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
//                        errInfo.setText("No Data Found");
//                        llnonet.setVisibility(View.VISIBLE);
                    }


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();
                  //  rcvarishta.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
//                    errInfo.setText("No Data Found.");
//                    llnonet.setVisibility(View.VISIBLE);
                }
            }, SarvKaryaActivity.this);
        } else {
            //rcvarishta.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
//            errInfo.setText("No Internet Connection.");
//            llnonet.setVisibility(View.VISIBLE);
        }
    }

    private void setData() {
//        try {
//            if (bodyvat != null) {
//                bodytrust.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = bodyvat;
//        Body body = new Body("cIf 3/S3I", "ivXvna4 ramdas gurv", "7984207082", "", "");
//        list.add(body);
//        List<Body> list2 = bodyvat;
//        Body body2 = new Body("A@y9", "illaka.t prxram gurv", "6352942291", "", "");
//        list2.add(body2);
//        List<Body> list3 = bodyvat;
//        Body body3 = new Body("]p-A@y9", "iwmrav ramdas gurv", "9924987073", "", "");
//        list3.add(body3);
//        List<Body> list4 = bodyvat;
//        Body body4 = new Body("Seak/e3rI", "Baapurav 7bulal gurv", "9427881056", "", "");
//        list4.add(body4);
//        List<Body> list5 = bodyvat;
//        Body body5 = new Body("Jao-ske/3rIe", "idnex nanawa] gurv", "9687627290", "", "");
//        list5.add(body5);
//        List<Body> list6 = bodyvat;
//        Body body6 = new Body("ko8a@y9", "nrex gorq gurv", "9428455370", "", "");
//        list6.add(body6);
//        List<Body> list7 = bodyvat;
//        Body body7 = new Body("AoDI3r", "Axok ihralal gurv", "9427187643", "", "");
//        list7.add(body7);
//        List<Body> list8 = bodyvat;
//        Body body8 = new Body("0rIya pmuq vra7a zon", "rvINd/ m2ukr gurv", "9726974559", "", "");
//        list8.add(body8);
//        List<Body> list9 = bodyvat;
//        Body body9 = new Body("0rIya pmuq puna-jolva", "Xaa.taram gulabrav gurv", "9537172455", "", "");
//        list9.add(body9);
//        List<Body> list10 = bodyvat;
//        Body body10 = new Body("0rIya pmuq ]2na-paDe.sra", "", "", "", "");
//        list10.add(body10);
//        List<Body> list11 = bodyvat;
//        Body body11 = new Body("0rIya pmuq weStan-scIn", "", "", "", "");
//        list11.add(body11);
//        List<Body> list12 = bodyvat;
//        Body body12 = new Body("0rIya pmuq A#va,ADajn,ktargam", "mnohr sonu devre", "9913315401", "", "");
//        list12.add(body12);
//        List<Body> list13 = bodyvat;
//        Body body13 = new Body("0rIya pmuq DI.DolI zon", "p/wakr rajaram gurv", "6351583663", "", "");
//        list13.add(body13);
//        List<Body> list14 = bodyvat;
//        Body body14 = new Body("0rIya pmuq lIba.yt", "Aru` hirc.Nd/ gurv", "9724191055", "", "");
//        list14.add(body14);
//        List<Body> list15 = bodyvat;
//        Body body15 = new Body("Salagar", "gopal prxram gurv", "9106277569", "", "");
//        list15.add(body15);
//        List<Body> list16 = bodyvat;
//        Body body16 = new Body("Salagar", "motIlal kaxIna4 gurv", "9925756244", "", "");
//        list16.add(body16);
//        List<Body> list17 = bodyvat;
//        Body body17 = new Body("Salagar", "ritlal kaxIna4 gurv", "8866541626", "", "");
//        list17.add(body17);
//        List<Body> list18 = bodyvat;
//        Body body18 = new Body("Salagar", "muku.Nd suklal gurv", "9427827376", "", "");
//        list18.add(body18);
//        List<Body> list19 = bodyvat;
//        Body body19 = new Body("Salagar", "Axok p.DIt gurv", "9904572347", "", "");
//        list19.add(body19);
//        List<Body> list20 = bodyvat;
//        Body body20 = new Body("Salagar", "p/kaxc/.d/ ihralal gurv", "", "", "");
//        list20.add(body20);
//        adp_varishta = new BodyAdapter(bodyvat, this);
//        rcvarishta.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        rcvarishta.setItemAnimator(new DefaultItemAnimator());
//        rcvarishta.setAdapter(adp_varishta);


        if (CommonObject.isNetworkConnected(SarvKaryaActivity.this)) {
          


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "vrI*# gurv");
            NetworkHelper networkHelper = new NetworkHelper(SarvKaryaActivity.this, SGSApplicaiton.BASEURL);
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
                        rcvarishta.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,rcvarishta);
                    } else {
                        rcvarishta.setVisibility(View.GONE);
                     
                    }


                    //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();
                    progressBar.setVisibility(View.GONE);
                    
                }
            }, SarvKaryaActivity.this);
        } else {
            rcvarishta.setVisibility(View.GONE);
          
        }
    }
    private void getData(List<UsersData> users, RecyclerView rview) {
        bodyAdapter = new BodyAdapter(users, SarvKaryaActivity.this);
        rview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(bodyAdapter);
    }
}
