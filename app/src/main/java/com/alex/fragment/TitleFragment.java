package com.alex.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alex.androidapiguides.R;

/**
 * Created by alex on 15-10-21.
 */
public class TitleFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_fragment, container, false);

        ImageButton speakBtn = (ImageButton) view.findViewById(R.id.btn_speak);
        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Speak Now", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
