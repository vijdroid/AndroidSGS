package com.gurav.samaj.surat.Util;

import android.app.Application;

public class SGSApplicaiton extends Application {
public static String BASEURL="https://vijdroidinfotech.info/SGS";
    @Override
    public void onCreate() {
        super.onCreate();

        DataProccessor dataProccessor=new DataProccessor(this);
    }
}
