package com.estefayjuanma.ilfornoapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.estefayjuanma.ilfornoapp.ui.Room.Ilforno
import com.estefayjuanma.ilfornoapp.ui.Room.UsuarioDAO
import com.estefayjuanma.ilfornoapp.ui.inicio.InicioFragment
import com.estefayjuanma.ilfornoapp.ui.model.Usuarioroom
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    var contraingresada=""
    var correoingresado=""
    var internet = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
 }

    public override fun onStart() {
        super.onStart()

        internet = isOnline( this )


        olvidecontra.setOnClickListener {
            var intent = Intent(this, OlvidecontraActivity::class.java)
            startActivityForResult(intent, 12348)
        }

        registrarse.setOnClickListener {
            var internet: Boolean= false
            var intent = Intent(this, RegistroActivity::class.java)
            internet = isOnline(this)
            if(internet==true) {
                startActivityForResult(intent, 1234)
            }else{
                Toast.makeText(
                    this,
                    "Debe tener conexion a internet",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

        inicio.setOnClickListener {


            correoingresado = correolog.text.toString()
            contraingresada = contrasenalog.text.toString()

            val auth = FirebaseAuth.getInstance()  //var global para la autenticacion

            if (correoingresado.isEmpty() || contraingresada.isEmpty()) {
                Toast.makeText(this, "Debe digitar todos los campos", Toast.LENGTH_SHORT).show()

            } else {


                if (internet == true) { ///////////
                    auth.signInWithEmailAndPassword(
                        correolog.text.toString(),
                        contrasenalog.text.toString()
                    )
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("LoginActivity", "signInWithEmail:success")
                                //borrarPedido()
                                gotoMainActivity()

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    this,
                                    "contraseña invalida",
                                    Toast.LENGTH_SHORT
                                ).show()
                                if (task.exception!!.message.equals(getString(R.string.error_usg_login))) {
                                    Toast.makeText(
                                        this,
                                        "Usuario no registrado",
                                        Toast.LENGTH_SHORT
                                    ).show()
  //                                  gotoRegistroActivity()
                                }

                            }
                        }
                } else {
                    val correo: String = correolog.text.toString()
                    val usuarioDao: UsuarioDAO = Ilforno.database.UsuarioDAO()
                    val usuarioroom: Usuarioroom = usuarioDao.searchUsuario(correo)
                    if (usuarioroom == null) {
                        Toast.makeText(
                            this,
                            "Usuario no exite, debe tener conexion a internet para registrarse",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        if (usuarioroom.contraseña == contrasenalog.text.toString()) {
                            gotoMainActivity()
                        } else {
                            Toast.makeText(
                                this,
                                "contraseña invalida",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


    private fun gotoMainActivity() {

     //   borrarPedido()
        var intentmain = Intent(this, DrawerActivity::class.java)
        startActivity(intentmain)
        finish()

    }

    private fun borrarPedido() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Pedidos")
        val idU = FirebaseAuth.getInstance().currentUser!!.uid

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.w("Lista", "No hay lista")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                myRef.child(idU).removeValue()
            }

        })

    }
}

