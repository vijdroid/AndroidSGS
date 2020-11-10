package com.gurav.samaj.surat.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gurav.samaj.surat.Model.ArticlesData;
import com.gurav.samaj.surat.Model.GalleryData;
import com.gurav.samaj.surat.Model.GalleryModel;
import com.gurav.samaj.surat.Model.LiveNewsModel;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.ApiService;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.RetroClient;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.datetimeutils.DateTimeStyle;
import com.gurav.samaj.surat.datetimeutils.DateTimeUtils;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import org.json.JSONArray;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RelativeLayout mRelativeLayout;
    RecyclerView recyclerView;
    View view;
    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initview();
        getData();
        return view;
    }

    private void getData() {


        if (CommonObject.isNetworkConnected(getActivity())) {
            llnonet.setVisibility(View.GONE);


            progressBar.setVisibility(View.VISIBLE);
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("token", "4df05645gh6456565h546fg65bg6545");
            NetworkHelper networkHelper = new NetworkHelper(getActivity(), SGSApplicaiton.BASEURL);
            networkHelper.sendRequest("/reader/getNews.php", headers, new NetworkCallback() {
//            NetworkHelper networkHelper = new NetworkHelper(getActivity(), "https://newsapi.org/v2/everything?");
//            networkHelper.sendRequest("q=maharashtra&apiKey=2e000705017549609b1aa9df173a271e&language=hi", headers, new NetworkCallback() {
                @Override
                public void onNetworkResult(String response) {
                    //TODO : perform on success

//                String masterData;
//                masterData = "{\"products\":" + response + "}";
                    Gson gson = new Gson();
                    JSONArray arr = null;


                    Log.d("TAG", "onNetworkResult: " + response);

////
                    CommonObject.galleryModel = gson.fromJson(response, GalleryModel.class);
                    Log.d("TAG", "onNetworkResult: "+CommonObject.galleryModel.News.size());
//
                    if (CommonObject.galleryModel.News.size() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        setData(CommonObject.galleryModel.News);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        errInfo.setText("No Data Found");
                        llnonet.setVisibility(View.VISIBLE);
                    }


//                    CommonObject.liveNewsModel = gson.fromJson(response, LiveNewsModel.class);
//                    recyclerView.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.GONE);
//                    setData(CommonObject.liveNewsModel.articles);

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
/*
        ApiService api = RetroClient.getApiService();
        Call<ResponseBody> call = api.getContacts();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Log.d("TAG", "onResponse: "+response.body().source().readUtf8().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/
    }

    private void initview() {

        recyclerView = view.findViewById(R.id.rv_body);
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
                // Your code heres
                // To keep animation for 4 seconds
                getData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }

        });
//        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

    }


    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private Context mContext;
        List<GalleryData> galleryData;
        private Random mRandom = new Random();

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView mTextView;
            public TextView title;
            public TextView desc;
            public TextView nwsdate;

            public ViewHolder(View v) {
                super(v);
                mTextView = (ImageView) v.findViewById(R.id.tv);

                title = v.findViewById(R.id.nws_title);
                desc = v.findViewById(R.id.nws_desc);
                nwsdate = v.findViewById(R.id.nws_date);
            }
        }

        public NewsAdapter(Context context, List<GalleryData> galleryData) {
            this.galleryData = galleryData;
            mContext = context;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_view, parent, false));
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setVisibility(View.VISIBLE);
            Glide.with(getActivity())
                    .load(CommonObject.BASE_UPLOAD +galleryData.get(position).news_image)
                    .into(holder.mTextView);
//            if(galleryData.get(position).articles.get(position).urlToImage!=null){
//                holder.mTextView.setVisibility(View.VISIBLE);
//                Glide.with(getActivity())
//                        .load("https://vijdroidinfotech.info/SGS/uploads/" + galleryData.get(position).news_image)
//                        .into(holder.mTextView);
//            }else {
//                holder.mTextView.setVisibility(View.GONE);
//            }


            holder.title.setText(galleryData.get(position).nws_title);
            holder.desc.setText(galleryData.get(position).nws_desc);
            holder.nwsdate.setText(DateTimeUtils.formatWithStyle(galleryData.get(position).update_date, DateTimeStyle.MEDIUM));

        }

        public int getItemCount() {
            return galleryData.size();
        }

        /* access modifiers changed from: protected */
        public int getRandomIntInRange(int max, int min) {
            return mRandom.nextInt((max - min) + min) + min;
        }

        /* access modifiers changed from: protected */
        public int getRandomHSVColor() {
            return Color.HSVToColor(255, new float[]{(float) mRandom.nextInt(361), 1.0f, 1.0f});
        }
    }

    public void setData(List<GalleryData> data) {
        mAdapter = new NewsAdapter(getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

}
