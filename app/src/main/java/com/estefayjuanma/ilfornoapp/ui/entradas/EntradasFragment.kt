package com.estefayjuanma.ilfornoapp.ui.entradas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estefayjuanma.ilfornoapp.AdapterPlato
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.model.Plato
import com.estefayjuanma.ilfornoapp.ui.model.Restaurante
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_entradas.view.*
import kotlinx.android.synthetic.main.fragment_restaurantes.view.*
import java.util.ArrayList

class EntradasFragment : Fragment() {

    val allPlatos: MutableList<Plato> = mutableListOf()
    lateinit var adapterPlato: AdapterPlato

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_entradas, container, false)

        adapterPlato = AdapterPlato(
            activity!!.applicationContext,
            allPlatos as ArrayList<Plato>
        )
        root.rv_entradas.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL, false
        )
        root.rv_entradas.setHasFixedSize(true)
        root.rv_entradas.adapter = adapterPlato

        return root

    }

    override fun onResume() {
        super.onResume()
        cargarEntradas()
    }

    private fun cargarEntradas() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Entradas")

        allPlatos.clear()

        //Leer base de datos
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(snapshot in dataSnapshot.children){
                    val plato = snapshot.getValue(Plato::class.java)
                    allPlatos.add(plato!!)
                }
                adapterPlato.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Lista", "Failed to read values",error.toException())
            }
        })
    }
}