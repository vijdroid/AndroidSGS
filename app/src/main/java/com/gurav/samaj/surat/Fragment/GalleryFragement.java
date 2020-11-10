package com.gurav.samaj.surat.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gurav.samaj.surat.MainActivity;
import com.gurav.samaj.surat.Model.GalleryData;
import com.gurav.samaj.surat.Model.GalleryModel;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.Util.TouchImageView;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GalleryFragement extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RelativeLayout mRelativeLayout;
    RecyclerView recyclerView;
    View view;
    ProgressBar progressBar;
    LinearLayout llnonet;
    TextView errInfo;
    SwipeRefreshLayout swipeRefreshLayout;


    public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
        private Context mContext;
        List<GalleryData> galleryData;
        private Random mRandom = new Random();


        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView mTextView;
            public TextView title;
            public TextView desc;
            public ViewHolder(View v) {
                super(v);
                mTextView =  v.findViewById(R.id.tv);


                title=v.findViewById(R.id.nws_title);
                desc=v.findViewById(R.id.nws_desc);

                title.setVisibility(View.GONE);
                desc.setVisibility(View.GONE);
            }
        }

        public ColorAdapter(Context context, List<GalleryData> galleryData) {
            this.galleryData = galleryData;
            mContext = context;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.custom_view, parent, false));
        }

        public void onBindViewHolder(ViewHolder holder, final int position) {
            Glide.with(getActivity())
                    .load(CommonObject.BASE_UPLOAD+ galleryData.get(position).event_mage)
                    .into(holder.mTextView);

            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showFullImg(CommonObject.BASE_UPLOAD + galleryData.get(position).event_mage);
                }
            });
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

    private void showFullImg(String s) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
            View imgEntryView = inflater.inflate(R.layout.dialog_userhearder, null);
        final Dialog dialog=new Dialog(getActivity(),android.R.style.Theme_Black_NoTitleBar_Fullscreen); //default fullscreen titlebar
        ImageView img =  imgEntryView
                .findViewById(R.id.usericon_large);

        Glide.with(getActivity()).load(s).into(img);

        dialog.setContentView(imgEntryView);
        dialog.show();




    }

    public static GalleryFragement newInstance() {
        GalleryFragement fragment = new GalleryFragement();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
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
            networkHelper.sendRequest("/reader/getGallery.php", headers, new NetworkCallback() {
                @Override
                public void onNetworkResult(String response) {
                    //TODO : perform on success

//                String masterData;
//                masterData = "{\"products\":" + response + "}";
//                Log.d("TAG", "Response:: " + masterData);
                    Log.d("TAG", "Response:: " + response);
                    Gson gson = new Gson();
                    JSONArray arr = null;


//
                    CommonObject.galleryModel = gson.fromJson(response, GalleryModel.class);
//
                    if (CommonObject.galleryModel.Gallery.size() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        setData(CommonObject.galleryModel.Gallery);
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
                // Your code here
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
        mLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    public void setData(List<GalleryData> galleryData) {
        mAdapter = new ColorAdapter(getActivity(), galleryData);
        recyclerView.setAdapter(mAdapter);
    }
}
