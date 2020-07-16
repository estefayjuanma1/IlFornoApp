package com.estefayjuanma.ilfornoapp.ui.recoger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.estefayjuanma.ilfornoapp.DrawerActivity
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.model.Pedido
import com.estefayjuanma.ilfornoapp.ui.model.PedidoF
import com.estefayjuanma.ilfornoapp.ui.model.Recibotienda
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_recoger.*

class RecogerActivity : AppCompatActivity() {
    var totalf = ""
    val factura = FirebaseAuth.getInstance().currentUser?.uid
    val allPedidos: MutableList<Pedido> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recoger)
        var datosRecibidos= intent.extras
        if (datosRecibidos != null) {
            totalf = datosRecibidos.getString("total").toString()
        }
        bt_enviar_pedido2.setOnClickListener {
            var nombre = et_nombre_pedido2.text.toString()
            var telefono = et_telefono2.text.toString()
            var restaurante = ""

            if(sp_restaurante.selectedItem == "Cc Mayorca"){
                restaurante = "Mayorca"
            }else if(sp_restaurante.selectedItem == "Cc Viva Envigado"){
                restaurante = "Viva envigado"
            }else if(sp_restaurante.selectedItem == "Cc El Tesoro") {
                restaurante = "El tesoro"
            }else if(sp_restaurante.selectedItem == "Cc Santa fe"){
                restaurante = "Santafe"
            }else if(sp_restaurante.selectedItem == "Mall Villagrande"){
                restaurante = "Villagrande"
            }else if(sp_restaurante.selectedItem == "Mall San Lucas"){
                restaurante = "San lucas"
            }else if(sp_restaurante.selectedItem == "Il forno Parque Lleras"){
                restaurante = "Parque Lleras"
            }else{
                restaurante = "Las palmas"
            }


            if ( nombre.isNullOrEmpty() ||  telefono.isNullOrEmpty()){
                Toast.makeText(this,"Debes diligenciar todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("RecibosRecogerTienda")
                val idRecibo = myRef.push().key
                val recibo = Recibotienda(factura!!, totalf,nombre,telefono,restaurante)
                myRef.child(factura).child(idRecibo!!).setValue(recibo)
                tv_factura.text = factura.toString()
                bt_enviar_pedido2.isEnabled = false
                cargarpedido()
                mostrarDialog()
            }

        }
    }

    private fun cargarpedido() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Pedidos")
        var idU = FirebaseAuth.getInstance().currentUser?.uid

        allPedidos.clear()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
                Log.w("Lista", "Failed to read values")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                allPedidos.clear()
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.child(idU!!).children) {
                        val plato = snapshot.getValue(Pedido::class.java)
                        allPedidos.add(plato!!)
                    }
                    subirPedido(allPedidos)
                }
            }
        })
    }
    fun subirPedido(allPedidos: MutableList<Pedido>) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("PedidosFinalTienda")
        var idU = FirebaseAuth.getInstance().currentUser?.uid
        //var cont = 0
        val ensayo1 = myRef.push().key

        for (pedidoF in allPedidos) {
            val nombrepedidof = pedidoF.nombreP
            val cantidadpedidof = pedidoF.cantidad
            val idpedidofinal = pedidoF.idP
            val pedidoFinal = PedidoF(idpedidofinal,nombrepedidof,cantidadpedidof)
          //  cont++
            myRef.child(idU!!).child(ensayo1!!).child(idpedidofinal).setValue(pedidoFinal)
        }
    }

    private fun mostrarDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Gracias por su compra")
        builder.setMessage("Se verificaran los datos de pedido y nos comunicaremos con usted")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            var intent = Intent(this, DrawerActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.show()
    }
}
