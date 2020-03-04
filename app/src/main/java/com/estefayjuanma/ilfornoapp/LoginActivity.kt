package com.estefayjuanma.ilfornoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var contraingresada=""
    var correoingresado=""

    var correor=""
    var contrar=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Registrarse.setOnClickListener() {
            var intent = Intent(this, RegistroActivity::class.java)
            startActivityForResult(intent, 1234)
        }

        inicio.setOnClickListener {
            correoingresado = correolog.text.toString()
            contraingresada = contrasenalog.text.toString()

            if (correoingresado.isEmpty() || contraingresada.isEmpty()) {
                Toast.makeText(this, "Debe digitar todos los campos", Toast.LENGTH_SHORT).show()

            } else {
                if (correor == correoingresado && contrar == contraingresada) {
                    var intentmain = Intent(this, DrawerActivity::class.java)
                    intentmain.putExtra("correo", correor)
                    intentmain.putExtra("contrasena", contrar)
                    startActivity(intentmain)
                    finish()
                }
                else if (correoingresado != correor || contrar != contraingresada) {
                    Toast.makeText(this, "Datos no coinciden", Toast.LENGTH_SHORT).show()
                }
            }
        }

        var datosCerrar= intent.extras
        if(datosCerrar!= null){
            correor= datosCerrar?.getString("correo").toString()
            contrar= datosCerrar?.getString("contrasena").toString()
        }
        else
            Toast.makeText(this, "Debe registrarse primero", Toast.LENGTH_SHORT).show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       // if (requestCode == 1234 && resultCode == Activity.RESULT_CANCELED)
       //     Toast.makeText(this, "Registro no exitoso, los datos no coinciden", Toast.LENGTH_SHORT).show()

        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, data?.extras?.getString("correo"), Toast.LENGTH_SHORT).show()
            super.onActivityResult(requestCode, resultCode, data)
            //var datosRecibidos = intent.extras
            if (data != null) {
                correor= data?.extras?.getString("correo").toString()
                contrar= data?.extras?.getString("contrasena").toString()

            }
        }else if(requestCode == 1234){
            Toast.makeText(this, "Registro no exitoso, los datos no coinciden", Toast.LENGTH_SHORT).show()
        }


    }
}

