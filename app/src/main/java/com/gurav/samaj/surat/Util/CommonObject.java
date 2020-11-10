package com.gurav.samaj.surat.Util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gurav.samaj.surat.Model.CraditDengiModel;
import com.gurav.samaj.surat.Model.GalleryModel;
import com.gurav.samaj.surat.Model.KhataVahi;
import com.gurav.samaj.surat.Model.LiveNewsModel;
import com.gurav.samaj.surat.Model.LonderDetailModel;
import com.gurav.samaj.surat.Model.MiniStatmentModel;
import com.gurav.samaj.surat.Model.UserModel;

import java.util.List;

public class CommonObject {
    public static String vToekn = "0000";
    public static ProgressDialog progress;
    public static String min_amt;
    public static String max_amt;
    public static String sort_by;
    public static String order;
    public static UserModel userModel;
    public static KhataVahi khataVahi;
    public static GalleryModel galleryModel;
    public static MiniStatmentModel miniStatmentModel;
    public static CraditDengiModel craditDengiModel;
    public static LonderDetailModel londerDetailModel;
    public static LiveNewsModel liveNewsModel;
    public static String BASE_PROFILE="https://vijdroidinfotech.info/SGS/uploads/profile/";
    public static String BASE_UPLOAD="https://vijdroidinfotech.info/SGS/uploads/";

    public static Boolean isActivityRunning(Class activityClass, Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (activityClass.getCanonicalName().equalsIgnoreCase(task.baseActivity.getClassName()))
                return true;
        }
        return false;
    }

    public static void showMsg(String title, String message, Activity activity) {
        progress = new ProgressDialog(activity);
        progress.setTitle(title);
        progress.setMessage(message);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

    public static boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
