package ru.oktemsec.yktwords

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Фоновая музыка включается при старте приложения
        val mp:MediaPlayer = MediaPlayer.create(this, R.raw.go_sound)
        if (!mp.isPlaying) {
            mp.start()
        }
        else {
            mp.stop()
        }
    }
}