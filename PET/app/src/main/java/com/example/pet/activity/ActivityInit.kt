package com.example.pet.activity

import android.app.Activity
import android.os.Bundle
import com.xuexiang.xui.XUI

class ActivityInit : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        XUI.initTheme(this)
        super.onCreate(savedInstanceState)
    }
}