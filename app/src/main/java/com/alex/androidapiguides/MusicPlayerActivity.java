package com.alex.androidapiguides;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import com.alex.entity.MusicService;

/**
 * Created by alex on 15-11-5.
 */
public class MusicPlayerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        mConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mSender = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mSender = null;
            }
        };
    }

    public void onClick(View view) {

        Message msg = Message.obtain();

        switch (view.getId()) {
            case R.id.play : {
                msg.what = MusicService.PLAYER_PLAY;
                break;
            }
            case R.id.pause : {
                msg.what = MusicService.PLAYER_PAUSE;
                break;
            }
            case R.id.stop : {
                msg.what = MusicService.PLAYER_STOP;
                break;
            }
        }

        try {
            mSender.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(null != mConnection) {
            unbindService(mConnection);
        }
    }

    private Messenger mSender;
    private ServiceConnection mConnection;
}
