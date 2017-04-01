package com.mt.time.db;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MaoTong on 2017/3/13.
 */

public class TimeUtil {
    private static final String TAG = "TimeUtil";

    public static String currentTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }

    public static String durationTime(Date startDate, Date endDate) {
        long duration = endDate.getTime() - startDate.getTime();

        long sec = duration / (1000) % 60;
        long min = (duration - sec * 1000) / (60 * 1000);

        long hour = (duration - min * 60 * 1000) / (60 * 1000 * 24 ) ;
        Log.e(TAG, "durationTime: " + duration + "  " + hour + "    " + min + " " + sec + " " + new SimpleDateFormat("HH:mm:ss").format(new Date(0, 0, 0, (int) hour, (int) min, (int) sec)));
        return new SimpleDateFormat("HH:mm:ss").format(new Date(0, 0, 0, (int) hour, (int) min, (int) sec));
    }
}
