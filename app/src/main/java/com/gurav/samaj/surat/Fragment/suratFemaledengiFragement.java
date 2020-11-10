package com.gurav.samaj.surat.Fragment;

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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.gurav.samaj.surat.Model.Body;
import com.gurav.samaj.surat.Adapters.KaryakarniMahilaAdapter;
import com.gurav.samaj.surat.Model.UserModel;
import com.gurav.samaj.surat.Model.UsersData;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.Widgets.NumberToCurrencyExamples;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class suratFemaledengiFragement extends Fragment {
    KaryakarniMahilaAdapter adp_varishta;
    List<Body> bodyList = new ArrayList();
    RecyclerView recyclerView;
    View view;
    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;
    public static suratFemaledengiFragement newInstance() {
        suratFemaledengiFragement fragment = new suratFemaledengiFragement();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.fragment_dengi, container, false);
      intiview();
        return this.view;
    }

   

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    private void intiview() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_body);
        llnonet = view.findViewById(R.id.ll_nodata_nonet);
        errInfo = view.findViewById(R.id.tv_err_info);
        progressBar = view.findViewById(R.id.pb_data_loader);
        swipeRefreshLayout = view.findViewById(R.id.sw_refesh);
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
//            if (this.bodyList != null) {
//             bodyList.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = this.bodyList;
//        Body body = new Body("", "inlIma mnoj gurv", "9879040202", "", "11000");
//        list.add(body);
//        List<Body> list2 = this.bodyList;
//        Body body2 = new Body("", "xevNtaba{ ivXvna4 gurv", "7567652772", "", "11000");
//        list2.add(body2);
//        List<Body> list3 = this.bodyList;
//        Body body3 = new Body("", "r.jnben nrex gurv", "", "", "11000");
//        list3.add(body3);
//        List<Body> list4 = this.bodyList;
//        Body body4 = new Body("", "Xaardaben )aneXvr gurv", "7984518972", "", "11000");
//        list4.add(body4);
//        List<Body> list5 = this.bodyList;
//        Body body5 = new Body("", "s.gIta nre.d/ gurv", "9824535146", "", "11000");
//        list5.add(body5);
//        List<Body> list6 = this.bodyList;
//        Body body6 = new Body("", "kLpna yograj gurv", "9913822074", "", "5100");
//        list6.add(body6);
//        List<Body> list7 = this.bodyList;
//        Body body7 = new Body("", "7ayaba{ illaka.t gurv", "9978728607", "", "5100");
//        list7.add(body7);
//        List<Body> list8 = this.bodyList;
//        Body body8 = new Body("", "c.³kla gopal gurv", "", "", "5100");
//        list8.add(body8);
//        List<Body> list9 = this.bodyList;
//        Body body9 = new Body("", "v.dna rivNd/ gurv", "9099281127", "", "");
//        list9.add(body9);
//        List<Body> list10 = this.bodyList;
//        Body body10 = new Body("", "vExalI prex gurv", "", "", "");
//        list10.add(body10);
//        List<Body> list11 = this.bodyList;
//        Body body11 = new Body("", "maltIben ritlal gurv", "", "", "");
//        list11.add(body11);
//        List<Body> list12 = this.bodyList;
//        Body body12 = new Body("", "xItl Ainl gurv", "", "", "");
//        list12.add(body12);
//        List<Body> list13 = this.bodyList;
//        Body body13 = new Body("", "sureqa dxr4 gurv", "", "", "");
//        list13.add(body13);
//        List<Body> list14 = this.bodyList;
//        Body body14 = new Body("", "kivta mukex gurv", "", "", "");
//        list14.add(body14);
//        List<Body> list15 = this.bodyList;
//        Body body15 = new Body("", "yogIta idlIp gurv", "", "", "");
//        list15.add(body15);
//        List<Body> list16 = this.bodyList;
//        Body body16 = new Body("", "Aaxaba{ sunIl gurv", "", "", "");
//        list16.add(body16);
//        List<Body> list17 = this.bodyList;
//        Body body17 = new Body("", "v.dna tulxIram gurv", "", "", "");
//        list17.add(body17);
//        List<Body> list18 = this.bodyList;
//        Body body18 = new Body("", "mInaben mnoj gurv", "", "", "");
//        list18.add(body18);
//        List<Body> list19 = this.bodyList;
//        Body body19 = new Body("", "v.dna walcRd/ gurv", "", "", "");
//        list19.add(body19);
//        List<Body> list20 = this.bodyList;
//        Body body20 = new Body("", "JyotI bapurav gurv", "", "", "");
//        list20.add(body20);
//        int total = 0;
//        for (int i = 0; i < this.bodyList.size(); i++) {
//            if (!((Body) this.bodyList.get(i)).amount.isEmpty()) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("setData: ");
//                sb.append(((Body) this.bodyList.get(i)).name);
//                Log.d("TAG", sb.toString());
//                total += Integer.parseInt(((Body) this.bodyList.get(i)).amount);
//            }
//        }
//        ((TextView) this.view.findViewById(R.id.tv_total)).setText(String.valueOf(total));
//     adp_varishta = new KaryakarniMahilaAdapter(this.bodyList, getActivity());
//     rcvarishta.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
//     rcvarishta.setItemAnimator(new DefaultItemAnimator());
//     rcvarishta.setAdapter(this.adp_varishta);

        if (CommonObject.isNetworkConnected(getActivity())) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("dtype", "mihla de`gIdata surt");
            NetworkHelper networkHelper = new NetworkHelper(getActivity(), SGSApplicaiton.BASEURL);
            networkHelper.sendRequest("/reader/getDendar.php", headers, new NetworkCallback() {
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
            }, getActivity());
        } else {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            errInfo.setText("No Internet Connection.");
            llnonet.setVisibility(View.VISIBLE);
        }
    }
    private void getData(List<UsersData> users) {
        int total = 0;
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).amount.isEmpty()) {
                StringBuilder sb = new StringBuilder();

                total += Integer.parseInt((users.get(i).amount));
            }
        }

        ((TextView) view.findViewById(R.id.tv_total)).setText(getResources().getString(R.string.Rs)+NumberToCurrencyExamples.getAmount(Double.valueOf(total)));
        adp_varishta = new KaryakarniMahilaAdapter(users, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adp_varishta);
    }
}
