package com.example.day4_scalableimage

import android.annotation.SuppressLint
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import kotlin.math.abs
import kotlin.math.sqrt

// Implement for Log debug
const val TAG = "DragZoomListener"

@SuppressLint("StaticFieldLeak")
lateinit var imageView: ImageView

// The 4 modes we can be in touching screen
const val NONE = 0
const val DRAG = 1
const val ZOOM = 2
const val CLICK = 3

// Set initial mode to NONE mode
private var mode = NONE

// Ingredients for zooming
private val start = PointF()
private val mid = PointF()
private val startRef = PointF()
private var oldDistance = 1f

class DragZoomListener : View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        imageView = view as ImageView
        when (event.action and MotionEvent.ACTION_MASK) {
            // When puts one finger on imageView
            MotionEvent.ACTION_DOWN -> {
                // Recoding the first touch relative position(相對於imageView的左上角)
                start.set(event.x, event.y)
                // Recoding the first touch absolute position(手機螢幕上的絕對座標)
                startRef.set(
                    imageView.x + event.x,
                    imageView.y + event.y
                )
                Log.d(TAG, "Absolute coordinate: (${startRef.x}, ${startRef.y})")
                // Why not CLICK? CLICK mode is the DRAG mode with movement under critical value
                mode = DRAG
            }
            // When second finger puts on imageView
            MotionEvent.ACTION_POINTER_DOWN -> {
                // Recoding the initial distance between first finger and second finger
                oldDistance = getDistance(event)
                Log.d(TAG, "oldDistance = $oldDistance")
                // Setting critical value(prevent the sensitivity)
                if (oldDistance > 10f) {
                    // Recoding the mid point between two fingers
                    midPoint(mid, event)
                    // Because the distance between two fingers is bigger than the critical value we set,
                    // we set the mode into ZOOM
                    mode = ZOOM
                    Log.d(TAG, "mode=ZOOM: ${imageView.x}, ${imageView.y}")
                }
            }
            // When first finger leaves screen
            MotionEvent.ACTION_UP -> {
                // 觸碰點和放手點的分量
                val dxDiff: Float = (imageView.x + event.x) - startRef.x
                val dyDiff: Float = (imageView.y + event.y) - startRef.y
                // 上述取絕對值並以無條件捨去法取整數
                val xDiff: Int = abs(dxDiff).toInt()
                val yDiff: Int = abs(dyDiff).toInt()
                // Implement CLICK mode
                if ((mode == DRAG) and (xDiff < 2) and (yDiff < 2)) {
                    mode = CLICK
                    Log.d(TAG, "mode=CLICK")
                    imageView.performClick()
                }
            }
            // When second finger leaves screen
            MotionEvent.ACTION_POINTER_UP -> {
                // Don't want to implement drag or zoom after your second finger leave screen
                mode = NONE
                Log.d(TAG, "mode=NONE")
            }
            // Finger or fingers move when touching screen
            MotionEvent.ACTION_MOVE -> {
                when (mode) {
                    DRAG -> {
                        // Change the left corner coordinate and keep the size of imageView when move
                        // 後面的imageView.scale之所以要乘上是因為要實現放大後還能夠維持放大後的size來移動
                        imageView.x += (event.x - start.x) * (imageView.scaleX)
                        imageView.y += (event.y - start.y) * (imageView.scaleY)
                    }
                    ZOOM -> {
                        val newDistance = getDistance(event)
                        Log.d(TAG, "newDistance=$newDistance")
                        if (newDistance > 10) {
                            val scale = newDistance / oldDistance
                            imageView.scaleX *= scale
                            imageView.scaleY *= scale
                        }
                    }
                }
            }
        }
        return true
    }

    // Determine the distance between the first two fingers
    private fun getDistance(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt((x * x) + (y * y))
    }

    // Calculate the mid point of the first two fingers
    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        point.set(x / 2, y / 2)
    }
}