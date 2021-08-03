package com.example.pet.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import com.example.pet.R
import com.xuexiang.xui.widget.imageview.RadiusImageView

class PetShowPage : AppCompatActivity() {

    @BindView(R.id.pet_image) lateinit var petImage : RadiusImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_show_page)
        ButterKnife.bind(this)
        val actionBar = actionBar
        val intent = intent



        // TODO: 2021/8/3 传输方式修改 ,MainActivity也要相应修改
        val imageId = intent.getIntExtra("image",0)
        petImage.setImageResource(imageId)

    }

    override fun onDestroy() {
        finish()
        super.onDestroy()
    }
}