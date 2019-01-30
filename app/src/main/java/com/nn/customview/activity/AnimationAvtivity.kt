package com.nn.customview.activity

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nn.customview.R
import kotlinx.android.synthetic.main.activity_animation_avtivity.*
import org.jetbrains.anko.backgroundColor

class AnimationAvtivity : AppCompatActivity() {

    //属性动画
    var mValueAnimation: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_avtivity)
        mValueAnimation = ValueAnimator.ofInt(Color.RED, Color.BLUE)
        mValueAnimation?.duration = 1000
        mValueAnimation?.addUpdateListener {
            tv_value_anim.backgroundColor = mValueAnimation?.animatedValue as Int
        }
        btn_start.setOnClickListener {
            mValueAnimation?.start()
        }
        btn_stop.setOnClickListener {
            mValueAnimation?.pause()
        }
    }
}
