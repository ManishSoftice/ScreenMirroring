package com.example.softice.utils;



import static com.example.softice.activity.AppSettingActivity.sharedPreferencesHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Base64;

import java.security.MessageDigest;

public class AdUtils {

    public static AdLoading adLoading = null;
    public static AdProgress adProgress = null;

    public static void PlayStore(Context context, String url) {
        try {
            if (url.startsWith("https://play.google.com/store/apps/details?id=")) {
                String remove = url.replace("https://play.google.com/store/apps/details?id=", "");
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + remove)));
            } else if (url.startsWith("http://play.google.com/store/apps/details?id=")) {
                String remove = url.replace("http://play.google.com/store/apps/details?id=", "");
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + remove)));
            } else {
                Intent packageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                packageIntent.setPackage("com.android.chrome");
                PackageManager packageManager = context.getPackageManager();
                if (packageIntent.resolveActivity(packageManager) != null)
                    context.startActivity(packageIntent);
                else
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager manager =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    @SuppressLint("PackageManagerGetSignatures")
    public static String getKeyHash(Activity activity, String libraryPackageName) {
        PackageInfo info;
        try {
            info = activity.getPackageManager().getPackageInfo(libraryPackageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = (Base64.encodeToString(md.digest(), Base64.NO_WRAP));
                return something.replace("+", "*");
            }
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();

        } catch (Exception ignored) {


        }
        return null;
    }


    public static void showAdLoading(Context context) {
        try {
            if (adLoading != null && adLoading.isShowing())
                adLoading.dismiss();

            if (adLoading == null)
                adLoading = new AdLoading(context);

            adLoading.setCancelable(false);
            adLoading.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void dismissAdLoading() {
        try {
            if (adLoading != null && adLoading.isShowing())
                adLoading.dismiss();
            adLoading = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showAdProgress(Context context) {
        try {
            if (adProgress != null && adProgress.isShowing())
                adProgress.dismiss();

            if (adProgress == null)
                adProgress = new AdProgress(context);

            adProgress.setCancelable(false);
            adProgress.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void dismissAdProgress() {
        if (adProgress != null && adProgress.isShowing())
            adProgress.dismiss();
        adProgress = null;
    }

    public static boolean checkUpdate(int version) {
        if (sharedPreferencesHelper.getVersionUpdateDialog()) {
            return (float) version < Float.parseFloat(sharedPreferencesHelper.getVersionCode());
        }

        return false;
    }

    public static int getCurrentVersionCode(Activity activity) {
        PackageManager manager = activity.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(
                    activity.getPackageName(), 0);
            return info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public enum NativeType {
        NATIVE_BANNER,
        NATIVE_MEDIUM,
        NATIVE_BIG
    }

    public enum ClickType {
        MAIN_CLICK,
        BACK_CLICK,
        EVERY_CLICK

    }
}
