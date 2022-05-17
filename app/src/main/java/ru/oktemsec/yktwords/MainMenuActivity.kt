package ru.oktemsec.yktwords

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //Объекты кнопок категорий (ImageView)
        val catDigitalButton: ImageView = findViewById(R.id.cat_digitals_button)

        catDigitalButton.setOnClickListener {
            clickAnimation(it, 1.1f, 100)
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