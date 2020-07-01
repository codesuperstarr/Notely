package com.example.notely;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

public class LinedEditText extends AppCompatEditText {
    private Rect mRect;
    private Paint mPaint;
    @Override
    protected void onDraw(Canvas canvas) {

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(0xFFFFD966);

        int height = ((View)this.getParent()).getHeight();
        int lineHeight = getLineHeight();
        int numberOfLines = height / lineHeight;

        Rect r = mRect;
        Paint paint = mPaint;

        int baseLines = getLineBounds(0,r);

        for (int i = 0; i < numberOfLines; i++){
            canvas.drawLine(r.left, baseLines + 1,r.right, baseLines + 1, paint);
            baseLines += lineHeight;

        }

        super.onDraw(canvas);
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);


    }
}
