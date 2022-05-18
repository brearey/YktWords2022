package ru.oktemsec.yktwords

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.Toast

class MainMenuActivity : AppCompatActivity() {

    lateinit var mp: MediaPlayer
    // флажок звука
    var toggleMusc:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //Фоновая музыка включается при старте приложения
        mp = MediaPlayer.create(this, R.raw.stars)
        mp.setVolume(0.15f, 0.15f)
        mp.isLooping = true
        mp.start()

        //Объекты кнопок категорий (ImageView)
        val catDigitalButton: ImageView = findViewById(R.id.cat_digitals_button)
        val catAnimalsButton: ImageView = findViewById(R.id.cat_animals_button)

        catDigitalButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            val intent = Intent(this, DigitalsActivity::class.java).apply {
                putExtra("ru.oktemsec.yktwords.MESSAGE", "my message")
            }
            startActivity(intent)
        }

        catAnimalsButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            val intent = Intent(this, AnimalsActivity::class.java).apply {
                putExtra("ru.oktemsec.yktwords.MESSAGE", "my message")
            }
            startActivity(intent)
        }

        // Функциональные кнопки внизу
        val toggleMusicButton:ImageView = findViewById(R.id.toggle_music)
        val buttonHome:ImageView = findViewById(R.id.button_home)
        val toggleSoundButton:ImageView = findViewById(R.id.toggle_sound)

        // Нажатие функциональных кнопок
        // Пауза музыки
        toggleMusicButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            if (toggleMusc) {
                mp.pause()
                toggleMusc = false
            } else {
                mp.start()
                toggleMusc = true
            }
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


    fun clickAnimation(v:View, value:Float, _duration:Long) {
        val valAnimator = ValueAnimator.ofFloat(1f, value)
        valAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            v.scaleX = value
            v.scaleY = value
        }
        valAnimator.interpolator = LinearInterpolator()
        valAnimator.duration = _duration
        valAnimator.repeatCount = 1
        valAnimator.repeatMode = ValueAnimator.REVERSE
        valAnimator.start()
    }
}