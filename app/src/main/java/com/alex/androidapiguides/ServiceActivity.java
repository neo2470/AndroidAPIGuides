package com.alex.androidapiguides;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alex.entity.IntentServiceTest;

/**
 * Created by alex on 15-10-29.
 */
public class ServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.intentServiceBtn : {
                Intent intent = new Intent(this, IntentServiceTest.class);
                intent.putExtra(IntentServiceTest.KEY_1, "杨婵");
                intent.putExtra(IntentServiceTest.KEY_2, 26);
                startService(intent);
                break;
            }
            case R.id.serviceBtn : {
                break;
            }
        }
    }
}
