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
        // FIXME: Code missing here!
    }
}
