package ru.oktemsec.yktwords

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Фоновая музыка включается при старте приложения
        val mp:MediaPlayer = MediaPlayer.create(this, R.raw.stars)
        mp.setVolume(0.5f, 0.5f)
        mp.isLooping = true
        mp.start()

        //Анимация кнопки старт
        val startButton:ImageView = findViewById(R.id.startButton)
        val scale:Float = 1.15f;
        val durationScale:Long = 1000;
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
    }

    // Кнопка перехода в главное меню
    fun gotoMainMenu(view: View) {
        val intent = Intent(this, MainMenuActivity::class.java).apply {
            putExtra("ru.oktemsec.yktwords.MESSAGE", "my message")
        }
        startActivity(intent)
    }
}