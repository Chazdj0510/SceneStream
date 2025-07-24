// This file is completed
package com.example.scenestream

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logo)
        val appName = findViewById<TextView>(R.id.appName)

        // Load animations from XML (optional, or use animate() programmatically)
        val fadeScale = AnimationUtils.loadAnimation(this, R.anim.fade_scale)
        val fadeInText = AnimationUtils.loadAnimation(this, R.anim.fade_in_text)

        logo.startAnimation(fadeScale)
        appName.startAnimation(fadeInText)

        // Play sound
        mediaPlayer = MediaPlayer.create(this, R.raw.swish)
        mediaPlayer?.start()

        // Transition to LoginActivity after delay
        Handler(Looper.getMainLooper()).postDelayed({
            mediaPlayer?.release()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2500)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }

}