package com.demo.toucheventtestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TestViewGrop testViewGrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testViewGrop = findViewById(R.id.view_test);
        testViewGrop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "--- onClick --- ");
        startActivity(new Intent(MainActivity.this, PlayerActivity.class));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "--- onTouch ---   ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "--- onTouch ---   ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "--- onTouch ---   ACTION_MOVE:  x_" + event.getX() + " / y_" + event.getY());
                break;
            default:
                break;
        }
        return false;
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