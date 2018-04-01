package com.gmail.walles.johan.johansbarnklocka;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AnalogClock extends View {
    private static final float CLOCK_RADIUS_PERCENT = 40;
    private static final float BORDER_WIDTH_PERCENT = 3;
    private final Paint borderPaint;

    private static final float MINUTE_TICK_RADIUS_PERCENT = 36;
    private static final float MINUTE_TICK_LENGTH_PERCENT = 4;
    private static final float MINUTE_TICK_WIDTH_PERCENT = .5f;
    private final Paint minuteTickPaint;

    private static final float HOUR_TICK_RADIUS_PERCENT = 36;
    private static final float HOUR_TICK_LENGTH_PERCENT = 4;
    private static final float HOUR_TICK_WIDTH_PERCENT = 1.5f;
    private final Paint hourTickPaint;

    public AnalogClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Stroke widths are set in onSizeChanged()

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);

        minuteTickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minuteTickPaint.setColor(Color.BLACK);
        minuteTickPaint.setStyle(Paint.Style.STROKE);

        hourTickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourTickPaint.setColor(Color.BLACK);
        hourTickPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        borderPaint.setStrokeWidth(w * BORDER_WIDTH_PERCENT / 100f);
        minuteTickPaint.setStrokeWidth(w * MINUTE_TICK_WIDTH_PERCENT / 100f);
        hourTickPaint.setStrokeWidth(w * HOUR_TICK_WIDTH_PERCENT / 100f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (canvas.getWidth() == 0 || canvas.getHeight() == 0) {
            // This can happen while Android Studio tries to preview us
            return;
        }

        drawBorder(canvas);
        drawMinuteTicks(canvas);
        drawHourTicks(canvas);
        drawHourNumbers(canvas);
        drawHands(canvas);
    }

    private void drawHands(Canvas canvas) {

    }

    private void drawHourNumbers(Canvas canvas) {

    }

    private void drawHourTicks(Canvas canvas) {
        for (int i = 1; i <= 12; i++) {
            double radians = (2 * Math.PI) * (i / 12.0);

            float inner_radius = canvas.getWidth() * (HOUR_TICK_RADIUS_PERCENT
                    - HOUR_TICK_LENGTH_PERCENT / 2f) / 100f;
            float x0 = canvas.getWidth() / 2 + (float)(inner_radius * Math.cos(radians));
            float y0 = canvas.getWidth() / 2 + (float)(inner_radius * Math.sin(radians));

            float outer_radius = canvas.getWidth() * (HOUR_TICK_RADIUS_PERCENT
                    + HOUR_TICK_LENGTH_PERCENT / 2f) / 100f;
            float x1 = canvas.getWidth() / 2 + (float)(outer_radius * Math.cos(radians));
            float y1 = canvas.getWidth() / 2 + (float)(outer_radius * Math.sin(radians));

            canvas.drawLine(x0, y0, x1, y1, hourTickPaint);
        }
    }

    private void drawMinuteTicks(Canvas canvas) {
        for (int i = 0; i <= 59; i++) {
            double radians = (2 * Math.PI) * (i / 60.0);

            float inner_radius = canvas.getWidth() * (MINUTE_TICK_RADIUS_PERCENT
                    - MINUTE_TICK_LENGTH_PERCENT / 2f) / 100f;
            float x0 = canvas.getWidth() / 2 + (float)(inner_radius * Math.cos(radians));
            float y0 = canvas.getWidth() / 2 + (float)(inner_radius * Math.sin(radians));

            float outer_radius = canvas.getWidth() * (MINUTE_TICK_RADIUS_PERCENT
                    + MINUTE_TICK_LENGTH_PERCENT / 2f) / 100f;
            float x1 = canvas.getWidth() / 2 + (float)(outer_radius * Math.cos(radians));
            float y1 = canvas.getWidth() / 2 + (float)(outer_radius * Math.sin(radians));

            canvas.drawLine(x0, y0, x1, y1, minuteTickPaint);
        }
    }

    private void drawBorder(Canvas canvas) {
        canvas.drawCircle(
                canvas.getWidth() / 2,
                canvas.getWidth() / 2,
                canvas.getWidth() * CLOCK_RADIUS_PERCENT / 100f,
                borderPaint);
    }
}
