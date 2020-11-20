package com.demo.toucheventtestdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TestViewGrop extends LinearLayout {
    private static final String TAG = TestViewGrop.class.getSimpleName();

    private TestButton button;

    public TestViewGrop(Context context) {
        super(context);
        init();
    }

    public TestViewGrop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestViewGrop(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        button = new TestButton(getContext());
        addView(button);
        setGravity(Gravity.CENTER);
    }

    public TestButton getButton() {
        return button;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "--- dispatchTouchEvent ---   ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "--- dispatchTouchEvent ---   ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "--- dispatchTouchEvent ---   ACTION_MOVE:  x_" + event.getX() + " / y_" + event.getY());
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "--- onTouchEvent ---   ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "--- onTouchEvent ---   ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "--- onTouchEvent ---   ACTION_MOVE:  x_" + event.getX() + " / y_" + event.getY());
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "--- onInterceptTouchEvent ---   ACTION_DOWN ");
                return true;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "--- onInterceptTouchEvent ---   ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "--- onInterceptTouchEvent ---   ACTION_MOVE:  x_" + ev.getX() + " / y_" + ev.getY());
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
