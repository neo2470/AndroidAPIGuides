package com.alex.androidapiguides;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by alex on 15-4-12.
 * API Guides / User Interface / Toasts
 */
public class ToastsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toasts);
        changeTitle();
    }

    public void onClick(View view) {
        String info = null;
        switch (view.getId()) {
            case R.id.basicToast :
                info = "Basic Toast";
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                break;
            case R.id.positionToast :
                info = "Position Toast";
                Toast toast = Toast.makeText(this, info, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                break;
            case R.id.customToast :
                View toastView = getLayoutInflater().from(this).inflate(R.layout.toast_layout, null);
                Toast t = new Toast(this);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.setDuration(Toast.LENGTH_SHORT);
                t.setView(toastView);
                t.show();
                break;
        }
    }
}
