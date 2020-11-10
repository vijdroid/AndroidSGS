package com.gurav.samaj.surat.Util;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gurav.samaj.surat.R;

public class FullScreenDialog extends DialogFragment implements View.OnClickListener {
    RequestOptions requestOptions = new RequestOptions();
    public static final String TAG = "FullScreenDialog";
    ImageView imageView, close;
    TextView tvtitle, tvdes, tvprice;

    public FullScreenDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
        requestOptions.placeholder(R.drawable.logo);
    }

    public static FullScreenDialog newInstance(String img_url, String name, String description, String price) {
        FullScreenDialog frag = new FullScreenDialog();
        Bundle args = new Bundle();
        args.putString("url", img_url);
        args.putString("name", name);
        args.putString("description", description);
        args.putString("price", price);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color
                    .TRANSPARENT));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, parent, false);
        imageView = view.findViewById(R.id.iv_full_src_image);
        tvtitle = view.findViewById(R.id.iv_full_title);
        tvdes = view.findViewById(R.id.iv_full_dec);
        tvprice = view.findViewById(R.id.iv_full_price);
        close = view.findViewById(R.id.back);


        String murl = getArguments().getString("url", "");
        String title = getArguments().getString("name", "");
        String description = getArguments().getString("description", "");
        String price = getArguments().getString("price", "");

        tvtitle.setText(title);
        tvdes.setText(description);
        tvprice.setText(price);


        Glide.with(getActivity())
                .applyDefaultRequestOptions(requestOptions)
                .load(murl)
                .into(imageView);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                dismiss();
                break;
        }
    }
}