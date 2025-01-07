package com.learning.netflixclone

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data to populate the RecyclerView with
        // data to populate the RecyclerView with
        val animalNames = listOf(
            "Horse", "Cow", "Camel", "Sheep", "Lion", "Elephant",
            "Tiger", "Giraffe", "Panda", "Dolphin", "Penguin",
            "Kangaroo", "Cheetah", "Gorilla", "Koala", "Polar Bear",
            "Zebra", "Octopus", "Owl", "Rhino", "Platypus",
            "Chimpanzee", "Fox"
        )

        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvAnimals)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyRecyclerViewAdapter(this, animalNames)
        recyclerView.adapter = adapter

        val textView = findViewById<TextView>(R.id.textview)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var previousScrollY = 0.0f
            var initialScrollViewPosition = textView.y
            var shouldInitialize = true
            var clipHeight = 0f // Dynamic height for clipping
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val isScrollingUp = dy > previousScrollY
                val isVisible = textView.y >= 0.0f
                val beforeInitialPosition = textView.y < initialScrollViewPosition
                if (isScrollingUp && isVisible) {
                    if (shouldInitialize) {
                        initialScrollViewPosition = textView.y
                        shouldInitialize = false
                    }
                    textView.y = textView.y - dy
                } else if (!isScrollingUp) {
                    if (beforeInitialPosition) {
                        textView.y = textView.y - dy
                    } else {
                        if (!shouldInitialize) {
                            textView.y = initialScrollViewPosition
                        }
                    }
                }
                if (dy == 0) {
                    textView.post {
                        textView.clipBounds = android.graphics.Rect(
                            0, 0, textView.width, textView.height
                        )
                    }
                    clipHeight = 0.toFloat()
                } else if (dy > 0) { // Scrolling up
                    textView.post {
                        if (textView.height > clipHeight) {
                                clipHeight += dy
                                textView.clipBounds = android.graphics.Rect(
                                    0, clipHeight.toInt(), textView.width, textView.height
                                )
                        }
                    }
                } else {
                    if(isVisible){
                        if(clipHeight>=0){
                            textView.clipBounds = android.graphics.Rect(
                                0,clipHeight.toInt() , textView.width, textView.height
                            )
                            clipHeight += dy
                        }
                    }
                }
            }
        })
    }
}