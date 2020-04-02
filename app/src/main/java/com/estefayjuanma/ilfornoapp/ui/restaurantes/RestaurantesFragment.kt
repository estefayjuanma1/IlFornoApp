package com.estefayjuanma.ilfornoapp.ui.restaurantes
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
import com.estefayjuanma.ilfornoapp.AdapterRestaurante
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.model.Restaurante
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_restaurantes.view.*
import kotlinx.android.synthetic.main.item_restaurante.view.*

class RestaurantesFragment : Fragment() {

    var finish: Boolean = false///////////////////////
    val allRestaurantes: MutableList<Restaurante> = mutableListOf()
    lateinit var adapterRestaurante: AdapterRestaurante

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?//root
    ): View? {
        val root = inflater.inflate(R.layout.fragment_restaurantes, container, false)

        adapterRestaurante = AdapterRestaurante(
            activity!!.applicationContext,
            allRestaurantes as ArrayList<Restaurante>
        )
        root.rv_restaurantes.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL, false
        )
        root.rv_restaurantes.setHasFixedSize(true)
        root.rv_restaurantes.adapter = adapterRestaurante

        return root
    }

    override fun onResume() {
        super.onResume()
        cargarRestaurantes()
    }

    private fun cargarRestaurantes() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Restaurantes")

        allRestaurantes.clear()

        //Leer de base de datos
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(snapshot in dataSnapshot.children){
                    val restaurante = snapshot.getValue(Restaurante::class.java)
                    allRestaurantes.add(restaurante!!)
                }
                adapterRestaurante.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

                Log.w("Lista", "Failed to read values",error.toException())
            }
        })
    }
}