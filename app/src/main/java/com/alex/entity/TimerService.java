package com.alex.entity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

/**
 * Created by alex on 15-11-4.
 */
public class TimerService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Messenger messenger;
    public static final int MSG_COUNTER = 0x45645;
}
