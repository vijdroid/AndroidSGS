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
import com.gurav.samaj.surat.Adapters.KaryakarniAdapter;
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

public class etardengiFragement extends Fragment {
    KaryakarniAdapter adp_varishta;
    List<Body> bodyList = new ArrayList();
    RecyclerView recyclerView;
    View view;
    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;

    public static etardengiFragement newInstance() {
        etardengiFragement fragment = new etardengiFragement();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dengi, container, false);
        intiview();
        return view;
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
//            if (bodyList != null) {
//                bodyList.clear();
//            }
//        } catch (Exception e) {
//        }
//        List<Body> list = bodyList;
//        Body body = new Body("", "xxx xxxx xxxxx", "99 9999 9999", "", "0");
//        list.add(body);
//        List<Body> list2 = bodyList;
//        Body body2 = new Body("", "xxx xxxx xxxxx", "99 9999 9999", "", "0");
//        list2.add(body2);
//        List<Body> list3 = bodyList;
//        Body body3 = new Body("", "xxx xxxx xxxxx", "99 9999 9999", "", "0");
//        list3.add(body3);
//        List<Body> list4 = bodyList;
//        Body body4 = new Body("", "xxx xxxx xxxxx", "99 9999 9999", "", "0");
//        list4.add(body4);
//        List<Body> list5 = bodyList;
//        Body body5 = new Body("", "xxx xxxx xxxxx", "99 9999 9999", "", "0");
//        list5.add(body5);
//        List<Body> list6 = bodyList;
//        Body body6 = new Body("", "xxx xxxx xxxxx", "99 9999 9999", "", "0");
//        list6.add(body6);
//        List<Body> list7 = bodyList;
//        Body body7 = new Body("", "xxx xxxx xxxxx", "99 9999 9999", "", "0");
//        list7.add(body7);
//        List<Body> list8 = bodyList;
//        Body body8 = new Body("", "xxx xxxx xxxxx", "99 9999 9999", "", "0");
//        list8.add(body8);
//        Collections.sort(bodyList, new Comparator<Body>() {
//            public int compare(Body body, Body t1) {
//                return t1.amount.compareToIgnoreCase(body.amount);
//            }
//        });
//        int total = 0;
//        for (int i = 0; i < bodyList.size(); i++) {
//            if (!((Body) bodyList.get(i)).amount.isEmpty()) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("setData: ");
//                sb.append(((Body) bodyList.get(i)).name);
//                Log.d("TAG", sb.toString());
//                total += Integer.parseInt(((Body) bodyList.get(i)).amount);
//            }
//        }
//        ((TextView) view.findViewById(R.id.tv_total)).setText(String.valueOf(total));
//        adp_varishta = new KaryakarniAdapter(bodyList, getActivity());
//        rcvarishta.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
//        rcvarishta.setItemAnimator(new DefaultItemAnimator());
//        rcvarishta.setAdapter(adp_varishta);
        if (CommonObject.isNetworkConnected(getActivity())) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("dtype", "[tr de`gIdata");
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
        ((TextView) view.findViewById(R.id.tv_total)).setText(getResources().getString(R.string.Rs)+ NumberToCurrencyExamples.getAmount(Double.valueOf(total)));
        adp_varishta = new KaryakarniAdapter(users, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adp_varishta);
    }
}
