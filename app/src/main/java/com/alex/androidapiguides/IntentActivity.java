package com.alex.androidapiguides;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.alex.entity.PeopleParcelable;
import com.alex.entity.PeopleSerializable;

import java.util.ArrayList;

/**
 * Created by alex on 15-10-14.
 * Learn to use Intent
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
               Intent intent = new Intent(this, IntentActivity1.class);

               // 传递基本类型数据
               intent.putExtra("intent_base", "基本类型的数据");

               // 传递数组
               intent.putExtra("intent_array", new String[]{"杨婵", "周旭峰"});

               // 传递List<String>
               ArrayList<String> string = new ArrayList<>();
               string.add("杨鹏");
               string.add("周莉");
               string.add("周瑜");
               intent.putStringArrayListExtra("intent_list_string", string);

               // 传递List<Integer>
               ArrayList<Integer> integer = new ArrayList<>();
               integer.add(1);
               integer.add(2);
               integer.add(3);
               intent.putIntegerArrayListExtra("intent_list_integer", integer);

               // 传递Serializable
               PeopleSerializable peopleSerializable = new PeopleSerializable("张三");
               intent.putExtra("intent_serializable", peopleSerializable);

               // 传递Parcelable
               PeopleParcelable peopleParcelable = new PeopleParcelable("李四");
               intent.putExtra("intent_parcelable", peopleParcelable);

               // 传递List<Parcelable>
               ArrayList<PeopleParcelable> peopleParcelableList = new ArrayList<>();
               peopleParcelableList.add(new PeopleParcelable("Parcelable 1"));
               peopleParcelableList.add(new PeopleParcelable("Parcelable 2"));
               peopleParcelableList.add(new PeopleParcelable("Parcelable 3"));
               intent.putParcelableArrayListExtra("intent_list_parcelable", peopleParcelableList);

               startActivity(intent);
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

           case R.id.btn6 : {
               Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                       .putExtra(AlarmClock.EXTRA_MESSAGE, "This is a Alarm test which is set by implicit intent")
                       .putExtra(AlarmClock.EXTRA_HOUR, 10)
                       .putExtra(AlarmClock.EXTRA_MINUTES, 10);

               if(null != intent.resolveActivity(getPackageManager())) {
                   startActivity(intent);
               }
               break;
           }

           case R.id.btn7 : {
               Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                       .putExtra(AlarmClock.EXTRA_MESSAGE, "This time is set by implicit intent")
                       .putExtra(AlarmClock.EXTRA_LENGTH, 60)
                       .putExtra(AlarmClock.EXTRA_SKIP_UI, false);

               if(null != intent.resolveActivity(getPackageManager())) {
                   startActivity(intent);
               }
               break;
           }

           case R.id.btn8 : {
               Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);

               if (null != intent.resolveActivity(getPackageManager())) {
                   startActivity(intent);
               }
               break;
           }

           case R.id.btn9 : {
               Intent intent = new Intent(Intent.ACTION_INSERT)
                       .setData(CalendarContract.Events.CONTENT_URI)
                       .putExtra(CalendarContract.Events.TITLE, "This is a calendar event")
                       .putExtra(CalendarContract.Events.EVENT_LOCATION, "Shenzhen Luohu")
                       .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, "10:00")
                       .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, "12:00");

               if(null != intent.resolveActivity(getPackageManager())) {
                   startActivity(intent);
               }
               break;
           }

           case R.id.btn10 : {
               Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);

               if(null != intent.resolveActivity(getPackageManager())) {
                   startActivity(intent);
               }
               break;
           }

           case R.id.btn11 : {
               Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);

               if(null != intent.resolveActivity(getPackageManager())) {
                   startActivity(intent);
               }
               break;
           }

           case R.id.btn12 : {
               Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

               if(null != intent.resolveActivity(getPackageManager())) {
                   startActivityForResult(intent, PICK_CONTACTS_REQUEST);
               }
           }
       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(RESULT_OK == resultCode) {
            if(PICK_CONTACTS_REQUEST == requestCode) {
                Cursor cursor = getContentResolver().query(data.getData(), new String[] {ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);

                if(cursor.moveToFirst()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private final int PICK_CONTACTS_REQUEST = 1;
}
