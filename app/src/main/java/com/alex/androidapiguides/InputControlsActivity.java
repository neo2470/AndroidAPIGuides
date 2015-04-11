package com.alex.androidapiguides;

import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.alex.fragment.DatePickerFragment;
import com.alex.fragment.TimePickerFragment;

/**
 * Created by alex on 15-4-10.
 * API Guides / User Interface / Input Controls
 */
public class InputControlsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_controls);
        changeTitle();

        final String[] data = getResources().getStringArray(R.array.country);
        AutoCompleteTextView autoText = (AutoCompleteTextView) findViewById(R.id.autoComp);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        autoText.setAdapter(adapter);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InputControlsActivity.this, data[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(InputControlsActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onCheckBoxChecked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        String option = null;
        switch (view.getId()) {
            case R.id.checkA :
                option = "A";
                break;
            case R.id.checkB :
                option = "B";
                break;
            case R.id.checkC :
                option = "C";
                break;
            case R.id.checkD :
                option = "D";
                break;
        }

        if(checked) {
            Toast.makeText(this, "Your option: "+ option, Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonChecked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        String option = null;
        switch (view.getId()) {
            case R.id.optA :
                option = "A";
                break;
            case R.id.optB :
                option = "B";
                break;
            case R.id.optC :
                option = "C";
                break;
            case R.id.optD :
                option = "D";
                break;
        }

        if(checked) {
            Toast.makeText(this, "Your option: "+ option, Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog(View view) {
        DialogFragment dialog = null;
        String tag = null;
        switch (view.getId()) {
            case R.id.timePicker :
                dialog = new TimePickerFragment();
                tag = "timePicker";
                break;
            case R.id.datePicker :
                dialog = new DatePickerFragment();
                tag = "datePicker";
                break;
        }
        dialog.show(getFragmentManager(), tag);
    }

}
