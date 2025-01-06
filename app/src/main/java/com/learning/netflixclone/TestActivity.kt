package com.learning.netflixclone

import android.graphics.Rect
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        var top = 0
        val textView = findViewById<TextView>(R.id.textView)
        var textViewHeight = 0
        textView.post {
            textViewHeight = textView.height
            top = textView.height
            textView.clipBounds = Rect(0,top.minus(20),textView.width,textViewHeight)
        }

        /*findViewById<Button>(R.id.buttonPositive).setOnClickListener {
            textView.clipBounds = Rect(0,top,textView.width,textView.height)
            top++
        }

        findViewById<Button>(R.id.buttonNegative).setOnClickListener {
            textView.clipBounds = Rect(0,top,textView.width,textView.height)
            top++
        }*/
    }
}