package com.example.mythirdapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.animation.AnimationUtils

class SplashScreen : AppCompatActivity() {

    private val splashScreenStarted = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logo = findViewById<ImageView>(R.id.logo)

        Handler().postDelayed({
            val intent = Intent (this@SplashScreen, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },splashScreenStarted.toLong())
    }
}