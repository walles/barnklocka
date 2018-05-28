package com.gmail.walles.johan.johansbarnklocka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private AnalogClock analogClock;
    private TimeReadout timeReadout = new TimeReadout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        analogClock = findViewById(R.id.analogClock);
        analogClock.setOnTimeChanged(new AnalogClock.OnTimeChanged() {
            @Override
            public void onTimeChanged(int newHour, int newMinute) {
                setTime(newHour, newMinute);
            }
        });
        setTime(analogClock.getHour(), analogClock.getMinute());
    }

    private void setTime(int hour, int minute) {
        final TextView digitalClock = findViewById(R.id.digitalClock);
        digitalClock.setText(
                String.format(Locale.getDefault(),
                        "%02d:%02d", analogClock.getHour(), analogClock.getMinute()));

        final TextView analogReadout = findViewById(R.id.analogReadout);
        analogReadout.setText(timeReadout.format(hour, minute));
    }
}
