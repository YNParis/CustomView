package com.nn.customview.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nn.customview.R
import kotlinx.android.synthetic.main.activity_animation_avtivity.*
import org.jetbrains.anko.backgroundColor

class AnimationActivity : AppCompatActivity() {

    //属性动画
    private var mValueAnimation: ValueAnimator? = null

    //AnimatorSet
    private var mAnimSet: AnimatorSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_avtivity)
        initView()
        initValueAnimation()
        initAnimatorSet()
    }

    private fun initView() {
        btn_start.setOnClickListener {
            mAnimSet?.start()
        }
        btn_stop.setOnClickListener {

        }
    }

    private fun initValueAnimation() {
        mValueAnimation = ValueAnimator.ofInt(Color.RED, Color.BLUE)
        mValueAnimation?.duration = 1000
        mValueAnimation?.addUpdateListener {
            tv_value_anim.backgroundColor = mValueAnimation?.animatedValue as Int
        }
    }

    private fun initAnimatorSet() {
        val anim1 = ObjectAnimator.ofFloat(tv_value_anim, "translationX", 0f, 400f, 0f)
        val anim2 = ObjectAnimator.ofFloat(tv_value_anim, "translationY", 0f, 400f, 0f)
        anim1.startDelay = 2000
        anim2.startDelay = 2000
        mAnimSet = AnimatorSet()
        //play...with...就是说在位于前面的那个动画开始后，所有的才开始，如果前面的那个动画有延迟2000ms，过了2000ms以后开始所有动画
        mAnimSet?.play(anim1)?.with(anim2)//因为anim1有延迟，所以在anim1开始后，anim2经过自己的delay时间后再开始
        mAnimSet?.play(anim2)?.with(anim1)
        mAnimSet?.duration = 5000
    }

    override fun onDestroy() {
        super.onDestroy()
        mAnimSet?.cancel()
        mValueAnimation?.cancel()
    }
}
