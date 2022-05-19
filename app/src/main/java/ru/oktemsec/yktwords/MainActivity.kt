package ru.oktemsec.yktwords

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var mp:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Фоновая музыка включается при старте приложения
        mp = MediaPlayer.create(this, R.raw.stars)
        mp.setVolume(0.4f, 0.4f)
        mp.isLooping = true
        mp.start()

        //Анимация кнопки старт
        val startButton:ImageView = findViewById(R.id.startButton)
        val scale = 1.15f
        val durationScale:Long = 1000
        ObjectAnimator.ofFloat(startButton, "scaleX", scale).apply {
            duration = durationScale
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(startButton, "scaleY", scale).apply {
            duration = durationScale
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        // Анимация детей
        val kids:ImageView = findViewById(R.id.kids)
        val translationX = 20f
        val durationTranslationX:Long = 3000
        ObjectAnimator.ofFloat(kids, "translationX", translationX).apply {
            duration = durationTranslationX
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (mp.isPlaying) { mp.pause() }
    }

    override fun onResume() {
        super.onResume()
        if (!mp.isPlaying) {
            mp.start()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mp.isPlaying) {
            try {
                mp.stop()
                mp.prepare()
                mp.seekTo(0)
            }
            catch (t:Throwable) {
                Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mp.isPlaying) {
            try {
                mp.stop()
                mp.prepare()
                mp.seekTo(0)
            }
            catch (t:Throwable) {
                Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Кнопка перехода в главное меню
    fun gotoMainMenu(view:View) {
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
    }
}