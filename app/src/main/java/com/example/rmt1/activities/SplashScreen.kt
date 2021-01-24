package com.example.rmt1.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rmt1.R
import kotlinx.coroutines.*

class SplashScreen : AppCompatActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
//        val mediaPlayer = MediaPlayer.create(this, R.raw.rm_theme_song)
//        playMedia(mediaPlayer)
        activityScope.launch {
            delay(3000)

            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

//    fun playMedia(mediaPlayer: MediaPlayer){
//        if (mediaPlayer != null) {
//            mediaPlayer.start()
//        } else {
//            mediaPlayer.reset()
//            try {
//                mediaPlayer.prepare()
//            } catch (e: IllegalStateException) {
//                e.printStackTrace()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            mediaPlayer.start()
//        }
//
//    }
}