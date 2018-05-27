package com.gmail.walles.johan.johansbarnklocka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private AnalogClock analogClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        analogClock = findViewById(R.id.analogClock);
        analogClock.setOnTimeChanged(new AnalogClock.OnTimeChanged() {
            @Override
            public void onTimeChanged(int newHour, int newMinute) {
                setDigitalTime(newHour, newMinute);
            }
        });
        setDigitalTime(analogClock.getHour(), analogClock.getMinute());
    }

    private void setDigitalTime(int hour, int minute) {
        final TextView digitalClock = findViewById(R.id.digitalClock);
        digitalClock.setText(
                String.format(Locale.getDefault(),
                        "%02d:%02d", analogClock.getHour(), analogClock.getMinute()));
    }
}
