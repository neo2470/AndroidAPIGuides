package com.alex.entity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import com.alex.androidapiguides.R;

/**
 * Created by alex on 15-11-5.
 */
public class MusicService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case PLAYER_PLAY : {
                        mPlayer.start();
                        break;
                    }
                    case PLAYER_PAUSE : {
                        mPlayer.pause();
                        break;
                    }
                    case PLAYER_STOP : {
                        mPlayer.stop();
                        break;
                    }
                    default :
                        super.handleMessage(msg);
                }
                super.handleMessage(msg);

            }
        };

        mMsg = new Messenger(handler);

        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.maps);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMsg.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }

    public static final int PLAYER_PLAY = 0x0001;
    public static final int PLAYER_PAUSE = 0x0002;
    public static final int PLAYER_STOP = 0x0003;

    private MediaPlayer mPlayer;
    private Messenger mMsg;
}
