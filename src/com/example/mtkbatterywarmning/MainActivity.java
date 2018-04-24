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
