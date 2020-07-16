package com.estefayjuanma.ilfornoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.google.firebase.auth.FirebaseAuth
import kotlin.concurrent.timerTask
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)


        val timer = Timer()
        timer.schedule(timerTask {
            goToMainActivity()
        }, 2000)
    }


    private fun goToMainActivity() {
        var intent = Intent()
        if (FirebaseAuth.getInstance().currentUser != null){
            intent = Intent(this, DrawerActivity::class.java)
        }else{
            intent = Intent(this,LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
