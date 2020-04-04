package com.lib.library.widget.imageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.lib.library.utils.displaymetrics.DisplayMetricsUtils;

/**
 * @author zhongjh
 * @Description: 遮罩进度图片
 * @date 2018/1/8
 */
public class ProgressImageView extends AppCompatImageView {

    private static final String TAG = "MaskingProggress";
    //最大进度值
    private static final int MAX_PROGRESS = 100;

    // view 宽
    private int width;
    // view 高
    private int height;
    // 遮罩矩形
    private Rect rect;
    // 文字大小
    private int textSize = DisplayMetricsUtils.sp2px(12);
    private Paint maskingPaint;
    private Paint textPaint;

    public ProgressImageView(Context context) {
        this(context, null);
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        // 遮罩画笔，抗锯齿标志
        maskingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 遮罩填充
        maskingPaint.setStyle(Paint.Style.FILL);
        String maskingColor = "#4Dca0d0d";
        maskingPaint.setColor(Color.parseColor(maskingColor));

        // 进度画笔，抗锯齿标志
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSize);
        int textColor = Color.BLACK;
        textPaint.setColor(textColor);

    }

    /**
     * 测量
     *
     * @param widthMeasureSpec  宽度
     * @param heightMeasureSpec 高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (percentage > 0 && percentage < MAX_PROGRESS) {
            rect.top = height * percentage / MAX_PROGRESS;
            // 绘制图片遮罩
            canvas.drawRect(rect, maskingPaint);
            if (percentage < MAX_PROGRESS) {
                String text = "图片上传中";
                //测量文字的长度
                int textLength = (int) textPaint.measureText(text);
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                // 获取文字的高度
                int textHeight = (int) (fontMetrics.descent - fontMetrics.ascent);
                // 计算x轴居中的坐标
                int centerX = (width - textLength) / 2;
                int centerY = (int) ((height + textHeight) / 2 - fontMetrics.descent);
                canvas.drawText(text, centerX, centerY, textPaint);
                String percentageText = percentage + "%";
                int percentageTextLength = (int) textPaint.measureText(percentageText);
                canvas.drawText(percentageText, (width - percentageTextLength) / 2, (int) (height * 0.75), textPaint);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取view的宽高
        width = getWidth();
        height = getHeight();
        Log.d(TAG, "onSizeChanged: " + width + "----" + height);
        rect = new Rect();
        rect.left = 0;
        rect.top = 0;
        rect.right = width;
        rect.bottom = height;
    }

    int percentage = 0;

    /**
     * 设置进度之
     *
     * @param percentage 进度值
     */
    public void setPercentage(int percentage) {
        if (percentage > 0 && percentage <= MAX_PROGRESS) {
            this.percentage = percentage;
            Log.d(TAG, "setPercentage: " + percentage);
            invalidate();
        }
    }

    /**
     * 重置进度
     */
    public void reset() {
        this.percentage = 0;
        invalidate();
    }

    /**
     * 遮罩的颜色
     *
     * @param maskingColor
     */
    public void setMaskingColor(String maskingColor) {
        if (null != maskingPaint) {
            maskingPaint.setColor(Color.parseColor(maskingColor));
        }
    }

    /**
     * 进度字体大小
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        if (null != textPaint) {
            textPaint.setTextSize(textSize);
        }


    }

    /**
     * 进度颜色
     *
     * @param textColor
     */
    public void setTextColor(String textColor) {
        if (null != textPaint) {
            textPaint.setColor(Color.parseColor(textColor));
        }

    }

    /**
     * 测量宽高模式
     *
     * @param MeasureSpecSize
     * @return
     */
    private int measureSize(int MeasureSpecSize) {
        int size = 0;
        int[] ints = measureSpec(MeasureSpecSize);
        if (ints[0] == MeasureSpec.EXACTLY) {
            size = ints[1];
        } else {
            size = DisplayMetricsUtils.dip2px(70);
            if (ints[0] == MeasureSpec.AT_MOST) {
                size = Math.min(size, ints[1]);
            }
        }

        return size;
    }


    private int[] measureSpec(int measureSpec) {
        int[] measure = new int[2];
        measure[0] = MeasureSpec.getMode(measureSpec);
        measure[1] = MeasureSpec.getSize(measureSpec);
        return measure;
    }
}

