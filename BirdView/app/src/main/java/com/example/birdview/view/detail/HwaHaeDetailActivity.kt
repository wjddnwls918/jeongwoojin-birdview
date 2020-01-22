package com.example.birdview.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import com.example.birdview.R

class HwaHaeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //뒷배경 흐리게 하기
        WindowManager.LayoutParams().apply {
            flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            dimAmount = 0.0f
            window.attributes = this
        }

        setContentView(R.layout.activity_hwa_hae_detail)

        //사이즈 조절
        val dm:DisplayMetrics = applicationContext.resources.displayMetrics
        val width:Int = (dm.widthPixels)
        val height:Int = (dm.heightPixels*0.9).toInt()
        window.attributes.width = width
        window.attributes.height = height




    }
}
