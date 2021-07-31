package com.example.pet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }
}