package com.gmail.walles.johan.johansbarnklocka;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class AnalogClock extends View {
    private static final float CLOCK_RADIUS_PERCENT = 40;
    private static final float BORDER_WIDTH_PERCENT = 3;
    private final Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float MINUTE_TICK_RADIUS_PERCENT = 36;
    private static final float MINUTE_TICK_LENGTH_PERCENT = 4;
    private static final float MINUTE_TICK_WIDTH_PERCENT = .5f;
    private final Paint minuteTickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float HOUR_TICK_RADIUS_PERCENT = 36;
    private static final float HOUR_TICK_LENGTH_PERCENT = 4;
    private static final float HOUR_TICK_WIDTH_PERCENT = 1.5f;
    private final Paint hourTickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float HOUR_NUMBER_RADIUS_PERCENT = 29;
    private static final float HOUR_FONT_SIZE_PERCENT = 7;
    private final Paint hourNumberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float HOUR_HAND_LENGTH_PERCENT = 20;
    private static final float HOUR_HAND_WIDTH_PERCENT = 4;
    private final Paint hourHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float MINUTE_HAND_LENGTH_PERCENT = 26;
    private static final float MINUTE_HAND_WIDTH_PERCENT = 3;
    private final Paint minuteHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int hour;
    private int minute;

    public AnalogClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minute = Calendar.getInstance().get(Calendar.MINUTE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(w * BORDER_WIDTH_PERCENT / 100f);

        minuteTickPaint.setColor(Color.BLACK);
        minuteTickPaint.setStyle(Paint.Style.STROKE);
        minuteTickPaint.setStrokeWidth(w * MINUTE_TICK_WIDTH_PERCENT / 100f);

        hourTickPaint.setColor(Color.BLACK);
        hourTickPaint.setStyle(Paint.Style.STROKE);
        hourTickPaint.setStrokeWidth(w * HOUR_TICK_WIDTH_PERCENT / 100f);

        hourNumberPaint.setColor(Color.BLACK);
        hourNumberPaint.setStyle(Paint.Style.STROKE);
        hourNumberPaint.setTextSize(w * HOUR_FONT_SIZE_PERCENT / 100f);

        hourHandPaint.setColor(Color.BLACK);
        hourHandPaint.setStrokeCap(Paint.Cap.ROUND);
        hourHandPaint.setStrokeWidth(w * HOUR_HAND_WIDTH_PERCENT / 100f);

        minuteHandPaint.setColor(Color.BLACK);
        minuteHandPaint.setStrokeCap(Paint.Cap.ROUND);
        minuteHandPaint.setStrokeWidth(w * MINUTE_HAND_WIDTH_PERCENT / 100f);
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
        drawMinuteHand(canvas);
        drawHourHand(canvas);
    }

    private void drawHourHand(Canvas canvas) {
        double decimalHours = hour + (minute / 60.0);
        double radians = (2 * Math.PI) * (decimalHours / 12.0);
        float radius = canvas.getWidth() * HOUR_HAND_LENGTH_PERCENT / 100f;
        float x1 = canvas.getWidth() / 2 + (float)(radius * Math.sin(radians));
        float y1 = canvas.getWidth() / 2 - (float)(radius * Math.cos(radians));
        canvas.drawLine(
                canvas.getWidth() / 2f,
                canvas.getWidth() / 2f,
                x1,
                y1,
                hourHandPaint);
    }

    private void drawMinuteHand(Canvas canvas) {
        double radians = (2 * Math.PI) * (minute / 60.0);
        float radius = canvas.getWidth() * MINUTE_HAND_LENGTH_PERCENT / 100f;
        float x1 = canvas.getWidth() / 2 + (float)(radius * Math.sin(radians));
        float y1 = canvas.getWidth() / 2 - (float)(radius * Math.cos(radians));
        canvas.drawLine(
                canvas.getWidth() / 2f,
                canvas.getWidth() / 2f,
                x1,
                y1,
                minuteHandPaint);
    }

    private void drawHourNumbers(Canvas canvas) {
        for (int i = 1; i <= 12; i++) {
            double radians = (2 * Math.PI) * (i / 12.0);

            float radius = canvas.getWidth() * HOUR_NUMBER_RADIUS_PERCENT / 100f;
            float x0 = canvas.getWidth() / 2 + (float)(radius * Math.sin(radians));
            float y0 = canvas.getWidth() / 2 - (float)(radius * Math.cos(radians));

            String hourString = Integer.toString(i);
            Rect textBounds = new Rect();
            hourNumberPaint.getTextBounds(hourString, 0, hourString.length(), textBounds);
            canvas.drawText(
                    hourString,
                    x0 - textBounds.exactCenterX(),
                    y0 - textBounds.exactCenterY(),
                    hourNumberPaint);
        }
    }

    private void drawHourTicks(Canvas canvas) {
        for (int i = 1; i <= 12; i++) {
            double radians = (2 * Math.PI) * (i / 12.0);

            float inner_radius = canvas.getWidth() * (HOUR_TICK_RADIUS_PERCENT
                    - HOUR_TICK_LENGTH_PERCENT / 2f) / 100f;
            float x0 = canvas.getWidth() / 2 + (float)(inner_radius * Math.sin(radians));
            float y0 = canvas.getWidth() / 2 - (float)(inner_radius * Math.cos(radians));

            float outer_radius = canvas.getWidth() * (HOUR_TICK_RADIUS_PERCENT
                    + HOUR_TICK_LENGTH_PERCENT / 2f) / 100f;
            float x1 = canvas.getWidth() / 2 + (float)(outer_radius * Math.sin(radians));
            float y1 = canvas.getWidth() / 2 - (float)(outer_radius * Math.cos(radians));

            canvas.drawLine(x0, y0, x1, y1, hourTickPaint);
        }
    }

    private void drawMinuteTicks(Canvas canvas) {
        for (int i = 0; i <= 59; i++) {
            double radians = (2 * Math.PI) * (i / 60.0);

            float inner_radius = canvas.getWidth() * (MINUTE_TICK_RADIUS_PERCENT
                    - MINUTE_TICK_LENGTH_PERCENT / 2f) / 100f;
            float x0 = canvas.getWidth() / 2 + (float)(inner_radius * Math.sin(radians));
            float y0 = canvas.getWidth() / 2 - (float)(inner_radius * Math.cos(radians));

            float outer_radius = canvas.getWidth() * (MINUTE_TICK_RADIUS_PERCENT
                    + MINUTE_TICK_LENGTH_PERCENT / 2f) / 100f;
            float x1 = canvas.getWidth() / 2 + (float)(outer_radius * Math.sin(radians));
            float y1 = canvas.getWidth() / 2 - (float)(outer_radius * Math.cos(radians));

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
