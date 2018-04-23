/*
 * MIT License
 *
 * Copyright (c) 2018 M. Reza Nasirloo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.mrezanasirloo.preferencezero;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * A simple circular view
 */
public class CircleView extends View {
    private int mColor = Color.RED;
    private Paint mPaint;
    private int mViewSize;
    private int DEFAULT_VIEW_SIZE = 96;
    private RectF mInnerRectF = new RectF();

    public CircleView(Context context) {
        super(context);
        init(null, 0);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CircleView, defStyle, 0);
        mColor = a.getColor(R.styleable.CircleView_backgroundColor, mColor);

        a.recycle();

        // Set up a default Paint object
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mInnerRectF.set(0, 0, mViewSize, mViewSize);
        mInnerRectF.offset((getWidth() - mViewSize) / 2, (getHeight() - mViewSize) / 2);

        float centerX = mInnerRectF.centerX();
        float centerY = mInnerRectF.centerY();

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        canvas.drawCircle(centerX, centerY, contentWidth / 2, mPaint);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!enabled) {
            mPaint.setColor(getResources().getColor(R.color.gray_light));
            invalidate();
        } else {
            setColor(mColor);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = resolveSize(DEFAULT_VIEW_SIZE, widthMeasureSpec);
        int height = resolveSize(DEFAULT_VIEW_SIZE, heightMeasureSpec);
        mViewSize = Math.min(width, height);

        setMeasuredDimension(width, height);
    }


    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getColor() {
        return mColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color is the font
     * color.
     *
     * @param color The example color attribute value to use.
     */
    public void setColor(int color) {
        mColor = color;
        mPaint.setColor(mColor);
        invalidate();
    }
}
