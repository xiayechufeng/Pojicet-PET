package com.example.pet.activity

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.pet.R
import com.xuexiang.xui.widget.imageview.RadiusImageView

class PetShowPage : AppCompatActivity() {

    @BindView(R.id.pet_image) lateinit var pet_image : RadiusImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_show_page)
        ButterKnife.bind(this)
        val actionBar = actionBar
        val intent = intent

        intent.getParcelableExtra<Bitmap>("bitmap")

        if (actionBar !== null){
            actionBar.setTitle(R.string.app_name)
        }

    }
}