package com.alex.entity;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.alex.androidapiguides.TimerActivity;

/**
 * Created by alex on 15-11-4.
 */
public class TimerService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Debug", "onCreate()");

        Handler handler = new Handler() {

            @Override
            public void handleMessage(final Message msg) {
                switch (msg.what) {
                    case TIMER : {
                        Message msg1 = Message.obtain();
                        msg1.what = TimerActivity.TIMER_RECEIVER;
                        msg1.arg1 = ++counter;

                        try {
                            msg.replyTo.send(msg1);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    default :
                        super.handleMessage(msg);
                }
            }
        };

        mMsg = new Messenger(handler);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Debug", "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Debug", "onBind()");
        return mMsg.getBinder();
    }

    @Override
    public void onDestroy() {
        Log.d("Debug", "onDestroy()");
        super.onDestroy();
    }

    private Messenger mMsg;
    private static int counter;

    public static final int TIMER = 0x001;
}
