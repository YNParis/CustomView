package com.nn.customview.activity


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.ImageView
import com.nn.customview.R
import kotlinx.android.synthetic.main.activity_window_manager.*
import org.jetbrains.anko.backgroundResource


class WindowManagerActivity : AppCompatActivity() {

    var mWindleManager: WindowManager? = null
    var mImageView: ImageView? = null
    var mLayoutParams: WindowManager.LayoutParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_manager)
        if (checkPermission(this)) {
            //动态请求权限
            requestPermission(999)
        } else {
            initView()
        }
    }

    fun initView() {
        mWindleManager = this@WindowManagerActivity.windowManager
        mLayoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,//width
                WindowManager.LayoutParams.WRAP_CONTENT,//height
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,//type
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        and WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        and WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                ,//flags
                PixelFormat.TRANSPARENT//format
        )
        mLayoutParams?.gravity = Gravity.TOP and Gravity.START
        mLayoutParams?.width = 100
        mLayoutParams?.height = 100
        mLayoutParams?.x = 0
        mLayoutParams?.y = 0
        //WindowManager.LayoutParams.TYPE_SYSTEM_ALERT已弃用，改成FIRST_SYSTEM_WINDOW + 3可以使用
        //        mLayoutParams?.type = 2099
        mImageView = ImageView(this@WindowManagerActivity)
        mImageView?.backgroundResource = R.mipmap.ic_launcher
        mImageView?.setOnTouchListener { v, event ->
            move(event)
            true
        }
        btn_add.setOnClickListener { add() }
        btn_remove.setOnClickListener { remove() }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                999 -> {
                    Log.e("windowmanageractivity", "动态获取权限成功")
                    initView()
                }
                888 -> add()
            }
        }
    }


    fun remove() {
        mWindleManager?.removeViewImmediate(mImageView)
        false
    }


    fun add() {
        if (checkPermission(this)) {
            mWindleManager?.addView(mImageView, mLayoutParams)
        } else {
            requestPermission(888)
        }
    }

    fun checkPermission(activity: Context): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(activity)
        }
        return true
    }

    fun requestPermission(requestCode: Int) {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + packageName))

        this.startActivityForResult(intent, requestCode)
    }

    private fun move(event: MotionEvent) {
        mLayoutParams?.x = event.rawX.toInt()
        mLayoutParams?.y = event.rawY.toInt()
        mWindleManager?.updateViewLayout(mImageView, mLayoutParams)

    }
}
