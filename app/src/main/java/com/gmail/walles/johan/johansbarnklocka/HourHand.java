package com.gmail.walles.johan.johansbarnklocka;

import android.graphics.Paint;
import android.support.annotation.VisibleForTesting;

public class HourHand extends Hand {
    public HourHand(Paint paint, float widthPercent, float lengthPercent) {
        super(paint, widthPercent, lengthPercent);
    }

    @Override
    protected double getRadians() {
        double decimalHours = hour + (minute / 60.0);
        return (2 * Math.PI) * (decimalHours / 12.0);
    }

    @Override
    public void move(float x, float y) {
        if (x == centerX && y == centerY) {
            // Hitting the center has undefined direction
            return;
        }

        // Compute the new radians value
        float dx = x - centerX;
        float dy = y - centerY;
        double radians = Math.atan2(dx, -dy);

        // Update the time based on the new radians value
        double decimalHours = (radians / (2 * Math.PI)) * 12.0;
        if (decimalHours < 0) {
            decimalHours += 12;
        }
        setHour(decimalHours);
    }

    @VisibleForTesting
    void setHour(double decimalHours) {
        // From: https://stackoverflow.com/a/343602/473672
        double fractionalPart = decimalHours % 1;
        int newHour = (int)(decimalHours - fractionalPart);

        hour = newHour;
        minute = (int)(fractionalPart * 60);
    }
}
