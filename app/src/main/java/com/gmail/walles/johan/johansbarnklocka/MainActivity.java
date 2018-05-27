package com.gmail.walles.johan.johansbarnklocka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView digitalClock = findViewById(R.id.digitalClock);

        AnalogClock analogClock = findViewById(R.id.analogClock);
        digitalClock.setText(
                String.format(Locale.getDefault(),
                        "%02d:%02d", analogClock.getHour(), analogClock.getMinute()));
    }
}
