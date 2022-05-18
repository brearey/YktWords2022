package ru.oktemsec.yktwords

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class DigitalsActivity : AppCompatActivity() {

    lateinit var mp: MediaPlayer
    lateinit var mpVoice: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_digitals)

        //Фоновая музыка включается при старте приложения
        mp = MediaPlayer.create(this, R.raw.stars)
        mp.setVolume(0.15f, 0.15f)
        mp.isLooping = true
        mp.start()

        // Сыыппаралар
        mpVoice = MediaPlayer.create(this, R.raw.digitals)
        mpVoice.setVolume(0.15f, 0.15f)
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

        //Нажатие кнопок
        oneButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.one)
            mpVoice.start()
        }

        twoButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.two)
            mpVoice.start()
        }

        threeButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.three)
            mpVoice.start()
        }

        fourButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.four)
            mpVoice.start()
        }

        fiveButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.five)
            mpVoice.start()
        }

        sixButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.six)
            mpVoice.start()
        }

        sevenButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.seven)
            mpVoice.start()
        }

        eightButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.eight)
            mpVoice.start()
        }

        nineButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.nine)
            mpVoice.start()
        }

        zeroButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.zero)
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
    fun clickAnimation(v: View, value:Float, _duration:Long) {
        ObjectAnimator.ofFloat(v, "scaleX", value).apply {
            duration = _duration
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(v, "scaleY", value).apply {
            duration = _duration
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }

}