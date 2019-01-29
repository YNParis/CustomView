package com.nn.customview.activity

import android.app.Activity
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
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
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this@WindowManagerActivity)) {
                //动态请求权限
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                startActivityForResult(intent, 999)
            } else {
                initView()
            }
        } else {

            initView()
        }

    }

    fun initView() {
        mWindleManager = this@WindowManagerActivity.windowManager
        mLayoutParams = WindowManager.LayoutParams(
                100,
                100,
                TYPE_APPLICATION_OVERLAY,
                FLAG_NOT_FOCUSABLE
                        or FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSPARENT)
        mImageView = ImageView(this@WindowManagerActivity)
        mImageView?.backgroundResource = R.mipmap.ic_launcher
        mImageView?.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                move(event)
            }
            false
        }

        btn_add.setOnClickListener { add() }
        btn_remove.setOnClickListener { remove() }

    }

    fun move(event: MotionEvent) {
        event.rawX
        event.rawY


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 999) {
            Log.e("windowmanageractivity", "动态获取权限成功")
        }
    }

    fun add() {
        mWindleManager?.addView(mImageView, mLayoutParams)
    }

    fun remove() {
        mWindleManager?.removeViewImmediate(mImageView)

    }
}
