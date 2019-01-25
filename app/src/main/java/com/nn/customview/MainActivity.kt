package com.nn.customview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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

}
