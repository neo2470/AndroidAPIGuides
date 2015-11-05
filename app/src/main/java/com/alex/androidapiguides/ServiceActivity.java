package com.alex.androidapiguides;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.alex.entity.HelloService;
import com.alex.entity.IntentServiceTest;
import com.alex.entity.LocalService;
import com.alex.entity.MessengerService;
import com.alex.entity.MusicService;

/**
 * Created by alex on 15-10-29.
 */
public class ServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        Intent intent = new Intent(this, LocalService.class);
//        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        Intent intent1 = new Intent(this, MessengerService.class);
        bindService(intent1, mConnection1, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mBound) {
            unbindService(mConnection);
            mBound = false;
        }

        if(mBound1) {
            unbindService(mConnection1);
            mBound1 = false;
        }
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.simpleServiceBtn : {

                Object tag = view.getTag();
                if(null == tag) {
                    tag = 1;
                    view.setTag(tag);
                } else {
                    view.setTag((int) tag + 1);
                }

                Intent intent = new Intent(this, IntentServiceTest.class);

                int tmp = (int) tag;
                if(1 == (tmp&1)) {
                    Toast.makeText(this, "Stop Simple Service", Toast.LENGTH_SHORT).show();
                    stopService(intent);
                } else {
                    Toast.makeText(this, "Start Simple Service", Toast.LENGTH_SHORT).show();
                    startService(intent);
                }
                break;
            }
            case R.id.intentServiceBtn : {
                Intent intent = new Intent(this, IntentServiceTest.class);
                intent.putExtra(IntentServiceTest.KEY_1, "杨婵");
                intent.putExtra(IntentServiceTest.KEY_2, 26);
                startService(intent);
                break;
            }
            case R.id.serviceBtn : {
                Intent intent = new Intent(this, HelloService.class);
                intent.putExtra(HelloService.KEY_NAME, "周旭峰");
                intent.putExtra(HelloService.KEY_AGE, 25);
                startService(intent);
                break;
            }
            case R.id.serviceBinderBtn : {
                Toast.makeText(this, mLocalService.getCount()+"", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.serviceMessengerBtn : {
                if (mBound) {
                    Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
                    try {
                        mMessenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case R.id.timerActivityBtn : {
                Intent intent = new Intent(this, TimerActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.musicServiceBtn : {
                Intent intent = new Intent(this, MusicPlayerActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mLocalService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    private ServiceConnection mConnection1 = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            mBound1 = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger = null;
            mBound1 = false;
        }
    };

    private boolean mBound;
    private boolean mBound1;
    private LocalService mLocalService;
    private Messenger mMessenger;
}
