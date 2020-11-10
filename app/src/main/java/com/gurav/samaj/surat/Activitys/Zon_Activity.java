package com.gurav.samaj.surat.Activitys;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.gurav.samaj.surat.Adapters.BodyAdapter;
import com.gurav.samaj.surat.Model.UserModel;
import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

public class Zon_Activity extends AppCompatActivity {
    BodyAdapter bodyAdapter;
    RecyclerView rcadva;
    RecyclerView rclza;
    RecyclerView rclzb;
    RecyclerView rcpza;
    RecyclerView rcpzb;
    RecyclerView rcuza;
    RecyclerView rcuzb;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_zon_);
        getSupportActionBar().hide();
        intView();
       CollectDAta();
    }

    private void intView() {
        rcadva = (RecyclerView) findViewById(R.id.rc_adhva_zone);
        rcuza = (RecyclerView) findViewById(R.id.rc_undhna_zone_a);
        rcuzb = (RecyclerView) findViewById(R.id.rc_undhna_zone_b);
        rcpza = (RecyclerView) findViewById(R.id.rc_puna_zone_a);
        rcpzb = (RecyclerView) findViewById(R.id.rc_puna_zone_b);
        rclza = (RecyclerView) findViewById(R.id.rc_limyat_zone_a);
        rclzb = (RecyclerView) findViewById(R.id.rc_limyat_zone_b);
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
                CollectDAta();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });
    }

    private void CollectDAta() {
        setlza();
        setlzb();
        setpza();
        setpzb();
        setuza();
        setuzb();
        setadva();
    }

    private void setlza() {
//        try {
//            if (listlza != null) {
//                listlza.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = listlza;
//        Body body = new Body("0rIya p/muq", "Aru` hrIc.d gurv", "9724191055", "", "");
//        list.add(body);
//        List<Body> list2 = listlza;
//        Body body2 = new Body("0rIya 3/S3I", "Aa.ndrav mdukr gurv", "8140817343", "", "");
//        list2.add(body2);
//        List<Body> list3 = listlza;
//        Body body3 = new Body("A@y9", "wup.eNd/ rmex gurv", "9898887648", "", "");
//        list3.add(body3);
//        List<Body> list4 = listlza;
//        Body body4 = new Body("]p-A@y9", "ivjy xa.taram gurv", "9824878113", "", "");
//        list4.add(body4);
//        List<Body> list5 = listlza;
//        Body body5 = new Body("Seak/e3rI", "gopal mgn gurv", "9099742948", "", "");
//        list5.add(body5);
//        List<Body> list6 = listlza;
//        Body body6 = new Body("Jao-ske/3rIe", "rivNd/ ixvajI gurv", "9879236393", "", "");
//        list6.add(body6);
//        List<Body> list7 = listlza;
//        Body body7 = new Body("ko8a@y9", "rajex wIkn gurv", "9825477052", "", "");
//        list7.add(body7);
//        List<Body> list8 = listlza;
//        Body body8 = new Body("kmI3I p/muq", "rmex iv®l gurv", "", "", "");
//        list8.add(body8);
//        adp_lza = new BodyAdapter(listlza, this);
//        rclza.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        rclza.setItemAnimator(new DefaultItemAnimator());
//        rclza.setAdapter(adp_lza);
        if (CommonObject.isNetworkConnected(Zon_Activity.this)) {



            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "lIba.yt DI.DolI zon A");
            NetworkHelper networkHelper = new NetworkHelper(Zon_Activity.this, SGSApplicaiton.BASEURL);
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
                        rclza.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,rclza);
                    } else {
                        rclza.setVisibility(View.GONE);
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
            }, Zon_Activity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setlzb() {
//        try {
//            if (listlzb != null) {
//                listlzb.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = listlzb;
//        Body body = new Body("0rIya p/muq", "p/wakr rajaram gurv", "6351583663", "", "");
//        list.add(body);
//        List<Body> list2 = listlzb;
//        Body body2 = new Body("0rIya 3/S3I", "idlIp m2ukr gurv", "9898841688", "", "");
//        list2.add(body2);
//        List<Body> list3 = listlzb;
//        Body body3 = new Body("A@y9", "goku; Aoka.r gurv", "7016643201", "", "");
//        list3.add(body3);
//        List<Body> list4 = listlzb;
//        Body body4 = new Body("]p-A@y9", ")aneXvr Axok gurv", "9913232795", "", "");
//        list4.add(body4);
//        List<Body> list5 = listlzb;
//        Body body5 = new Body("Seak/e3rI", "Manoj gopal gurv", "9924562316", "", "");
//        list5.add(body5);
//        List<Body> list6 = listlzb;
//        Body body6 = new Body("Jao-ske/3rIe", "Gajan.d {Xvrdas gurv", "9824726318", "", "");
//        list6.add(body6);
//        List<Body> list7 = listlzb;
//        Body body7 = new Body("ko8a@y9", "kIxor gulab gurv", "9662619078", "", "");
//        list7.add(body7);
//        List<Body> list8 = listlzb;
//        Body body8 = new Body("kmI3I p/muq", "gulab ramdas gurv", "9265016449", "", "");
//        list8.add(body8);
//        adp_lzb = new BodyAdapter(listlzb, this);
//        rclzb.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        rclzb.setItemAnimator(new DefaultItemAnimator());
//        rclzb.setAdapter(adp_lzb);
        if (CommonObject.isNetworkConnected(Zon_Activity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "lIba.yt DI.DolI zon B");
            NetworkHelper networkHelper = new NetworkHelper(Zon_Activity.this, SGSApplicaiton.BASEURL);
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
                        rclzb.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,rclzb);
                    } else {
                        rclzb.setVisibility(View.GONE);
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
            }, Zon_Activity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setpza() {
//        try {
//            if (listpza != null) {
//                listpza.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = listpza;
//        Body body = new Body("0rIya p/muq ", "Xaa.taram gulabrav gurv", "9537172455", "", "");
//        list.add(body);
//        List<Body> list2 = listpza;
//        Body body2 = new Body("0rIya 3/S3I", "nreNd/ ivXvna4 gurv", "9979861797", "", "");
//        list2.add(body2);
//        List<Body> list3 = listpza;
//        Body body3 = new Body("A@y9", "ivxal inlk.# gurv", "9537336625", "", "");
//        list3.add(body3);
//        List<Body> list4 = listpza;
//        Body body4 = new Body("]p-A@y9", "", "", "", "");
//        list4.add(body4);
//        List<Body> list5 = listpza;
//        Body body5 = new Body("Seak/e3rI", "sunIl xa.taram gurv", "8866667956", "", "");
//        list5.add(body5);
//        List<Body> list6 = listpza;
//        Body body6 = new Body("Jao-ske/3rIe", "", "", "", "");
//        list6.add(body6);
//        List<Body> list7 = listpza;
//        Body body7 = new Body("ko8a@y9", "Axok p/Hlad gurv", "", "", "");
//        list7.add(body7);
//        List<Body> list8 = listpza;
//        Body body8 = new Body("kmI3I p/muq", "p/kax ihralal xmaR", "9998553517", "", "");
//        list8.add(body8);
//        adp_pza = new BodyAdapter(listpza, this);
//        rcpza.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        rcpza.setItemAnimator(new DefaultItemAnimator());
//        rcpza.setAdapter(adp_pza);
        if (CommonObject.isNetworkConnected(Zon_Activity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "punagam vra7a zon A");
            NetworkHelper networkHelper = new NetworkHelper(Zon_Activity.this, SGSApplicaiton.BASEURL);
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
                        rcpza.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,rcpza);
                    } else {
                        rcpza.setVisibility(View.GONE);
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
            }, Zon_Activity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setpzb() {
//        try {
//            if (listpzb != null) {
//                listpzb.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = listpzb;
//        Body body = new Body("0rIya p/muq", "rvINd/ m2ukr gurv", "9726974559", "", "");
//        list.add(body);
//        List<Body> list2 = listpzb;
//        Body body2 = new Body("0rIya 3/S3I", "rivNd/ prxram gurv", "8128005335", "", "");
//        list2.add(body2);
//        List<Body> list3 = listpzb;
//        Body body3 = new Body("A@y9", "Axok p.DIt gurv", "9904572347", "", "");
//        list3.add(body3);
//        List<Body> list4 = listpzb;
//        Body body4 = new Body("]p-A@y9", "mheNd/ sudam gurv", "9913751555", "", "");
//        list4.add(body4);
//        List<Body> list5 = listpzb;
//        Body body5 = new Body("Seak/e3rI", "p/ful wolen4 gurv", "8460251290", "", "");
//        list5.add(body5);
//        List<Body> list6 = listpzb;
//        Body body6 = new Body("Jao-ske/3rIe", "", "", "", "");
//        list6.add(body6);
//        List<Body> list7 = listpzb;
//        Body body7 = new Body("ko8a@y9", "", "", "", "");
//        list7.add(body7);
//        List<Body> list8 = listpzb;
//        Body body8 = new Body("kmI3I p/muq", "", "", "", "");
//        list8.add(body8);
//        adp_pzb = new BodyAdapter(listpzb, this);
//        rcpzb.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        rcpzb.setItemAnimator(new DefaultItemAnimator());
//        rcpzb.setAdapter(adp_pzb);
        if (CommonObject.isNetworkConnected(Zon_Activity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "punagam vra7a zon B");
            NetworkHelper networkHelper = new NetworkHelper(Zon_Activity.this, SGSApplicaiton.BASEURL);
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
                        rcpzb.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,rcpzb);
                    } else {
                        rcpzb.setVisibility(View.GONE);
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
            }, Zon_Activity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setuza() {
//        try {
//            if (listuza != null) {
//                listuza.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = listuza;
//        Body body = new Body("0rIya p/muq", "", "", "", "");
//        list.add(body);
//        List<Body> list2 = listuza;
//        Body body2 = new Body("0rIya 3/S3I", "c.du rmex gurv", "8469399072", "", "");
//        list2.add(body2);
//        List<Body> list3 = listuza;
//        Body body3 = new Body("A@y9", "walcNd/ barku gurv", "9106611347", "", "");
//        list3.add(body3);
//        List<Body> list4 = listuza;
//        Body body4 = new Body("]p-A@y9", "rajeNd/ baburav gurv ", "7874132555", "", "");
//        list4.add(body4);
//        List<Body> list5 = listuza;
//        Body body5 = new Body("Seak/e3rI", "imtex jgdIx gurv", "9664716195", "", "");
//        list5.add(body5);
//        List<Body> list6 = listuza;
//        Body body6 = new Body("Jao-ske/3rIe", "mukex devIdas gurv", "9904637071", "", "");
//        list6.add(body6);
//        List<Body> list7 = listuza;
//        Body body7 = new Body("ko8a@y9", "mukex rohIdas gurv", "", "", "");
//        list7.add(body7);
//        List<Body> list8 = listuza;
//        Body body8 = new Body("kmI3I p/muq", "sudam naray` gurv", "9904738299", "", "");
//        list8.add(body8);
//        adp_uza = new BodyAdapter(listuza, this);
//        rcuza.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        rcuza.setItemAnimator(new DefaultItemAnimator());
//        rcuza.setAdapter(adp_uza);
        if (CommonObject.isNetworkConnected(Zon_Activity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "]2na paDesra weStan scIn zon A");
            NetworkHelper networkHelper = new NetworkHelper(Zon_Activity.this, SGSApplicaiton.BASEURL);
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
                        rcuza.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,rcuza);
                    } else {
                        rcuza.setVisibility(View.GONE);
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
            }, Zon_Activity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setuzb() {
//        try {
//            if (listuzb != null) {
//                listuzb.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = listuzb;
//        Body body = new Body("0rIya p/muq ", "", "", "", "");
//        list.add(body);
//        List<Body> list2 = listuzb;
//        Body body2 = new Body("0rIya 3/S3I", "Ganex rmex gurv", "9978223221", "", "");
//        list2.add(body2);
//        List<Body> list3 = listuzb;
//        Body body3 = new Body("A@y9", "suklal kaxIna4 gurv", "8320834121", "", "");
//        list3.add(body3);
//        List<Body> list4 = listuzb;
//        Body body4 = new Body("]p-A@y9", "Ainl w3u gurv", "9714982427", "", "");
//        list4.add(body4);
//        List<Body> list5 = listuzb;
//        Body body5 = new Body("Seak/e3r", "ANkex bapurav gurv", "", "", "");
//        list5.add(body5);
//        List<Body> list6 = listuzb;
//        Body body6 = new Body("Jao-ske/3rIe", "Aan.d sudam gurv", "9427647035", "", "");
//        list6.add(body6);
//        List<Body> list7 = listuzb;
//        Body body7 = new Body("ko8a@y9", "mnoj n$4u gurv", "", "", "");
//        list7.add(body7);
//        List<Body> list8 = listuzb;
//        Body body8 = new Body("kmI3I p/muq", ")aneXvr gorq gurv", "6355797675", "", "");
//        list8.add(body8);
//        adp_uzb = new BodyAdapter(listuzb, this);
//        rcuzb.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        rcuzb.setItemAnimator(new DefaultItemAnimator());
//        rcuzb.setAdapter(adp_uzb);
        if (CommonObject.isNetworkConnected(Zon_Activity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "]2na paDesra weStan scIn zon B");
            NetworkHelper networkHelper = new NetworkHelper(Zon_Activity.this, SGSApplicaiton.BASEURL);
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
                        rcuzb.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,rcuzb);
                    } else {
                        rcuzb.setVisibility(View.GONE);
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
            }, Zon_Activity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setadva() {
//        try {
//            if (listadva != null) {
//                listadva.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = listadva;
//        Body body = new Body("0rIya p/muq", "mnohr sonu devre", "9913315401", "", "");
//        list.add(body);
//        List<Body> list2 = listadva;
//        Body body2 = new Body("0rIya 3/S3I", "ramc.d punmc.d gurv", "9725883988", "", "");
//        list2.add(body2);
//        List<Body> list3 = listadva;
//        Body body3 = new Body("A@y9", "prex n.dlal gurv", "8347855602", "", "");
//        list3.add(body3);
//        List<Body> list4 = listadva;
//        Body body4 = new Body("]p-A@y9", "mnoj Aan.drav gurv", "9879040202", "", "");
//        list4.add(body4);
//        List<Body> list5 = listadva;
//        Body body5 = new Body("Seak/e3rI", "", "", "", "");
//        list5.add(body5);
//        List<Body> list6 = listadva;
//        Body body6 = new Body("Jao-ske/3rIe", "idnex p/Hlad gurv", "8238748228", "", "");
//        list6.add(body6);
//        List<Body> list7 = listadva;
//        Body body7 = new Body("ko8a@y9", "", "", "", "");
//        list7.add(body7);
//        List<Body> list8 = listadva;
//        Body body8 = new Body("kmI3I p/muq", "Baapurav 7bulal gurv", "9427881056", "", "");
//        list8.add(body8);
//        adp_adva = new BodyAdapter(listadva, this);
//        rcadva.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        rcadva.setItemAnimator(new DefaultItemAnimator());
//        rcadva.setAdapter(adp_adva);
        if (CommonObject.isNetworkConnected(Zon_Activity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("mid", "A#va ktargam ADajn zon");
            NetworkHelper networkHelper = new NetworkHelper(Zon_Activity.this, SGSApplicaiton.BASEURL);
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
                        rcadva.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        getData(CommonObject.userModel.users,rcadva);
                    } else {
                        rcadva.setVisibility(View.GONE);
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
            }, Zon_Activity.this);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
    private void getData(List<UsersData> users, RecyclerView rview) {
        bodyAdapter = new BodyAdapter(users, Zon_Activity.this);
        rview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(bodyAdapter);
    }
}
