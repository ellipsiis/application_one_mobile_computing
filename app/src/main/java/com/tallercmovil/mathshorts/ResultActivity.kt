package com.tallercmovil.mathshorts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class ResultActivity : AppCompatActivity() {
//    fields
    private lateinit var tvResultShow : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvResultShow = findViewById(R.id.tvResult)
        val resShow : String = intent.getStringExtra("res").toString()
        tvResultShow.text = resShow

        val actionBar = supportActionBar
        actionBar!!.title = "Result of Math Short"
        actionBar.setDisplayHomeAsUpEnabled(true)

    }


}