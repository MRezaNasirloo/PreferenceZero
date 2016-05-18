package com.sixthsolution.materialpreferencesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sixthsolution.materialpreferences.preferences.EasyPreferenceSwitch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EasyPreferenceSwitch tempSwitch =
                (EasyPreferenceSwitch) findViewById(R.id.preferences_measurement_unit);
        tempSwitch.setOffText("F");
        tempSwitch.setOnText("C");

        EasyPreferenceSwitch clockSwitch =
                (EasyPreferenceSwitch) findViewById(R.id.preferences_clock_format);
        clockSwitch.setOffText("24");
        clockSwitch.setOnText("12");
    }
}
