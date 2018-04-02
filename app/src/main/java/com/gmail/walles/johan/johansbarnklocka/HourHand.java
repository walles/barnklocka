package com.gmail.walles.johan.johansbarnklocka;

import android.graphics.Paint;

public class HourHand extends Hand {
    public HourHand(Paint paint, float widthPercent, float lengthPercent) {
        super(paint, widthPercent, lengthPercent);
    }

    @Override
    protected double getRadians() {
        double decimalHours = hour + (minute / 60.0);
        return (2 * Math.PI) * (decimalHours / 12.0);
    }
}
