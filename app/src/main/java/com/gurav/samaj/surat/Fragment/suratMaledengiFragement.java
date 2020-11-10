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

public class suratMaledengiFragement extends Fragment {
    KaryakarniAdapter adp_varishta;
    List<Body> bodyList = new ArrayList();
    RecyclerView recyclerView;
    View view;
    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;
    public static suratMaledengiFragement newInstance() {
        suratMaledengiFragement fragment = new suratMaledengiFragement();
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
//        Body body = new Body("", "mhex m6ukr gurv", "9824209449", "", "101000");
//        list.add(body);
//        List<Body> list2 = bodyList;
//        Body body2 = new Body("", "gopal prxram gurv", "9106277569", "", "51000");
//        list2.add(body2);
//        List<Body> list3 = bodyList;
//        Body body3 = new Body("", "muku.Nd suklal gurv", "9427827376", "", "51000");
//        list3.add(body3);
//        List<Body> list4 = bodyList;
//        Body body4 = new Body("", "x.kr goiv.d gurv", "9879154222", "", "51000");
//        list4.add(body4);
//        List<Body> list5 = bodyList;
//        Body body5 = new Body("", "illaka.t prxram gurv", "6352942291", "", "31000");
//        list5.add(body5);
//        List<Body> list6 = bodyList;
//        Body body6 = new Body("", "ivXvna4 ramdas gurv", "7984207082", "", "31000");
//        list6.add(body6);
//        List<Body> list7 = bodyList;
//        Body body7 = new Body("", "Baapurav 7bulal gurv", "9427881056", "", "31000");
//        list7.add(body7);
//        List<Body> list8 = bodyList;
//        Body body8 = new Body("", "nrex gorq gurv", "9428455370", "", "31000");
//        list8.add(body8);
//        List<Body> list9 = bodyList;
//        Body body9 = new Body("", "Xaa.taram gulabrav gurv", "9537172455", "", "31000");
//        list9.add(body9);
//        List<Body> list10 = bodyList;
//        Body body10 = new Body("", "wImrav ramdas gurv", "9924987073", "", "31000");
//        list10.add(body10);
//        List<Body> list11 = bodyList;
//        Body body11 = new Body("", "rvINd/ m2ukr gurv", "9726974559", "", "31000");
//        list11.add(body11);
//        List<Body> list12 = bodyList;
//        Body body12 = new Body("", "motIlal kaxIna4 gurv", "9925756244", "", "31000");
//        list12.add(body12);
//        List<Body> list13 = bodyList;
//        Body body13 = new Body("", "Axok p.DIt gurv", "9904572347", "", "31000");
//        list13.add(body13);
//        List<Body> list14 = bodyList;
//        Body body14 = new Body("", "p/wakr rajaram gurv", "6351583663", "", "31000");
//        list14.add(body14);
//        List<Body> list15 = bodyList;
//        Body body15 = new Body("", "Aru` hirc.Nd/ gurv", "9724191055", "", "31000");
//        list15.add(body15);
//        List<Body> list16 = bodyList;
//        Body body16 = new Body("", "ritlal kaxIna4 gurv", "8866541626", "", "31000");
//        list16.add(body16);
//        List<Body> list17 = bodyList;
//        Body body17 = new Body("", "p/kax ihralal xmaR ", "9998553517", "", "31000");
//        list17.add(body17);
//        List<Body> list18 = bodyList;
//        Body body18 = new Body("", "idnex nanawa] gurv", "9687627290", "", "31000");
//        list18.add(body18);
//        List<Body> list19 = bodyList;
//        Body body19 = new Body("", "rajex wIkn gurv", "", "", "31000");
//        list19.add(body19);
//        List<Body> list20 = bodyList;
//        Body body20 = new Body("", "idlIp m2ukr gurv", "9898841688", "", "21000");
//        list20.add(body20);
//        List<Body> list21 = bodyList;
//        Body body21 = new Body("", "c.du rmex gurv", "8469399072", "", "21000");
//        list21.add(body21);
//        List<Body> list22 = bodyList;
//        Body body22 = new Body("", ")aneXvr gorq gurv", "6355797675", "", "21000");
//        list22.add(body22);
//        List<Body> list23 = bodyList;
//        Body body23 = new Body("", "Ainl w3u gurv", "9714982427", "", "21000");
//        list23.add(body23);
//        List<Body> list24 = bodyList;
//        Body body24 = new Body("", "kElax muril2r gurv", "9723357413", "", "21000");
//        list24.add(body24);
//        List<Body> list25 = bodyList;
//        Body body25 = new Body("", "ikr` bbn gurv", "9879173303", "", "21000");
//        list25.add(body25);
//        List<Body> list26 = bodyList;
//        Body body26 = new Body("", "Aan.drav m2ukr gurv", "8140817343", "", "21000");
//        list26.add(body26);
//        List<Body> list27 = bodyList;
//        Body body27 = new Body("", "goku; Aoka.r gurv", "7016643201", "", "21000");
//        list27.add(body27);
//        List<Body> list28 = bodyList;
//        Body body28 = new Body("", "suklal kaxIna4 gurv", "8320834121", "", "21000");
//        list28.add(body28);
//        List<Body> list29 = bodyList;
//        Body body29 = new Body("", "walcNd/ barku gurv", "9106611347", "", "21000");
//        list29.add(body29);
//        List<Body> list30 = bodyList;
//        Body body30 = new Body("", "wupeNd/ rmex gurv", "9898887648", "", "21000");
//        list30.add(body30);
//        List<Body> list31 = bodyList;
//        Body body31 = new Body("", "gopal mgn gurv", "9099742948", "", "21000");
//        list31.add(body31);
//        List<Body> list32 = bodyList;
//        Body body32 = new Body("", "mnoj n$4u gurv", "", "", "21000");
//        list32.add(body32);
//        List<Body> list33 = bodyList;
//        Body body33 = new Body("", "p/xa.t Axok gurv", "9638058047", "", "21000");
//        list33.add(body33);
//        List<Body> list34 = bodyList;
//        Body body34 = new Body("", "ivxal inlk.# gurv", "9537336625", "", "21000");
//        list34.add(body34);
//        List<Body> list35 = bodyList;
//        Body body35 = new Body("", "ivjy xa.taram gurv", "9824878113", "", "21000");
//        list35.add(body35);
//        List<Body> list36 = bodyList;
//        Body body36 = new Body("", "ANkex bapurav gurv", "9537190559", "", "21000");
//        list36.add(body36);
//        List<Body> list37 = bodyList;
//        Body body37 = new Body("", "rivNd/ ixvajI gurv", "9879236393", "", "21000");
//        list37.add(body37);
//        List<Body> list38 = bodyList;
//        Body body38 = new Body("", "suinl kaxIna4 gurv", "9898412412", "", "21000");
//        list38.add(body38);
//        List<Body> list39 = bodyList;
//        Body body39 = new Body("", "kayR-kRta ce nav", "9724437823", "", "21000");
//        list39.add(body39);
//        List<Body> list40 = bodyList;
//        Body body40 = new Body("", "iwqa ]Ttm gurv ", "9898440041", "", "21000");
//        list40.add(body40);
//        List<Body> list41 = bodyList;
//        Body body41 = new Body("", "rivNd/ prxram gurv", "8128005335", "", "21000");
//        list41.add(body41);
//        List<Body> list42 = bodyList;
//        Body body42 = new Body("", "kIxor gulab gurv", "9662619078", "", "21000");
//        list42.add(body42);
//        List<Body> list43 = bodyList;
//        Body body43 = new Body("", "ivjy inlk.# gurv", "9909777496", "", "21000");
//        list43.add(body43);
//        List<Body> list44 = bodyList;
//        Body body44 = new Body("", "Axok p/Hlad gurv", "9558785739", "", "21000");
//        list44.add(body44);
//        List<Body> list45 = bodyList;
//        Body body45 = new Body("", "gulab ramdas gurv", "9265016449", "", "21000");
//        list45.add(body45);
//        List<Body> list46 = bodyList;
//        Body body46 = new Body("", "Gajan.d {Xvrdas gurv", "9824726318", "", "21000");
//        list46.add(body46);
//        List<Body> list47 = bodyList;
//        Body body47 = new Body("", "[aneXvr Axok gurv", "9913232795", "", "21000");
//        list47.add(body47);
//        List<Body> list48 = bodyList;
//        Body body48 = new Body("", "Ganex rmex gurv ", "9978223221", "", "21000");
//        list48.add(body48);
//        List<Body> list49 = bodyList;
//        Body body49 = new Body("", "idnex p/Hlad gurv", "8238748228", "", "21000");
//        list49.add(body49);
//        List<Body> list50 = bodyList;
//        Body body50 = new Body("", "imtex jgdIx gurv ", "9664716195", "", "21000");
//        list50.add(body50);
//        List<Body> list51 = bodyList;
//        Body body51 = new Body("", "mukex devIdas gurv", "9904637071", "", "21000");
//        list51.add(body51);
//        List<Body> list52 = bodyList;
//        Body body52 = new Body("", "rajeNd/ baburav gurv", "7874132555", "", "21000");
//        list52.add(body52);
//        List<Body> list53 = bodyList;
//        Body body53 = new Body("", "sudam naray` gurv ", "", "", "21000");
//        list53.add(body53);
//        List<Body> list54 = bodyList;
//        Body body54 = new Body("", "mukex roihdas gurv", "9173796213", "", "21000");
//        list54.add(body54);
//        List<Body> list55 = bodyList;
//        Body body55 = new Body("", "p/xa.t m2ukr gurv", "", "", "21000");
//        list55.add(body55);
//        List<Body> list56 = bodyList;
//        Body body56 = new Body("", "prex n.dlal gurv", "8347855602", "", "21000");
//        list56.add(body56);
//        List<Body> list57 = bodyList;
//        Body body57 = new Body("", "mheNd/ sudam gurv", "9913751555", "", "21000");
//        list57.add(body57);
//        List<Body> list58 = bodyList;
//        Body body58 = new Body("", "devIdas goiv.d gurv", "9377042393", "", "11000");
//        list58.add(body58);
//        List<Body> list59 = bodyList;
//        Body body59 = new Body("", "vs.t g`pt gurv", "7990154834", "", "11000");
//        list59.add(body59);
//        List<Body> list60 = bodyList;
//        Body body60 = new Body("", "mnohr sonu devre", "9913315401", "", "11000");
//        list60.add(body60);
//        List<Body> list61 = bodyList;
//        Body body61 = new Body("", "nreNd/ ivXvna4 gurv", "9979861797", "", "11000");
//        list61.add(body61);
//        List<Body> list62 = bodyList;
//        Body body62 = new Body("", "mnoj gopal gurv", "9924562316", "", "11000");
//        list62.add(body62);
//        List<Body> list63 = bodyList;
//        Body body63 = new Body("", "rahul illaka.t gurv", "8160779151", "", "11000");
//        list63.add(body63);
//        List<Body> list64 = bodyList;
//        Body body64 = new Body("", "AnIl p/wakr gurv", "9825458849", "", "11000");
//        list64.add(body64);
//        List<Body> list65 = bodyList;
//        Body body65 = new Body("", "sunIl xa.taram gurv", "8866667956", "", "11000");
//        list65.add(body65);
//        List<Body> list66 = bodyList;
//        Body body66 = new Body("", "p/Xaa.t nrex gurv", "", "", "11000");
//        list66.add(body66);
//        List<Body> list67 = bodyList;
//        Body body67 = new Body("", "Axok ihralal gurv", "9427187643", "", "");
//        list67.add(body67);
//        List<Body> list68 = bodyList;
//        Body body68 = new Body("", "dugaRdas hemlal gurv", "", "", "");
//        list68.add(body68);
//        List<Body> list69 = bodyList;
//        Body body69 = new Body("", "mnoj Aan.drav gurv", "", "", "");
//        list69.add(body69);
//        List<Body> list70 = bodyList;
//        Body body70 = new Body("", "rmex iv##l gurv", "", "", "");
//        list70.add(body70);
//        List<Body> list71 = bodyList;
//        Body body71 = new Body("", "ramc.d punmc.d gurv", "9725883988", "", "");
//        list71.add(body71);
//        List<Body> list72 = bodyList;
//        Body body72 = new Body("", "dIpk bIjlal gurv", "", "", "");
//        list72.add(body72);
//        List<Body> list73 = bodyList;
//        Body body73 = new Body("", "Aan.d sudam gurv", "9427647035", "", "");
//        list73.add(body73);
//        List<Body> list74 = bodyList;
//        Body body74 = new Body("", "surex baburav gurv", "", "", "");
//        list74.add(body74);
//        List<Body> list75 = bodyList;
//        Body body75 = new Body("", "l(m` vaiLmk gurv", "", "", "");
//        list75.add(body75);
//        List<Body> list76 = bodyList;
//        Body body76 = new Body("", "3INkl inkm", "", "", "");
//        list76.add(body76);
//        List<Body> list77 = bodyList;
//        Body body77 = new Body("", "Mahex mukuNd cOhan", "  ", "", "");
//        list77.add(body77);
//        List<Body> list78 = bodyList;
//        Body body78 = new Body("", "dxr4 yxv.t gurv", "", "", "");
//        list78.add(body78);
//        List<Body> list79 = bodyList;
//        Body body79 = new Body("", "p/ful wolena4 gurv", "8460251290", "", "");
//        list79.add(body79);
//        List<Body> list80 = bodyList;
//        Body body80 = new Body("", "jIjabrav ritlal gurv", "", "", "");
//        list80.add(body80);

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
            headers.put("dtype", "pu=8 de`gIdata surt");
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

        adp_varishta = new KaryakarniAdapter(users, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adp_varishta);
    }
}
