package cn.damai.libs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;

import cn.damai.libs.R;

/**
 * Created by wanggeng on 2017/5/17.
 */

public class DelegateImageView extends android.support.v7.widget.AppCompatImageView {
    private static final float TOUCH_ADDITION = 10;
    private final int mAddition;

    public DelegateImageView(Context context) {
        this(context, null, 0);
    }

    public DelegateImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DelegateImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DelegateImageView);
        mAddition = (int) array.getDimension(R.styleable.DelegateImageView_addition, TOUCH_ADDITION);
        array.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.getParent() instanceof View) {
            final View parent = (View) this.getParent();
            parent.setTouchDelegate(new TouchDelegate(new Rect(left - mAddition, top - mAddition, right + mAddition, bottom + mAddition), this));
        }
    }
}
