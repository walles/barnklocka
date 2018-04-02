package com.gmail.walles.johan.johansbarnklocka;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Hand {
    protected int hour;
    protected int minute;

    private final Paint paint;
    private final float lengthPercent;
    private final float widthPercent;

    protected Hand(Paint paint, float widthPercent, float lengthPercent) {
        this.paint = paint;
        this.widthPercent = widthPercent;
        this.lengthPercent = lengthPercent;
    }

    public void draw(Canvas canvas) {
        double radians = getRadians();
        float radius = canvas.getWidth() * lengthPercent / 100f;
        float x1 = canvas.getWidth() / 2 + (float)(radius * Math.sin(radians));
        float y1 = canvas.getWidth() / 2 - (float)(radius * Math.cos(radians));

        paint.setStrokeWidth(canvas.getWidth() * widthPercent / 100f);
        canvas.drawLine(
                canvas.getWidth() / 2f,
                canvas.getWidth() / 2f,
                x1,
                y1,
                paint);
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    protected abstract double getRadians();
}
