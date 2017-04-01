package com.mt.time;

import android.app.Application;

import com.litesuits.orm.LiteOrm;

/**
 * Created by yoush on 2017/3/13.
 */

public class App extends Application {
    public static LiteOrm liteOrm;

    @Override
    public void onCreate() {
        super.onCreate();

        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "liteorm.db");
        }
        liteOrm.setDebugged(true); // open the log
    }
}
