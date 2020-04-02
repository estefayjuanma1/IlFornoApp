package com.estefayjuanma.ilfornoapp.ui.fuertes

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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_fuertes.view.*
import java.util.ArrayList
import kotlin.math.log

class FuertesFragment : Fragment() {

    val allPlatos: MutableList<Plato> = mutableListOf()
    lateinit var  adapterPlato: AdapterPlato

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_fuertes, container, false)

        adapterPlato = AdapterPlato(
            activity!!.applicationContext,
            allPlatos as ArrayList<Plato>
        )
        root.rv_fuertes.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL, false
        )
        root.rv_fuertes.setHasFixedSize(true)
        root.rv_fuertes.adapter = adapterPlato

        return root
    }

    override fun onResume() {
        super.onResume()
        cargarFuertes()
    }

    private fun cargarFuertes() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Platos_Fuertes")

        allPlatos.clear()

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children){
                    val plato = snapshot.getValue(Plato::class.java)
                    allPlatos.add(plato!!)
                }
                adapterPlato.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.w("Lista", "Failed to read values")
            }
        })
    }

}