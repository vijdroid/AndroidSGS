package com.gurav.samaj.surat.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.gurav.samaj.surat.Model.UserModel;
import com.gurav.samaj.surat.R;
import com.gurav.samaj.surat.Util.CommonObject;
import com.gurav.samaj.surat.Util.DataProccessor;
import com.gurav.samaj.surat.Util.SGSApplicaiton;
import com.gurav.samaj.surat.networking.NetworkCallback;
import com.gurav.samaj.surat.networking.NetworkHelper;

import java.util.HashMap;

import static com.gurav.samaj.surat.Activitys.HomeActivity.name_txt;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar progressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passwordEditText.getText().toString().isEmpty()) {
                    passwordEditText.setError("Please enter password");
                    return;
                } else {

                    if (CommonObject.isNetworkConnected(LoginActivity.this)) {


                        progressBar.setVisibility(View.VISIBLE);
                        HashMap headers = new HashMap();
                        // headers.put("orderby", "");
                        //headers.put("order", "");
                        //headers.put("maxnum", "5");


                        String android_id = Settings.Secure.getString(LoginActivity.this.getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                        Log.d("TAG", "onClick: "+android_id);

                        headers.put("password", passwordEditText.getText().toString());
                        headers.put("uuid", android_id);
                        NetworkHelper networkHelper = new NetworkHelper(LoginActivity.this, SGSApplicaiton.BASEURL);
                        networkHelper.sendRequest("/reader/getAuthUser.php", headers, new NetworkCallback() {
                            @Override
                            public void onNetworkResult(String response) {
                                Log.d("TAG", "Response:: " + response);
                                Gson gson = new Gson();
                                progressBar.setVisibility(View.GONE);

                                CommonObject.userModel = gson.fromJson(response, UserModel.class);


                                try {
                                    if (CommonObject.userModel.status.equals("success")) {

                                        name_txt.setText("nmSte " + CommonObject.userModel.users.get(0).name);
                                        DataProccessor.setStr("uname", name_txt.getText().toString());

                                        Toast.makeText(LoginActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, KhataVahi_detail_Activity.class));
                                        finish();

                                    } else {

                                        if (CommonObject.userModel.message.equals("Incorrect password")) {
                                            passwordEditText.setError("Password is Incorrect");
                                            Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                                        } else if (CommonObject.userModel.message.equals("try from other device")) {
                                            Toast.makeText(LoginActivity.this, CommonObject.userModel.message, Toast.LENGTH_SHORT).show();
                                        }
                                        else if (CommonObject.userModel.message.equals("your block by admin.")) {
                                            Toast.makeText(LoginActivity.this, CommonObject.userModel.message, Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(LoginActivity.this, "Fail Try Again", Toast.LENGTH_SHORT).show();
                                }


                                //  Log.d("TAG", "DAta:: " + CommonObject.producModel.products.get(1).description);
                            }

                            @Override
                            public void onNetworkError() {
                                //TODO : perform on error
                                Log.d("TAG", "Error:: ");
                                // CommonObject.progress.dismiss();

                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Fail To Login", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }, LoginActivity.this);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "No Internet Connection.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
