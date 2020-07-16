package com.estefayjuanma.ilfornoapp.ui.domicilios

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
import com.estefayjuanma.ilfornoapp.ui.model.Recibo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_domicilios.*

class DomiciliosActivity : AppCompatActivity() {
    var totalf = ""
    val factura = FirebaseAuth.getInstance().currentUser?.uid
    val allPedidos: MutableList<Pedido> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domicilios)
        var flag_efectivo = false
        var datosRecibidos= intent.extras

        if (datosRecibidos != null) {
            totalf = datosRecibidos.getString("total").toString()
            }
        bt_enviar_pedido.setOnClickListener {

            var direccion = et_direccion.text.toString()
            var nombre = et_nombre_pedido.text.toString()
            var telefono = et_documento.text.toString()

            if (ck_efectivo.isChecked){
                flag_efectivo = true
            }else{
                flag_efectivo = false
            }

            if ( direccion.isNullOrEmpty() || nombre.isNullOrEmpty() ||  telefono.isNullOrEmpty()){
                Toast.makeText(this,"Debes diligenciar todos los campos",Toast.LENGTH_SHORT).show()

            }else{
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Recibos domicilios")
                val idRecibo = myRef.push().key
                val recibo = Recibo(factura!!, totalf,nombre,telefono,direccion,flag_efectivo)
                myRef.child(factura).child(idRecibo!!).setValue(recibo)
                tv_factura.text= factura.toString()
                bt_enviar_pedido.isEnabled = false
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
                }
                subirPedido(allPedidos)
            }
        })
    }

    private fun mostrarDialog() {
        val builder = AlertDialog.Builder(this)
        val intent =
        builder.setTitle("Gracias por su compra")
        builder.setMessage("Se verificaran los datos de pedido y nos comunicaremos con usted")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            var intent = Intent(this, DrawerActivity::class.java)
            startActivity(intent)
            finish()
        }

        builder.show()
    }

   fun subirPedido(allPedidos: MutableList<Pedido>) {
       val database = FirebaseDatabase.getInstance()
       val myRef = database.getReference("PedidosFinalDomicilios")
       var idU = FirebaseAuth.getInstance().currentUser?.uid
       var cont = 0
       val ensayo1 = myRef.push().key


       for (pedidoF in allPedidos) {
           val nombrepedidof = allPedidos[cont].nombreP
           val cantidadpedidof = allPedidos[cont].cantidad
           val idpedidofinal = allPedidos[cont].idP
           val pedidoF = PedidoF(idpedidofinal,nombrepedidof,cantidadpedidof)
           cont++
           myRef.child(idU!!).child(ensayo1!!).child(idpedidofinal).setValue(pedidoF)
       }
   }

}
