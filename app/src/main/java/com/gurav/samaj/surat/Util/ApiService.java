package com.gurav.samaj.surat.Util;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @GET("q=maharashtra&apiKey=2e000705017549609b1aa9df173a271e&language=hi")
    Call<ResponseBody> getContacts();
}