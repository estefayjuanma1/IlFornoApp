package com.estefayjuanma.ilfornoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        bt_registrar.setOnClickListener {
            var nombre = nombre.text.toString()
            var correo = correo.text.toString()
            var contra = contrasena.text.toString()
            var concontra = repcontrasena.text.toString()
            var intent = Intent()

            if (contra != concontra) {
                Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show()
            }
            else {
                if (nombre.isEmpty() || correo.isEmpty() || contra.isEmpty() || concontra.isEmpty()) {
                    Toast.makeText(this, "Debe digitar todos los campos", Toast.LENGTH_SHORT).show()
                }
                else{
                    intent.putExtra("correo", correo)
                    intent.putExtra("contrasena", contra)
                    setResult(Activity.RESULT_OK, intent)
                }
            }
            finish()
        }
    }

    override fun onBackPressed() {  //podria enviarle un emnsaje al usuario diciendo que no se guardo correctamente
        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }
}
