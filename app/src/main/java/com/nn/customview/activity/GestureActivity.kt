package com.nn.customview.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.nn.customview.R
import kotlinx.android.synthetic.main.activity_gesture.*

class GestureActivity : AppCompatActivity(), View.OnTouchListener {

    var mGestureDetector: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture)
        mGestureDetector = GestureDetector(this, GestureListener())
        tv_gesture.setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return mGestureDetector!!.onTouchEvent(event)
    }
}

class GestureListener : GestureDetector.OnGestureListener {

    val TAG = "GestureActivity"
    override fun onShowPress(e: MotionEvent?) {
        //按下，在down之后触发
        Log.e(TAG, "onShowPress")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.e(TAG, "onSingleTapUp")
        //短时间按下抬起触发
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        //按下触发
        Log.e(TAG, "onDown")
        return true
    }

    /**
     * velocityX，x轴方向移动的速度，单位像素秒
     */
    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.e(TAG, "onFling")
        //滑动后手指离开触发
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.e(TAG, "onScroll")
        //滑动时触发
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.e(TAG, "onLongPress")
        //长按时触发
    }
}
