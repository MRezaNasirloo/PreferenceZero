package com.sixthsolution.materialpreferencesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sixthsolution.materialpreferences.preferences.EasyPreferenceCheckBox;
import com.sixthsolution.materialpreferences.preferences.EasyPreferenceSwitchCompoundButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EasyPreferenceSwitchCompoundButton tempSwitch =
                (EasyPreferenceSwitchCompoundButton) findViewById(R.id.preferences_measurement_unit);
        tempSwitch.setOffText("F");
        tempSwitch.setOnText("C");

        EasyPreferenceSwitchCompoundButton clockSwitch =
                (EasyPreferenceSwitchCompoundButton) findViewById(R.id.preferences_clock_format);
        clockSwitch.setOffText("24");
        clockSwitch.setOnText("12");

        EasyPreferenceCheckBox checkBox = (EasyPreferenceCheckBox) findViewById(R.id.preferences_auto_update);
        checkBox.setOffText("foo");
    }
}
