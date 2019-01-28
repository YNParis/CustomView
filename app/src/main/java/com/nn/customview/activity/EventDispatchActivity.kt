package com.nn.customview.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import com.nn.customview.R
import kotlinx.android.synthetic.main.activity_event_dispatch.*

class EventDispatchActivity : AppCompatActivity() {

    val TAG = "EventDispatchActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_dispatch)

        btn_event_dispatch.setOnClickListener {
            Log.e(TAG, "btn_event_dispatch is onclicked")
        }
        btn_event_dispatch.setOnTouchListener { v, event ->
            printLog("button",event)
            true
        }
        img_event_dispatch.setOnClickListener {
            Log.e(TAG, "img_event_dispatch is onclicked")
        }
        img_event_dispatch.setOnTouchListener { v, event ->
            printLog("imageview",event)
            true
        }

    }

    private fun printLog(name: String, event: MotionEvent) {
        //button点击时，先执行ACTION_DOWN，中间如果之间发生移动，执行ACTION_MOVE，手指离开屏幕时，执行ACTION_UP
        Log.e(TAG, "$name event is" + when (event.action) {
            MotionEvent.ACTION_UP ->
                "ACTION_UP"
            MotionEvent.ACTION_DOWN ->
                "ACTION_DOWN"
            MotionEvent.ACTION_MOVE ->
                "ACTION_MOVE"
            else -> "else motion"
        })
    }


}
