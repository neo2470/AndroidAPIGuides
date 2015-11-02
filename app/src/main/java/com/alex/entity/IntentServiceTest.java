package com.alex.entity;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by alex on 15-11-2.
 */
public class IntentServiceTest extends IntentService {

    public IntentServiceTest() {
        super("Hello Intent Services");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Debug", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Debug", "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("Debug", "onHandleIntent() Parameters : " + intent.getStringExtra(KEY_1) + ", " + intent.getIntExtra(KEY_2, 0));

        long endTime = System.currentTimeMillis() + 5*1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime-System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "onDestory()");
    }

    public static final String KEY_1 = "KEY_1";
    public static final String KEY_2 = "KEY_2";
}
