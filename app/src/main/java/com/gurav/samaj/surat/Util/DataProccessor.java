package com.gurav.samaj.surat.Util;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class DataProccessor {
    public static final String PREFS_NAME = "appname_prefs";
    private static Context context;

    public DataProccessor(Context context2) {
        context = context2;
    }

    public static void setInt(String key, int value) {
        Editor editor = context.getSharedPreferences(PREFS_NAME, 0).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        return context.getSharedPreferences(PREFS_NAME, 0).getInt(key, 0);
    }

    public static void setStr(String key, String value) {
        Editor editor = context.getSharedPreferences(PREFS_NAME, 0).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        return context.getSharedPreferences(PREFS_NAME, 0).getString(key, "DNF");
    }

    public static void setBool(String key, boolean value) {
        Editor editor = context.getSharedPreferences(PREFS_NAME, 0).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBool(String key) {
        return context.getSharedPreferences(PREFS_NAME, 0).getBoolean(key, false);
    }
}
