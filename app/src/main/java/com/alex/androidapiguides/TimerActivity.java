package com.alex.androidapiguides;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alex.entity.TimerService;

/**
 * Created by alex on 15-11-5.
 */
public class TimerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_timer);

        final TextView counter = (TextView) findViewById(R.id.counter);
        Button startTimer = (Button) findViewById(R.id.startTimer);
        startTimer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Message msg = Message.obtain();
                msg.what = TimerService.TIMER;
                msg.replyTo = mReceiver;
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


        mConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }
        };

        Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == TIMER_RECEIVER) {
                    counter.setText(msg.arg1+"");
                }
            }
        };

        mReceiver = new Messenger(handler);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, TimerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(null != mConnection) {
            unbindService(mConnection);
        }
    }

    private Messenger mService;
    private Messenger mReceiver;
    private ServiceConnection mConnection;
    public static final int TIMER_RECEIVER = 0x0235315;
}
