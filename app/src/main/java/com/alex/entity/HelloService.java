package com.alex.entity;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

/**
 * Created by alex on 15-11-2.
 */
public class HelloService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Debug", "onCreate()");

        HandlerThread handlerThread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();

        mServiceLooper = handlerThread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Debug", "onStartCommand() Parameters : " + intent.getStringExtra(KEY_NAME) + ", " + intent.getIntExtra(KEY_AGE, 0));

        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Debug", "onBind()");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "onDestroy()");
    }

    private final class ServiceHandler extends Handler {

        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
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

            stopSelf(msg.arg1);
        }
    }

    public static final String KEY_NAME = "key_name";
    public static final String KEY_AGE = "key_age";

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
}
