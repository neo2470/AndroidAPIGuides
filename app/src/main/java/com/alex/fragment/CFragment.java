package com.alex.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alex.androidapiguides.R;

/**
 * Created by alex on 15-10-21.
 */
public class CFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Debug-" + CFragment.class.getSimpleName(), "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.c_fragment, container, false);
    }

    public final static String TAG = CFragment.class.getName();
}
