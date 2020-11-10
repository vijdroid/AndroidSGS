package com.gurav.samaj.surat.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.gurav.samaj.surat.Activitys.KaryKarniActivity;
import com.gurav.samaj.surat.Activitys.KhataVahi_detail_Activity;
import com.gurav.samaj.surat.Activitys.LoginActivity;
import com.gurav.samaj.surat.Activitys.SabsadActivity;
import com.gurav.samaj.surat.Activitys.SarvKaryaActivity;
import com.gurav.samaj.surat.Activitys.Zon_Activity;
import com.gurav.samaj.surat.R;

public class SGSFragement extends Fragment implements OnClickListener {
    LinearLayout ll_karkarni;
    LinearLayout ll_sabasad;
    LinearLayout ll_sarv_karya;
    LinearLayout ll_zon;
    LinearLayout ll_login;
    CardView  ccKhataWahi;
    View view;

    public static SGSFragement newInstance() {
        SGSFragement fragment = new SGSFragement();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sgs, container, false);
        intview();
        return view;
    }

    private void intview() {
        ll_karkarni = (LinearLayout) view.findViewById(R.id.tv_btn_karkarni);
        ll_sabasad = (LinearLayout) view.findViewById(R.id.tv_btn_sabasad);
        ll_zon = (LinearLayout) view.findViewById(R.id.tv_btn_zon);
        ll_sarv_karya = (LinearLayout) view.findViewById(R.id.tv_btn_sarv_karya);
        ll_login = (LinearLayout) view.findViewById(R.id.tv_btn_login);
        ccKhataWahi=view.findViewById(R.id.cv_khatawahi);
        ll_karkarni.setOnClickListener(this);
        ll_sabasad.setOnClickListener(this);
        ll_zon.setOnClickListener(this);
        ll_sarv_karya.setOnClickListener(this);
//        ll_login.setOnClickListener(this);
        ccKhataWahi.setOnClickListener(this);
    }

    public void onClick(View view2) {
        switch (view2.getId()) {
            case R.id.tv_btn_karkarni /*2131362052*/:
                startActivity(new Intent(getActivity(), KaryKarniActivity.class));
                return;
            case R.id.tv_btn_sabasad /*2131362054*/:
                startActivity(new Intent(getActivity(), SabsadActivity.class));
                return;
            case R.id.tv_btn_sarv_karya /*2131362055*/:
                startActivity(new Intent(getActivity(), SarvKaryaActivity.class));
                return;
            case R.id.tv_btn_zon /*2131362056*/:
                startActivity(new Intent(getActivity(), Zon_Activity.class));
                return;
            case R.id.cv_khatawahi:
//                startActivity(new Intent(getActivity(), KhataVahi_detail_Activity.class));
                break;
            case R.id.tv_btn_login:
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(i, 101);
                break;
            default:
                return;
        }
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == 101) {
//            if(resultCode == Activity.RESULT_OK){
//                String result=data.getStringExtra("result");
//                if(result.equals("done"))
//                {
//                    ccKhataWahi.setVisibility(View.VISIBLE);
//                    ll_login.setVisibility(View.GONE);
//                }
//            }
//            if (resultCode == Activity.RESULT_CANCELED) {
//                //Write your code if there's no result
//                ll_login.setVisibility(View.VISIBLE);
//                ccKhataWahi.setVisibility(View.GONE);
//                Toast.makeText(getActivity(), "Fail To Login", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }//onActivityResult

    @Override
    public void onResume() {
        super.onResume();

    }
}
