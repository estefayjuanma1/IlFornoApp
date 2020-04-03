package com.estefayjuanma.ilfornoapp

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.estefayjuanma.ilfornoapp.ui.Room.Ilforno
import com.estefayjuanma.ilfornoapp.ui.Room.UsuarioDAO
import com.estefayjuanma.ilfornoapp.ui.Room.UsuarioDB
import com.estefayjuanma.ilfornoapp.ui.model.Usuario
import com.estefayjuanma.ilfornoapp.ui.model.Usuarioroom
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registro.*
import java.sql.Types.NULL
import java.util.regex.Matcher
import java.util.regex.Pattern


class RegistroActivity : AppCompatActivity()  {
    //AppCompatActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        bt_registrar.setOnClickListener {

            var nombre = nombre.text.toString()
            var correo = et_correo.text.toString()
            var contra = et_contrasena.text.toString()
            var concontra = repcontrasena.text.toString()
            var intent = Intent()

            //if (isEmailValid(correo))
            if (contra != concontra) {
                Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show()
            }
            else {
                if (nombre.isEmpty() || correo.isEmpty() || contra.isEmpty() || concontra.isEmpty()) {
                    Toast.makeText(this, "Debe digitar todos los campos", Toast.LENGTH_SHORT).show()
                }
                else{
                    createUserInRoom()
                    createUserInFirebaseAuth()
                }
            }
        }
    }

    private fun createUserInRoom() {
        var correo = et_correo.text.toString()
        var contra = et_contrasena.text.toString()

        val usuarioroom = Usuarioroom(NULL, correo, contra)
        var UsuarioDao: UsuarioDAO = Ilforno.database.UsuarioDAO()

        UsuarioDao.insertUsuario(usuarioroom)
        }



    private fun createUserInFirebaseAuth() {
        var malescrito = true
        val auth = FirebaseAuth.getInstance()  //var global para la autenticacion

        auth.createUserWithEmailAndPassword(
            et_correo.text.toString(),
            et_contrasena.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginActivity", "signInWithEmail:success")
                    val user = auth.currentUser
                    createUserOnDataBase(user)
                    gotoLoginActivity()
                } else {
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    malescrito=isEmailValid(et_correo.text.toString())/////
                    if(malescrito==false){/////
                        Toast.makeText(this, "Correo mal escrito", Toast.LENGTH_SHORT).show()////
                    }////
                    if (task.exception!!.message.equals("The email address is already in use by another acoount.")) {
                        Toast.makeText(
                            this,
                            "Ya existe un usuario con esa cuenta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }

    private fun gotoLoginActivity() {
        val intent = Intent (this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun createUserOnDataBase(user: FirebaseUser?) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios")

        val usuario = Usuario(
            user!!.uid,
            user!!.email.toString()
        )

        myRef.child(user!!.uid).setValue(usuario)
    }

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    override fun onBackPressed() {  //podria enviarle un emnsaje al usuario diciendo que no se guardo correctamente
        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }
}
