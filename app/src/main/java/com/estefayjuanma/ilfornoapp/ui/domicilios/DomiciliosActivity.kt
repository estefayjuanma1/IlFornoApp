package com.estefayjuanma.ilfornoapp.ui.domicilios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.estefayjuanma.ilfornoapp.R

class DomiciliosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domicilios)

        var datosRecibidos= intent.extras

        if (datosRecibidos != null) {
            Toast.makeText(this, datosRecibidos.getString("total"), Toast.LENGTH_SHORT).show()
        }


    }
}
