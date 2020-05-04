package com.estefayjuanma.ilfornoapp.ui.perfil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.LoginActivity
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.inicio.InicioFragment
import com.estefayjuanma.ilfornoapp.ui.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class PerfilFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_perfil, container, false)

        root.bt_cerrar_sesion.setOnClickListener {
            val auth = FirebaseAuth.getInstance()  //var global para la autenticacion
            auth.signOut()
            gotoLoginActivity()
        }


        return root
    }

    override fun onResume() {
        super.onResume()
        cargarInfo()

    }

    private fun cargarInfo() {

        var user = FirebaseAuth.getInstance().currentUser?.uid
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios")

        myRef.addValueEventListener(object : ValueEventListener{


            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val usuario = snapshot.getValue(Usuario::class.java)
                    if (usuario!!.id == user) {
                        tv_mostrarnombre.text =
                            dataSnapshot.child(user!!).child("nombre").getValue().toString()
                        tv_mostrarcorreo.text =
                            dataSnapshot.child(user!!).child("correo").getValue().toString()
                        tv_id.text = dataSnapshot.child(user!!).child("id").getValue().toString()
                    }
                }
            }
            override fun onCancelled(dataSnapshot: DatabaseError) {
                Log.w("Lista", "Failed to read values")
            }
        })
    }

    private fun gotoLoginActivity() {
        val intent = Intent (activity!!.applicationContext, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        activity!!.finish()

    }
}

