package com.estefayjuanma.ilfornoapp.ui.ayuda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.model.Recomendacion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_ayudanos.*
import kotlinx.android.synthetic.main.fragment_ayudanos.view.*

class AyudanosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_ayudanos, container, false)
            enviarReco(root)
        return root
    }

    private fun enviarReco(root: View) {
       root.bt_enviar_reco.setOnClickListener {

           if (root.et_ayuda.text.toString().isEmpty()) {
             //  bt_enviar_reco.isEnabled = false
               Toast.makeText(context,"Ingresa primero una recomendacion", Toast.LENGTH_SHORT).show()
           } else {

               var user = FirebaseAuth.getInstance().currentUser?.uid
               val database = FirebaseDatabase.getInstance()
               val myRef = database.getReference("Recomendaciones")
               val idR = myRef.push().key
               val recomendacion = Recomendacion(et_ayuda.text.toString())
               myRef.child(user!!).child(idR!!).setValue(recomendacion)
               Toast.makeText(context,"Su recomendacion a sido registrada", Toast.LENGTH_SHORT).show()
           }
       }
    }
}