package com.alex.androidapiguides;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by alex on 15-10-14.
 */
public class IntentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
    }

    public void onClick(View view) {
       switch (view.getId()) {
           case R.id.btn1 : {

               // 写法1
//               Intent intent = new Intent();
//               ComponentName cmp = new ComponentName(this, IntentActivity1.class);
//               intent.setComponent(cmp);
//               startActivity(intent);

               // 写法2
               Intent intent1 = new Intent(this, IntentActivity1.class);
               startActivity(intent1);

               break;
           }

           case R.id.btn2 : {
               Intent intent = new Intent();
               intent.setAction("com.alex.androidapiguides.MY_ACTION");
               startActivity(intent);
               break;
           }

           case R.id.btn3 : {
               Intent intent = new Intent();
               intent.setAction("com.alex.androidapiguides.MY_ACTION");
               intent.addCategory("com.alex.androidapiguides.MY_CATEGORY");
               startActivity(intent);
               break;
           }

           case R.id.btn4 : {
               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("http://www.baidu.com"));
               startActivity(intent);
               break;
           }

           case R.id.btn5 : {
               Intent intent = new Intent(Intent.ACTION_CALL);
               intent.setData(Uri.parse("tel:10086"));

               if(null != intent.resolveActivity(getPackageManager())) {
                   startActivity(intent);
               }

               break;
           }
       }
    }
}
