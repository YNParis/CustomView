/**
 * FileName: CustomView
 * Author: Administrator
 * Date: 2019/1/23 16:06
 * Description: A custom view by overwrite ondraw method.
 */
package com.nn.customview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.nn.customview.R


/**
 * Created by Administrator on 2019/1/23.
 */
class DrawView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var paint = Paint()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //画正圆
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f
        paint.color = Color.BLACK
        //canvas按顺序绘制
        canvas?.drawCircle(300f, 300f, 200f, paint)
        paint.color = Color.parseColor("#88880000")
        paint.strokeWidth = 10f
        //画线
        canvas?.drawLine(300f, 300f, 500f, 600f, paint)
        //画弧形
        canvas?.drawArc(300f, 900f, 600f, 1000f, 30f, 280f, false, paint)
        //画扇形，整体是正圆或椭圆
        canvas?.drawArc(300f, 600f, 600f, 800f, 30f, 280f, true, paint)
        //用颜色填充整个区域
        //canvas?.drawARGB(44,23,67,43)
        //用颜色填充整个区域
        canvas?.drawColor(Color.parseColor("#88009900"))
        paint.color = Color.RED
        paint.strokeWidth = 5f
        //画方形的点
        paint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawPoint(600f, 300f, paint)
        //画方形的点，没看懂有啥区别，好像是会小一圈
        paint.strokeCap = Paint.Cap.BUTT
        canvas?.drawPoint(660f, 300f, paint)
        //画圆形的点
        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawPoint(720f, 300f, paint)
        //画椭圆
        canvas?.drawOval(RectF(100f, 900f, 300f, 1000f), paint)
        //一组点，里面按顺序，每两个数字代表一个点的位置，每两个点确定一条线。下面这个组有28个数字，确定14个点，7条线。
        val points = floatArrayOf(20f, 20f, 120f, 20f, 70f, 20f, 70f, 120f, 20f, 120f, 120f, 120f, 150f, 20f, 250f, 20f, 150f, 20f, 150f, 120f, 250f, 20f, 250f, 120f, 150f, 120f, 250f, 120f)
        //画一组线
        canvas?.drawLines(points, paint)
        //画矩形
        canvas?.drawRect(RectF(20f, 200f, 80f, 240f), paint)
        //画圆角矩形
        canvas?.drawRoundRect(RectF(20f, 250f, 280f, 490f), 50f, 50f, paint)


        //画复杂图形，画路径
        var path = Path()
        path.addArc(200f, 200f, 400f, 400f, -225f, 225f)//从-225度开始，顺时针旋转225度，水平向右是0度
        path.arcTo(400f, 200f, 600f, 400f, -180f, 225f, false)//设置false可以把中间断了的路径连起来
        path.lineTo(400f, 542f)
        path.close()
        canvas?.drawPath(path, paint)
        val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher) // 间接调用 BitmapFactory.decodeStream
        canvas?.drawBitmap(bitmap, 100f, 600f, paint)

        paint.textSize = 50f
        paint.color=Color.BLUE
        canvas?.drawText("Hello",100f,700f,paint)

        
    }

}