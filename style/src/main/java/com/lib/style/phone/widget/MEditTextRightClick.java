package com.lib.style.phone.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 这是个触发编辑框右边图标事件
 * 
 * @author zhongjh
 * 
 */
public class MEditTextRightClick extends androidx.appcompat.widget.AppCompatEditText {

	public MEditTextRightClick(Context context) {
		this(context, null);
	}

	public MEditTextRightClick(Context context, AttributeSet attrs) {
		// 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
		this(context, attrs, android.R.attr.editTextStyle);
	}

	public MEditTextRightClick(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
	 * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
	 */
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {

				boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
						&& (event.getX() < ((getWidth() - getPaddingRight())));

				if (touchable) {
					mIRightOnClick.onClick();
				}
			}
		}
		return super.onTouchEvent(event);
	}

	public interface IRightOnClick {
		/**
		 * 成功
		 */
		void onClick();
	}

	/**
	 * 成功或者失败的回调函数
	 */
	public IRightOnClick mIRightOnClick;

	public IRightOnClick getIRightOnClick() {
		return mIRightOnClick;
	}

	public void setIRightOnClick(IRightOnClick iRightOnClick) {
		this.mIRightOnClick = iRightOnClick;
	}

    /**
     * 解决google提示
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean performClick() {
        return true;
    }

}
