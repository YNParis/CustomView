package com.nn.customview.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.nn.customview.R
import org.jetbrains.anko.startActivity

/**
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toEventDispatch(v: View) {
        startActivity<EventDispatchActivity>()
    }

    fun toCanvasPaint(v: View) {
        startActivity<CanvasPaintActivity>()
    }

    fun toWidgetEncapsulation(v: View) {
        startActivity<WidgetEncapsulationActivity>()
    }

    fun toGesture(v: View) {
        startActivity<GestureActivity>()
    }

    fun toWindowManager(v: View) {
        startActivity<WindowManagerActivity>()
    }

    fun toAnimation(v: View) {
        startActivity<AnimationActivity>()
    }

}
