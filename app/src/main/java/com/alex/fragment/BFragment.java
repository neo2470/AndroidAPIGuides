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
public class BFragment extends BaseFragment {

    public interface OnBtnBClickListener {
        void onBtnBClicked();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Debug-" + BFragment.class.getSimpleName(), "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.b_fragment, container, false);
        Button btnB = (Button) view.findViewById(R.id.btnB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != getActivity()) {
                    ((OnBtnBClickListener)getActivity()).onBtnBClicked();
                }
            }
        });

        return view;
    }

    public final static String TAG = BFragment.class.getName();
}
