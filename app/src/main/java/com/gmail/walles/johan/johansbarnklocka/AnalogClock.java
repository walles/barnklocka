package com.gmail.walles.johan.johansbarnklocka;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class AnalogClock extends View {
    private static final float CLOCK_RADIUS_PERCENT = 40;
    private static final double BORDER_WIDTH_MM = 3;
    private final Paint borderPaint;

    private static final float TICK_RADIUS_PERCENT = 33;
    private static final float TICK_LENGTH_PERCENT = 4;
    private static final float TICK_WIDTH_MM = 0.5f;
    private final Paint minuteTickPaint;

    public AnalogClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStrokeWidth(mmToPx(BORDER_WIDTH_MM));
        borderPaint.setStyle(Paint.Style.STROKE);

        minuteTickPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minuteTickPaint.setColor(Color.BLACK);
        minuteTickPaint.setStrokeWidth(mmToPx(TICK_WIDTH_MM));
        minuteTickPaint.setStyle(Paint.Style.STROKE);
    }

    private float mmToPx(double mm) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, (float)mm, displayMetrics);
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

    }

    private void drawMinuteTicks(Canvas canvas) {
        for (int i = 0; i <= 59; i++) {
            double radians = (2 * Math.PI) * (i / 60.0);

            float inner_radius = canvas.getWidth() * (TICK_RADIUS_PERCENT - TICK_LENGTH_PERCENT / 2f) / 100f;
            float x0 = canvas.getWidth() / 2 + (float)(inner_radius * Math.cos(radians));
            float y0 = canvas.getWidth() / 2 + (float)(inner_radius * Math.sin(radians));

            float outer_radius = canvas.getWidth() * (TICK_RADIUS_PERCENT + TICK_LENGTH_PERCENT / 2f) / 100f;
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
