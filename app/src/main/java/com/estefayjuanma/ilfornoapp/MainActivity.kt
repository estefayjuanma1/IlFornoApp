package com.estefayjuanma.ilfornoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var correo = ""
    var contra = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //obtener correo y contra de registro o login
        var datos= intent.extras
        if(datos != null) {
            correo = datos?.getString("correo").toString()
            contra = datos?.getString("contrasena").toString()
            tv_correo.text = correo
            tv_contra.text = contra
        }
        else{
            Toast.makeText(this, "Los datos ingresados no coinciden", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (data != null) {
            correo = data?.extras?.getString("correo").toString()
            contra = data?.extras?.getString("contrasena").toString()

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //configurar menu
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_cerrar) {

            var intentcerrar = Intent(this, LoginActivity::class.java)
            intentcerrar.putExtra("correo", correo)
            intentcerrar.putExtra("contrasena", contra)
            startActivity(intentcerrar)
            finish()

        }
        return super.onOptionsItemSelected(item)
    }
}