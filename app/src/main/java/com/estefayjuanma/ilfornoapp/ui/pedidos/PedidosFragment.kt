package com.estefayjuanma.ilfornoapp.ui.pedidos
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.domicilios.DomiciliosActivity
import com.estefayjuanma.ilfornoapp.ui.recoger.RecogerActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_pedidos.view.*


class PedidosFragment : Fragment() {

    var cuponglotreinta: String = ""
    var cuponglocincuenta: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_pedidos, container, false)
        /////////////////////////////////Crear descuento//////////////////////////////////
        cargarCupon()
        root.bt_recoger_tienda.setOnClickListener {
            var intent = Intent (activity!!.applicationContext, RecogerActivity::class.java)
            intent.putExtra("total", "hola")
            startActivity(intent)

            //       val transaction = fragmentManager?.beginTransaction()?.addToBackStack(null)
     //       transaction?.replace(R.id.nav_host_fragment, RecogerFragment())
     //       transaction?.commit()
            ////nav_host_fragment es el id del del frame layout en la actividad que invoca los fragmentos
        }

        root.bt_domicilio.setOnClickListener {
            var intent = Intent (activity!!.applicationContext, DomiciliosActivity::class.java)
            intent.putExtra("total", "hola")
            startActivity(intent)

        }
        return root

    }

    private fun cargarCupon() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Cupones")


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    var cupontreinta = p0.child("id1").child("codigo").getValue()
                    var cuponcincuenta = p0.child("id2").child("codigo").getValue()
                    // Log.w("Lista",  cupon)
                    cuponglotreinta = cupontreinta.toString()
                    cuponglocincuenta = cuponcincuenta.toString()

                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.w("Lista", "Failed to read values")
            }
        })
    }


    override fun onResume() {
        super.onResume()
    }
}