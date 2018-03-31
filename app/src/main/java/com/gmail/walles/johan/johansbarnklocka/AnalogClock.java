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
    private static final double BORDER_WIDTH_MM = 3;
    private final Paint borderPaint;

    public AnalogClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStrokeWidth(mmToPx(BORDER_WIDTH_MM));
        borderPaint.setStyle(Paint.Style.STROKE);
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

    }

    private void drawBorder(Canvas canvas) {
        canvas.drawCircle(
                canvas.getWidth() / 2,
                canvas.getWidth() / 2,
                canvas.getWidth() * 0.4f,
                borderPaint);
    }
}
