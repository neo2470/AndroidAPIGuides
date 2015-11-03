package com.alex.entity;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by alex on 15-11-3.
 */
public class MessengerService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Debug", "onBind()");
        return mMessenger.getBinder();
    }

    class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO : {
                    Toast.makeText(getApplicationContext(), "Service Use Messenger", Toast.LENGTH_SHORT).show();
                }
                default :
                    super.handleMessage(msg);
            }

        }
    }

    final Messenger mMessenger = new Messenger(new IncomingHandler());
    public static final int MSG_SAY_HELLO = 1;
}
