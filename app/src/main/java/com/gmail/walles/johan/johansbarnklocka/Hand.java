package com.gmail.walles.johan.johansbarnklocka;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Hand {
    protected int hour;
    protected int minute;

    private final Paint paint;
    private final float lengthPercent;
    private final float widthPercent;

    private float handEndX;
    private float handEndY;

    protected Hand(Paint paint, float widthPercent, float lengthPercent) {
        this.paint = paint;
        this.widthPercent = widthPercent;
        this.lengthPercent = lengthPercent;
    }

    public void draw(Canvas canvas) {
        double radians = getRadians();
        float radius = canvas.getWidth() * lengthPercent / 100f;
        handEndX = canvas.getWidth() / 2 + (float)(radius * Math.sin(radians));
        handEndY = canvas.getWidth() / 2 - (float)(radius * Math.cos(radians));

        paint.setStrokeWidth(canvas.getWidth() * widthPercent / 100f);
        canvas.drawLine(
                canvas.getWidth() / 2f,
                canvas.getWidth() / 2f,
                handEndX,
                handEndY,
                paint);
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    protected abstract double getRadians();

    public double getDistanceTo(float x, float y) {
        double dx = x - handEndX;
        double dy = y - handEndY;

        return Math.sqrt(dx * dx + dy * dy);
    }
}
