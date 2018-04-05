package com.gmail.walles.johan.johansbarnklocka;

import android.graphics.Paint;

public class MinuteHand extends Hand {
    public MinuteHand(Paint paint, float widthPercent, float lengthPercent) {
        super(paint, widthPercent, lengthPercent);
    }

    @Override
    protected double getRadians() {
        return (2 * Math.PI) * (minute / 60.0);
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
        minute = (int)((radians / (2 * Math.PI)) * 60.0);

        // FIXME: Handle change when moving the minute between 0 and 59
    }
}
