package com.example.cmuproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MyAsyncTask mytask=new MyAsyncTask(context);
        mytask.execute();

    }


}
