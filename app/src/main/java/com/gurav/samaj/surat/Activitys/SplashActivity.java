package com.gurav.samaj.surat.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.DataProccessor;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public static int SPLASH_TIME_OUT = 500;
    ImageView imageView;
    TextView textView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash);
        getSupportActionBar().hide();
        this.imageView = (ImageView) findViewById(R.id.iv_app_logo);
        this.textView = (TextView) findViewById(R.id.tc_power);





        if (!DataProccessor.getBool("istoken")) {
            if (CommonObject.isNetworkConnected(this)) {

                FirebaseInstanceId.getInstance().getInstanceId()

                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.d("TAG", "getInstanceId failed", task.getException());
                                    goHome();
                                    return;
                                }

                                // Get new Instance ID token
                                String token = task.getResult().getToken();
                                // Log and toast
                                    DataProccessor.setBool("istoken",true);
                                    Log.d("TAG", "toekn " + token);
                                    getData(token);
                            }
                        });
            } else {
                goHome();
                Toast.makeText(this, "No Interet connection", Toast.LENGTH_SHORT).show();
            }
        } else {
            goHome();
        }


    }

    private void getData(String token) {
        if (CommonObject.isNetworkConnected(this)) {
            HashMap headers = new HashMap();
            // headers.put("orderby", "");
            //headers.put("order", "");
            //headers.put("maxnum", "5");
            headers.put("token", token);
            NetworkHelper networkHelper = new NetworkHelper(this, SGSApplicaiton.BASEURL);
            networkHelper.sendRequest("/insert_data/update_token.php", headers, new NetworkCallback() {
                @Override
                public void onNetworkResult(String response) {
                    Log.d("TAG", "onNetworkResult: " + response);
                    goHome();

                }

                @Override
                public void onNetworkError() {
                    //TODO : perform on error
                    Log.d("TAG", "Error:: ");
                    // CommonObject.progress.dismiss();
                    goHome();
                }
            }, this);
        } else {
            goHome();
            Toast.makeText(this, "No Interet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void goHome() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setStartTime(1000);
        scaleAnimation.setFillAfter(true);
        this.imageView.startAnimation(scaleAnimation);
        scaleAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        finish();
                    }
                }, (long) SplashActivity.SPLASH_TIME_OUT);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });



    }
}
