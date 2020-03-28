package com.pandiandcode.cardsagainsthumanity.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pandiandcode.cardsagainsthumanity.R

class BlackCardActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_black_card)
    }

    companion object{
        fun intent(context: Context): Intent = Intent(context, BlackCardActivity::class.java)
    }
}