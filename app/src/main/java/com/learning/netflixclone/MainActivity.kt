package com.learning.netflixclone

import android.graphics.Rect
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
        recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun setClipBounds(clipHeight: Int) {
                textView.post {
                    textView.clipBounds = Rect(
                        0, clipHeight, textView.width, textView.height
                    )
                }
            }

            override fun scrolledY(Y: Float) {
                textView.y = Y
            }

            override fun getY() = textView.y

            override fun getHeight() = textView.height

        })

    }
}