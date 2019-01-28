/**
 * FileName: CustomLinearLayout
 * Author: Administrator
 * Date: 2019/1/28 14:04
 * Description: CustomLinearLayout
 */
package com.nn.customview.view

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import com.nn.customview.R

/**
 * Created by Administrator on 2019/1/28.
 */
class CustomLinearLayout : ViewGroup {

    var mPaint: Paint? = null

    //重写generateLayoutParams方法和generateDefaultLayoutParams方法，处理子控件中的margin
    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //TODO 需要重写该方法
        var top = 0
        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            //处理子控件的margin
            val lp = child.layoutParams as MarginLayoutParams
            val childWidth = child.measuredWidth + lp.leftMargin + lp.rightMargin
            val childHeight = child.measuredHeight + lp.topMargin + lp.bottomMargin
            child.layout(0, top, childWidth, top + childHeight)
            top += childHeight
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获得系统建议的数值和模式
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        val measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)

        var width = 0
        var height = 0

        var count = childCount

        for (i in 0 until count) {
            val child = getChildAt(i)

            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            //处理子控件的margin
            // 在调用getMeasuredWidth方法必须在measureChild方法之后。
            val lp = child.layoutParams as MarginLayoutParams
            val w = child.measuredWidth + lp.leftMargin + lp.rightMargin
            val h = child.measuredHeight + lp.topMargin + lp.bottomMargin

            height += h
            width = maxOf(w, width)
        }

        setMeasuredDimension(if (measureWidthMode == MeasureSpec.EXACTLY) measureWidth else width, if (measureHeightMode == MeasureSpec.EXACTLY) measureHeight else height)
    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        init(attr, 0)
    }

    /**
     *
     */
    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(context, attr, defStyle) {
        init(attr, defStyle)
    }

    fun init(attr: AttributeSet?, defStyle: Int) {
        mPaint = Paint()
        val a = context.obtainStyledAttributes(R.styleable.CustomLinearLayout)
        val bgcolor = a.getColor(R.styleable.CustomLinearLayout_bgcolor, 656565)
        mPaint?.color = bgcolor
        a.recycle()
    }

}