package com.alex.entity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by alex on 15-11-3.
 */
public class LocalService extends Service {

    public class LocalBinder extends Binder {
        public LocalService getService () {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int getCount() {
        return ++count;
    }

    private IBinder mBinder = new LocalBinder();
    private static int count;
}
