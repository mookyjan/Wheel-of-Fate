package com.mudassirkhan.wheeloffate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mudassirkhan.wheeloffate.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showProgress(isLoading:Boolean) {
        if(isLoading)
        progressMain.visibility = View.VISIBLE
        else
            progressMain.visibility = View.GONE
    }


}
