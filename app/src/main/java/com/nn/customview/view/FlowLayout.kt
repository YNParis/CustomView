package com.nn.customview.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * TODO: document your custom view class.
 */
class FlowLayout : ViewGroup {

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var top = 0
        var left = 0
        var lineHeight = 0
        var lineWidth = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as MarginLayoutParams
            val wc = child.measuredWidth + lp.rightMargin + lp.leftMargin
            val hc = child.measuredHeight + lp.bottomMargin + lp.topMargin
            if (wc + lineWidth > measuredWidth) {
                //换行
                top += lineHeight
                left = 0
                lineHeight = hc
                lineWidth = wc
            } else {
                lineHeight = maxOf(hc, lineHeight)
                lineWidth += wc
            }
            child.layout(left, top, left + wc, top + hc)
            //将left移到行末，或者下一个控件的起点
//            left += wc//下一个控件的起点
            left = lineWidth//行末
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMeasure = MeasureSpec.getSize(widthMeasureSpec)
        val widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMeasure = MeasureSpec.getSize(heightMeasureSpec)
        val heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec)

        var height = 0
        var width = 0
        var lineHeight = 0
        var lineWidth = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val lp = child.layoutParams as MarginLayoutParams
            val wc = child.measuredWidth + lp.rightMargin + lp.leftMargin
            val hc = child.measuredHeight + lp.bottomMargin + lp.topMargin
            if (wc + lineWidth > widthMeasure) {
                //需要换行
                height += lineHeight
                width = maxOf(width, lineWidth)
                lineWidth = wc
                lineHeight = hc
            } else {
                lineHeight = maxOf(hc, lineHeight)
                lineWidth += wc
            }

            if (i == childCount - 1) {
                height += lineHeight
                width = maxOf(width, lineHeight)
            }

        }

        setMeasuredDimension(if (widthMeasureMode == MeasureSpec.EXACTLY) widthMeasure else width, if (heightMeasureMode == MeasureSpec.EXACTLY) heightMeasure else height)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }
    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)


}
