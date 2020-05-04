package com.estefayjuanma.ilfornoapp.ui.pedidos
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estefayjuanma.ilfornoapp.AdapterPlato
import com.estefayjuanma.ilfornoapp.PedidosAdapter
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.domicilios.DomiciliosActivity
import com.estefayjuanma.ilfornoapp.ui.model.Pedido
import com.estefayjuanma.ilfornoapp.ui.model.Plato
import com.estefayjuanma.ilfornoapp.ui.model.Usuario
import com.estefayjuanma.ilfornoapp.ui.recoger.RecogerActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_olvidecontra.*
import kotlinx.android.synthetic.main.fragment_pedidos.*
import kotlinx.android.synthetic.main.fragment_pedidos.view.*
import kotlinx.android.synthetic.main.item_pedidos.*
import kotlinx.android.synthetic.main.item_pedidos.view.*


class PedidosFragment : Fragment(), PedidosAdapter.OnItemClickListener {

    var cuponglotreinta: String = ""
    var cuponglocincuenta: String = ""
    val allPedidos: MutableList<Pedido> = mutableListOf()
    lateinit var  adapterPedido: PedidosAdapter
    var total = 0
    var cont = 0
    var cantidadl = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_pedidos, container, false)
        allPedidos.clear()
        /////////////////////////////////Crear descuento//////////////////////////////////
        adapterPedido = PedidosAdapter(
            activity!!.applicationContext,
            allPedidos as ArrayList<Pedido>, this
        )
        root.rv_pedidos.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL, false
        )
        root.rv_pedidos.setHasFixedSize(true)
        root.rv_pedidos.adapter = adapterPedido

        cargarPedido()
        cargarCupon()



        root.bt_recoger_tienda.setOnClickListener {
            var intent = Intent (activity!!.applicationContext, RecogerActivity::class.java)
            intent.putExtra("total", "hola")
            startActivity(intent)
        }
        root.bt_domicilio.setOnClickListener {
            var intent = Intent (activity!!.applicationContext, DomiciliosActivity::class.java)
            intent.putExtra("total", "hola")
            startActivity(intent)

        }
        return root

    }


    override fun onResume() {
        super.onResume()

    }



    private fun cargarPedido() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Pedidos")
        var idU = FirebaseAuth.getInstance().currentUser?.uid

        allPedidos.clear()

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                allPedidos.clear()
                if(dataSnapshot.exists()){
                for (snapshot in dataSnapshot.child(idU!!).children){
                    val plato = snapshot.getValue(Pedido::class.java)
                    allPedidos.add(plato!!)
                    //cantidad = tv_cantidad.text as String
                    //total += allPedidos[cont].precioP.toInt()*allPedidos[cont].cantidad.toInt() //cantidad.toInt()
                    //cont+= 1
                    totalPedido()

                  //  Toast.makeText(activity!!.applicationContext, total, Toast.LENGTH_SHORT).show()
                }
                adapterPedido.notifyDataSetChanged()

            }}

            override fun onCancelled(p0: DatabaseError) {
                Log.w("Lista", "Failed to read values")
            }
        })

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
    override fun onItemClick(pedido: Pedido, position: Int) {
        var idU = FirebaseAuth.getInstance().currentUser?.uid
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Pedidos")
        myRef.child(idU!!).child(pedido.idP).removeValue()
            adapterPedido.notifyItemRemoved(position)


    }

    fun totalPedido(){
        tv_aplicardes.setOnClickListener {

            if (tv_cuponPedido.text.toString() == cuponglotreinta){
                total = (total *(0.7)).toInt()
                tv_total_pedido.text = total.toString()
            }
            else if (tv_cuponPedido.text.toString() == cuponglocincuenta){
                total = (total *(0.5)).toInt()
                tv_total_pedido.text = total.toString()
            }
            else
                Toast.makeText(activity!!.applicationContext, "El cupon ingresado no existe", Toast.LENGTH_SHORT).show()
        }
        if (tv_cuponPedido.text.isNullOrEmpty()){
            tv_total_pedido.text = total.toString()
        }

}
}