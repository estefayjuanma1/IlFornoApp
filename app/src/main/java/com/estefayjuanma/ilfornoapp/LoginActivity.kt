package com.estefayjuanma.ilfornoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var contraingresada=""
    var correoingresado=""
    var correor=""
    var contrar=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        olvidecontra.setOnClickListener {
            var intent = Intent(this, OlvidecontraActivity::class.java)
            startActivityForResult(intent, 12348)
        }

        registrarse.setOnClickListener {
            var intent = Intent(this, RegistroActivity::class.java)
            startActivityForResult(intent, 1234)
        }

        inicio.setOnClickListener {
            correoingresado = correolog.text.toString()
            contraingresada = contrasenalog.text.toString()

            val auth = FirebaseAuth.getInstance()  //var global para la autenticacion

            if (correoingresado.isEmpty() || contraingresada.isEmpty()) {
                Toast.makeText(this, "Debe digitar todos los campos", Toast.LENGTH_SHORT).show()

            } else {


                    auth.signInWithEmailAndPassword( correolog.text.toString(), contrasenalog.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("LoginActivity", "signInWithEmail:success")
                                gotoMainActivity()

                                //si logra iniciar sesion queda cargado correo y contra, util cuando se actualizan datos
                                //val user = auth.currentUser
                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                                if (task.exception!!.message.equals(getString(R.string.error_usg_login))) {
                                    Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show()
                                    gotoRegistroActivity()
                                }

                            }
                        }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()  //var global para la autenticacion
        val currentUser = auth.currentUser

        //hay que mandarlo para cerrar sesion 
//        if(currentUser != null)
//            gotoMainActivity()
    }
    private fun gotoRegistroActivity() {
        val intent= Intent(this, RegistroActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun gotoMainActivity() {
        var intentmain = Intent(this, DrawerActivity::class.java)
        intentmain.putExtra("correo", correor)
        intentmain.putExtra("contrasena", contrar)
        startActivity(intentmain)
        finish()

    }



 //   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       // if (requestCode == 1234 && resultCode == Activity.RESULT_CANCELED)
       //     Toast.makeText(this, "Registro no exitoso, los datos no coinciden", Toast.LENGTH_SHORT).show()

  //      if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
  //          Toast.makeText(this, data?.extras?.getString("correo"), Toast.LENGTH_SHORT).show()
  //          super.onActivityResult(requestCode, resultCode, data)
  //          //var datosRecibidos = intent.extras
  //          if (data != null) {
  //              correor= data?.extras?.getString("correo").toString()
  //              contrar= data?.extras?.getString("contrasena").toString()

  //          }
  //      }else if(requestCode == 1234){
  //          Toast.makeText(this, "Registro no exitoso, los datos no coinciden", Toast.LENGTH_SHORT).show()
   //     }


 //   }
}

