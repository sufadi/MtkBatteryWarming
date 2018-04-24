### 1. 源码位置
\vendor\mediatek\proprietary\packages\apps\BatteryWarning\

\vendor\mediatek\proprietary\frameworks\opt\batterywarning\

![BatteryWarmning](https://img-blog.csdn.net/20180424111559766?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N1NzQ5NTIw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

### 2. 应用层的 BatteryWarning 核心逻辑
主要根据 type 进行提示框显示


注意 type = (int) (Math.log(type) / Math.log(2));

```
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        String action = intent.getAction();
        Log.d(TAG, "action = " + action);
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            Log.d(TAG, action + " clear battery_warning_settings shared preference");
            SharedPreferences.Editor editor = getSharedPreferences().edit();
            editor.clear();
            editor.apply();
        } else if (ACTION_BATTERY_WARNING.equals(action)) {
            Log.d(TAG, action + " start activity according to shared preference");
            int type = intent.getIntExtra("type", -1);
            Log.d(TAG, "type = " + type);
            type = (int) (Math.log(type) / Math.log(2));
            if (type < 0 || type >= BatteryWarningActivity.sWarningTitle.length) {
                return;
            }
            boolean showDialogFlag = getSharedPreferences().getBoolean(
                    Integer.toString(type), true);
            Log.d(TAG, "type = " + type + "showDialogFlag = " + showDialogFlag);
            if (showDialogFlag) {
                Intent activityIntent = new Intent();
                activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                activityIntent.setClass(mContext, BatteryWarningActivity.class);
                activityIntent.putExtra(BatteryWarningActivity.KEY_TYPE, type);
                mContext.startActivity(activityIntent);
            }
        } else if (ACTION_THERMAL_WARNING.equals(action)) {
            int typeValue = intent.getIntExtra(ThermalWarningActivity.KEY_TYPE, -1);
            Log.d(TAG, "typeValue = " + typeValue);
            if (typeValue == 0 || typeValue == 1) {
                Intent thermalIntent = new Intent();
                thermalIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                thermalIntent.setClass(mContext, ThermalWarningActivity.class);
                thermalIntent.putExtra(ThermalWarningActivity.KEY_TYPE, typeValue);
                mContext.startActivity(thermalIntent);
            }
        }
    }
```

### 3. 模拟数据

触发上述事件

```
package com.example.mtkbatterywarmning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    protected static final String KEY_TYPE = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // msg_charger_over_voltage
    public void msg_charger_over_voltage(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction("mediatek.intent.action.BATTERY_WARNING");
        mIntent.putExtra(KEY_TYPE, 1);
        sendBroadcast(mIntent);
    }

    // msg_battery_over_temperature
    public void msg_battery_over_temperature(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction("mediatek.intent.action.BATTERY_WARNING");
        mIntent.putExtra(KEY_TYPE, 2);
        sendBroadcast(mIntent);
    }

    // msg_over_current_protection
    public void msg_over_current_protection(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction("mediatek.intent.action.BATTERY_WARNING");
        mIntent.putExtra(KEY_TYPE, 4);
        sendBroadcast(mIntent);
    }

    // msg_battery_over_voltage
    public void msg_battery_over_voltage(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction("mediatek.intent.action.BATTERY_WARNING");
        mIntent.putExtra(KEY_TYPE, 10);
        sendBroadcast(mIntent);
    }

    // msg_safety_timer_timeout
    public void msg_safety_timer_timeout(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction("mediatek.intent.action.BATTERY_WARNING");
        mIntent.putExtra(KEY_TYPE, 17);
        sendBroadcast(mIntent);
    }

    // msg_battery_low_temperature
    public void msg_battery_low_temperature(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction("mediatek.intent.action.BATTERY_WARNING");
        mIntent.putExtra(KEY_TYPE, 60);
        sendBroadcast(mIntent);
    }

    // thermal_clear_temperature
    public void thermal_clear_temperature(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction("mediatek.intent.action.THERMAL_DIAG");
        mIntent.putExtra(KEY_TYPE, 0);
        sendBroadcast(mIntent);
    }

    // thermal_over_temperature
    public void thermal_over_temperature(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction("mediatek.intent.action.THERMAL_DIAG");
        mIntent.putExtra(KEY_TYPE, 1);
        sendBroadcast(mIntent);
    }

}

```

### 4. 源码下载

https://github.com/sufadi/MtkBatteryWarming

https://blog.csdn.net/su749520/article/details/80061946