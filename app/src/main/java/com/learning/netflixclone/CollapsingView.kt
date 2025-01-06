package com.learning.netflixclone

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class CollapsingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var initialY = 0f
    private var currentY = 0f
    private val maxHeight = 200f // Maximum height when fully expanded
    private val minHeight = 100f // Minimum height when collapsed
    private val clipBounds = Rect()

    init {
        setWillNotDraw(false) // Ensure this view will be drawn
        setClipToPadding(false) // Disable padding clipping, just use clipBounds
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> initialY = event.y
            MotionEvent.ACTION_MOVE -> {
                currentY = event.y
                val deltaY = initialY - currentY
                adjustViewHeight(deltaY)
                initialY = currentY
            }
            else -> return super.onTouchEvent(event)
        }
        return true
    }

    private fun adjustViewHeight(deltaY: Float) {
        var newHeight = height - deltaY
        if (newHeight > maxHeight) {
            newHeight = maxHeight
        } else if (newHeight < minHeight) {
            newHeight = minHeight
        }

        layoutParams.height = newHeight.toInt()
        requestLayout()

        // Adjust the clipBounds to clip the content as the height changes
        clipBounds.top = 0
        clipBounds.bottom = newHeight.toInt()
        setClipBounds(clipBounds)
        invalidate()
    }

    override fun draw(canvas: android.graphics.Canvas) {
        // Clip the canvas before drawing
        canvas.save()
        canvas.clipRect(clipBounds)
        super.draw(canvas)
        canvas.restore()
    }
}
