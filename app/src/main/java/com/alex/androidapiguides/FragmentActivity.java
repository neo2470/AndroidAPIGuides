package com.alex.androidapiguides;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;

import com.alex.fragment.AFragment;
import com.alex.fragment.BFragment;
import com.alex.fragment.CFragment;

/**
 * Created by alex on 15-10-21.
 */
public class FragmentActivity extends BaseActivity implements AFragment.OnBtnAClickListener, BFragment.OnBtnBClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment);

        if(null == savedInstanceState) {
            FragmentManager fm = getFragmentManager();
            AFragment aFragment = (AFragment) fm.findFragmentByTag(AFragment.TAG);
            if (null == aFragment) {
                aFragment = new AFragment();
            }
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, aFragment, AFragment.TAG);
            ft.commit();
        }
    }

    @Override
    public void onBtnAClicked() {
        FragmentManager fm = getFragmentManager();
        BFragment bFragment = (BFragment) fm.findFragmentByTag(BFragment.TAG);

        if(null == bFragment) {
            bFragment = new BFragment();
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content, bFragment, BFragment.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBtnBClicked() {
        FragmentManager fm = getFragmentManager();
        CFragment cFragment = (CFragment) fm.findFragmentByTag(CFragment.TAG);

        if(null == cFragment) {
            cFragment = new CFragment();
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content, cFragment, CFragment.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}
