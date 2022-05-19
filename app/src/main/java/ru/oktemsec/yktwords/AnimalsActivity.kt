package ru.oktemsec.yktwords

import android.animation.ValueAnimator
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.Toast

class AnimalsActivity : AppCompatActivity() {

    lateinit var mp: MediaPlayer
    lateinit var mpVoice: MediaPlayer
    // флажок звука
    var toggleSound:Boolean = true
    var toggleMusc:Boolean = true
    // Volume of voice
    var volume:Float = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animals)

        //Фоновая музыка включается при старте приложения
        mp = MediaPlayer.create(this, R.raw.stars)
        mp.setVolume(0.15f, 0.15f)
        mp.isLooping = true
        mp.start()

        // Звук в начале "Название категории"
        mpVoice = MediaPlayer.create(this, R.raw.animals)
        mpVoice.setVolume(volume, volume)
        if (!mpVoice.isPlaying) { mpVoice.start() }

        // Цифры кнопки
        val foxButton: ImageView = findViewById(R.id.fox_image)
        val kurochkaButton: ImageView = findViewById(R.id.kurochka_image)
        val koalaButton: ImageView = findViewById(R.id.koala_image)
        val krabButton: ImageView = findViewById(R.id.krab_image)
        val kitButton: ImageView = findViewById(R.id.kit_image)
        val ezikButton: ImageView = findViewById(R.id.ezik_image)
        val tigerButton: ImageView = findViewById(R.id.tiger_image)
        val pigButton: ImageView = findViewById(R.id.pig_image)
        val cowButton: ImageView = findViewById(R.id.cow_image)

        // Функциональные кнопки внизу
        val toggleMusicButton: ImageView = findViewById(R.id.toggle_music)
        val buttonHome: ImageView = findViewById(R.id.button_home)
        val toggleSoundButton: ImageView = findViewById(R.id.toggle_sound)

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
        foxButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.fox)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        kurochkaButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.chiken)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        koalaButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.koala)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        krabButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.krab)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        kitButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.kit)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        ezikButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.ezik)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        tigerButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.tiger)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        pigButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.pig)
            mpVoice.setVolume(volume, volume)
            mpVoice.start()
        }

        cowButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
            mpVoice.reset()
            mpVoice = MediaPlayer.create(this, R.raw.korova)
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

    fun clickAnimation(v: View, value:Float, _duration:Long) {
        val valAnimator = ValueAnimator.ofFloat(1f, value)
        valAnimator.addUpdateListener {
            val myValue = it.animatedValue as Float
            v.scaleX = myValue
            v.scaleY = myValue
        }
        valAnimator.interpolator = LinearInterpolator()
        valAnimator.duration = _duration
        valAnimator.repeatCount = 1
        valAnimator.repeatMode = ValueAnimator.REVERSE
        valAnimator.start()
    }

}