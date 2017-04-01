package com.mt.time;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telecom.CallScreeningService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.mt.time.db.DBUtil;
import com.mt.time.db.TimeUtil;

import static com.mt.time.AppUtil.getApplicationNameByPackageName;


/**
 * @author Eicky
 * @Description:
 * @date: 2017/1/10 15:50
 * @version: V1.0
 */
public class TrackerService extends AccessibilityService {
    public static final String TYPE_KEY = "type_key";
    private FloatWindowUtils mFloatWindowUtils;
    private final String TAG = "Eicky";
    private int type;

    public enum Type {
        OPEN(1), CLOSE(0);

        public int code;

        Type(int code) {
            this.code = code;
        }
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mFloatWindowUtils == null)
            mFloatWindowUtils = new FloatWindowUtils(getApplicationContext());
        type = intent.getIntExtra(TYPE_KEY, -1);
        if (type != -1) {
            if (type == Type.OPEN.code) {
                mFloatWindowUtils.addFloatView();
            } else if (type == Type.CLOSE.code) {
                mFloatWindowUtils.removeFloatView();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {

            String packageNameStr = event.getPackageName().toString();
            String classNameStr = event.getClassName().toString();
            String appNameStr = getApplicationNameByPackageName(this , packageNameStr);
            Log.i(TAG, classNameStr);
            if (classNameStr.startsWith(packageNameStr)) {
                classNameStr = classNameStr.substring(packageNameStr.length());
            }

            DBUtil.updateLastApp(appNameStr);
            DBUtil.insertApp(appNameStr, classNameStr, packageNameStr , TimeUtil.currentTime());
            DBUtil.findApp();

            if (mFloatWindowUtils != null) {
                mFloatWindowUtils.updateDisplay(packageNameStr, classNameStr ,appNameStr);
            }


        }
    }



    @Override
    public void onInterrupt() {

    }
}
