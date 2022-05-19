package ru.oktemsec.yktwords

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.Toast

class DigitalsActivity : AppCompatActivity() {

    lateinit var mp: MediaPlayer
    lateinit var mpVoice: MediaPlayer
    // флажок звука
    var toggleSound:Boolean = true
    var toggleMusc:Boolean = true
    // Volume of voice
    var volume:Float = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_digitals)

        //Фоновая музыка включается при старте приложения
        mp = MediaPlayer.create(this, R.raw.stars)
        mp.setVolume(0.15f, 0.15f)
        mp.isLooping = true
        mp.start()

        // Звук в начале "Название категории"
        mpVoice = MediaPlayer.create(this, R.raw.numbers)
        mpVoice.setVolume(volume, volume)
        if (!mpVoice.isPlaying) { mpVoice.start() }

        // Цифры кнопки
        val oneButton:ImageView = findViewById(R.id.one_image)
        val twoButton:ImageView = findViewById(R.id.two_image)
        val threeButton:ImageView = findViewById(R.id.three_image)
        val fourButton:ImageView = findViewById(R.id.four_image)
        val fiveButton:ImageView = findViewById(R.id.five_image)
        val sixButton:ImageView = findViewById(R.id.six_image)
        val sevenButton:ImageView = findViewById(R.id.seven_image)
        val eightButton:ImageView = findViewById(R.id.eight_image)
        val nineButton:ImageView = findViewById(R.id.nine_image)
        val zeroButton:ImageView = findViewById(R.id.zero_image)

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
        // Переход в меню категорий
        buttonHome.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
        // Выключение/включение звука голоса Зины
        toggleSoundButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            if (toggleSound) {
                volume = 0f
                toggleSound = false
            } else {
                volume = 1f
                toggleSound = true
            }
        }

        //Нажатие кнопок цифр
        oneButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.one)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        twoButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.two)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        threeButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.three)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        fourButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.four)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        fiveButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.five)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        sixButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.six)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        sevenButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.seven)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        eightButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.eight)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        nineButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.nine)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        zeroButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.zero)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
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

    // Анимация кнопок при нажатии

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