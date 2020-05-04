package com.estefayjuanma.ilfornoapp.ui.configuracion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.estefayjuanma.ilfornoapp.OlvidecontraActivity
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_config.*
import kotlinx.android.synthetic.main.fragment_config.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class ConfigFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_config, container, false)
        root.tv_cambiopass.setOnClickListener {
            val intent = Intent (activity!!.applicationContext, OlvidecontraActivity::class.java)
            startActivity(intent)
        }
        updateuser(root)
        return root
    }

    private fun updateuser(root: View?) {
        root?.bt_guardar_cambios?.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("usuarios")
            val newname = et_nuevo_nombre.text.toString()
            val newemail = et_nuevo_correo.text.toString()
            val currentuser = FirebaseAuth.getInstance().currentUser!!.uid

            myRef.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val usuario = snapshot.getValue(Usuario::class.java)
                        if (usuario!!.id == currentuser) {
                            if (newname != "")
                                et_nuevo_nombre.setText(usuario.nombre)
                            else
                                usuario.nombre = usuario.nombre
                            if (newemail != "")
                                et_nuevo_correo.setText(usuario.correo)
                            else
                                usuario.correo = usuario.correo
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("Lista", "Failed to read value.", error.toException())
                }
            })
            var bienescrito: Boolean = false
            val childUpdate = HashMap<String, Any> ()
            childUpdate["nombre"] = et_nuevo_nombre.text.toString()
            bienescrito=isEmailValid(newemail)
            if(bienescrito == true && !et_pass.text.isEmpty()) {
                childUpdate["correo"] = newemail
                myRef.child(currentuser).updateChildren(childUpdate)
                crearuseryeliminaruser(newemail,et_pass.text.toString())

            }else{
                Toast.makeText(
                    activity!!.applicationContext,
                    "Correo no valido o campo de contraseña vacio",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun crearuseryeliminaruser(newemail: String, pass: String){
        val auth = FirebaseAuth.getInstance()  //var global para la autenticacion
        val user = auth.currentUser!!.delete()
        auth.createUserWithEmailAndPassword(
            newemail,pass
        )

    }

    private fun isEmailValid(newemail: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(newemail)
        return matcher.matches()
    }
}