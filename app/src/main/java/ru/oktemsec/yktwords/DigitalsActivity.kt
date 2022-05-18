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
    lateinit var mpDigitals: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_digitals)

        //Фоновая музыка включается при старте приложения
        mp = MediaPlayer.create(this, R.raw.stars)
        mp.setVolume(0.15f, 0.15f)
        mp.isLooping = true
        mp.start()

        mpDigitals = MediaPlayer.create(this, R.raw.digitals)
        mpDigitals.setVolume(0.15f, 0.15f)
        if (!mpDigitals.isPlaying) { mpDigitals.start() }

        // MediaPlayers of digits
        val mpOne:MediaPlayer = MediaPlayer.create(this, R.raw.one)
        val mpTwo:MediaPlayer = MediaPlayer.create(this, R.raw.two)
        val mpThree:MediaPlayer = MediaPlayer.create(this, R.raw.three)

        // Цифры кнопки
        val oneButton:ImageView = findViewById(R.id.one_image)
        val twoButton:ImageView = findViewById(R.id.two_image)
        val threeButton:ImageView = findViewById(R.id.three_image)

        //Нажатие кнопок
        oneButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpOne.start()
        }

        twoButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpTwo.start()
        }

        threeButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpThree.start()
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