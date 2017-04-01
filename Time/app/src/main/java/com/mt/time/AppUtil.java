package com.mt.time;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * Created by yoush on 2017/3/16.
 */

public class AppUtil {

    public static String getApplicationNameByPackageName(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        String Name;
        try {
            Name = pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString();
        } catch (PackageManager.NameNotFoundException e) {
            Name = "";
        }
        return Name;
    }

    public static Drawable getApplicationIconByPackageName(Context context, String packageName){
        PackageManager pm = context.getPackageManager();
        Drawable icon;
        try {
            icon = pm.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            icon = null;
        }
        return icon;
    }


}
