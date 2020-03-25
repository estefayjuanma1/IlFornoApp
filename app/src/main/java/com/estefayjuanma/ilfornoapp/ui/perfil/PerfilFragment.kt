package com.estefayjuanma.ilfornoapp.ui.perfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.LoginActivity
import com.estefayjuanma.ilfornoapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class PerfilFragment : Fragment() {

    private lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        perfilViewModel =
            ViewModelProviders.of(this).get(PerfilViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_perfil, container, false)
        val textView: TextView = root.findViewById(R.id.text_perfil)

        root.bt_cerrar_sesion.setOnClickListener {
            val auth = FirebaseAuth.getInstance()  //var global para la autenticacion
            auth.signOut()
            gotoLoginActivity()
        }

        perfilViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    private fun gotoLoginActivity() {
        val intent = Intent (activity!!.applicationContext, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


}

