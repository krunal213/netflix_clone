package com.learning.netflixclone

import androidx.recyclerview.widget.RecyclerView

abstract class OnScrollListener : RecyclerView.OnScrollListener() {

    private var previousScrollY = 0.0f
    private var initialScrollViewPosition = 0f
    private var shouldInitialize = true
    private var clipHeight = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val isScrollingUp = dy > previousScrollY
        val isVisible = getY() >= 0.0f
        val beforeInitialPosition = getY() < initialScrollViewPosition
        if (isScrollingUp && isVisible) {
            if (shouldInitialize) {
                initialScrollViewPosition = getY()
                shouldInitialize = false
            }
            scrolledY(getY() - dy)
        } else if (!isScrollingUp) {
            if (beforeInitialPosition) {
                scrolledY(getY() - dy)
            } else {
                if (!shouldInitialize) {
                    scrolledY(initialScrollViewPosition)
                }
            }
        }
        if (dy == 0) {
            clipHeight = 0
            setClipBounds(clipHeight)
        } else if (dy > 0) {
            clipHeight = clipHeight.plus(dy).coerceIn(0, getHeight())
            setClipBounds(clipHeight)
        } else {
            if (isVisible) {
                if (clipHeight >= 0) {
                    clipHeight = clipHeight.plus(dy).coerceIn(0, getHeight())
                    setClipBounds(clipHeight)
                }
            }
        }
    }

    abstract fun setClipBounds(clipHeight: Int)

    abstract fun scrolledY(Y: Float)

    abstract fun getY(): Float

    abstract fun getHeight(): Int

}