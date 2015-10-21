package com.alex.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alex.androidapiguides.R;

/**
 * Created by alex on 15-10-21.
 */
public class AFragment extends BaseFragment {

    public interface OnBtnAClickListener {
        void onBtnAClicked();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Debug-" + AFragment.class.getSimpleName(), "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.a_fragment, container, false);
        Button btn1 = (Button) view.findViewById(R.id.btnA);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != getActivity()) {
                    ((OnBtnAClickListener)getActivity()).onBtnAClicked();
                }
            }
        });

        return view;
    }

    public final static String TAG = AFragment.class.getName();
}
