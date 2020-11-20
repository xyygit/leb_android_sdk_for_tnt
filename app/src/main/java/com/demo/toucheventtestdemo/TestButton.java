package com.demo.toucheventtestdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestButton extends androidx.appcompat.widget.AppCompatButton {
    private static final String TAG = TestButton.class.getSimpleName();

    public TestButton(@NonNull Context context) {
        super(context);
        init();
    }

    public TestButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setText("事件分发演示");
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
}
