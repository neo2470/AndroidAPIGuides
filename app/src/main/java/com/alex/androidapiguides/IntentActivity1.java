package com.alex.androidapiguides;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alex.entity.PeopleParcelable;
import com.alex.entity.PeopleSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 15-10-14.
 */
public class IntentActivity1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_1);

        Intent intent = getIntent();
        StringBuilder builder = new StringBuilder();

        // 基本类型数据
        builder.append("intent_base ： ")
            .append(intent.getStringExtra("intent_base"))
            .append("\n");

        // 基本类型数组
        builder.append("intent_array : ");
        String[] string =  intent.getStringArrayExtra("intent_array");
        for(String s : string) {
            builder.append(s);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n");

        // List<String>
        builder.append("intent_list_string : ");
        List<String> strings = intent.getStringArrayListExtra("intent_list_string");
        for(String s : strings) {
            builder.append(s);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n");

        // List<Integer>
        builder.append("intent_list_integer : ");
        List<Integer> integers = intent.getIntegerArrayListExtra("intent_list_integer");
        for(int i : integers) {
            builder.append(i);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n");

        // Serializable
        builder.append("intent_serializable : ");
        PeopleSerializable peopleSerializable = (PeopleSerializable) intent.getSerializableExtra("intent_serializable");
        builder.append(peopleSerializable.getName());
        builder.append("\n");

        // Parcelable
        builder.append("intent_parcelable : ");
        PeopleParcelable peopleParcelable = intent.getParcelableExtra("intent_parcelable");
        builder.append(peopleParcelable.getName());
        builder.append("\n");

        // List<Parcelable>
        builder.append("intent_list_parcelable : ");
        ArrayList<PeopleParcelable> peopleParcelableList = intent.getParcelableArrayListExtra("intent_list_parcelable");
        for(PeopleParcelable p : peopleParcelableList) {
            builder.append(p.getName());
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n");

        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(builder.toString());
    }
}
