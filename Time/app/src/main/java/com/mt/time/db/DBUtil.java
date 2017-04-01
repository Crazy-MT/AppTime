package com.mt.time.db;

import com.litesuits.orm.db.model.ConflictAlgorithm;
import com.mt.time.App;

import java.util.Date;
import java.util.List;

/**
 * Created by yoush on 2017/3/13.
 */

public class DBUtil {

    private static final String TAG = "DBUtil";


    static {
        if (findAll().size() == 0) {
            App.liteOrm.insert(new AppModel("place holder", "", "", TimeUtil.currentTime()), ConflictAlgorithm.Abort);
        }
    }

    public static void insertApp(String appName, String className , String packageName,String startTime) {

        AppModel appModel = findApp();
        if (appModel.getAppName().equals(appName))
            return;

        AppModel mAppModel = new AppModel();

        mAppModel.setAppName(appName);
        mAppModel.setClassName(className);
        mAppModel.setPackageName(packageName);
        mAppModel.setStartTime(startTime);

        App.liteOrm.insert(mAppModel, ConflictAlgorithm.Abort);
    }


    public static void updateLastApp(String appName) {
        AppModel appModel = findApp();
        appModel.setEndTime(TimeUtil.currentTime());
        appModel.setDuration(TimeUtil.durationTime(new Date(appModel.getStartTime()) , new Date(appModel.getEndTime())));
        App.liteOrm.update(appModel);
        //Log.e(TAG, "updateLastApp: " );
    }

    public static AppModel findApp() {
        List<AppModel> list = App.liteOrm.query(AppModel.class);
        //OrmLog.i(TAG, list.get(0).getAppName() + "    " + list.get(0).getStartTime());

        for (int i = 0; i < list.size(); i++) {
            //Log.e(TAG, "findApp: " + list.get(i).getAppName() + "    " + list.get(i).getStartTime() + "        " + list.get(i).getEndTime());
        }


        return list.get(list.size() - 1);
    }

    public static List<AppModel> findAll() {
        return App.liteOrm.query(AppModel.class);
    }

}
