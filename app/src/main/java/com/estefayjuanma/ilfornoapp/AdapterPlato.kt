package com.estefayjuanma.ilfornoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.estefayjuanma.ilfornoapp.ui.model.Pedido
import com.estefayjuanma.ilfornoapp.ui.model.Plato
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import java.util.ArrayList
import kotlinx.android.synthetic.main.item_plato.view.*

class AdapterPlato (
    private val context: Context,
    private val platosList: ArrayList<Plato>
): RecyclerView.Adapter<AdapterPlato.PlatosViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlatosViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_plato,parent,false)
        return PlatosViewHolder(itemView,context)

    }
    override fun getItemCount(): Int = platosList.size

    override fun onBindViewHolder(
        holder: PlatosViewHolder,
        position: Int) {
        val plato: Plato = platosList[position]
        holder.bindPlato(plato)
    }

    class PlatosViewHolder(
        itemView: View,
        var context: Context
    ): RecyclerView.ViewHolder(itemView){

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Pedidos")

        fun bindPlato(plato: Plato) {
            itemView.tv_nombreplato.text = plato.nombre
           // itemView.tv_descripcion.text = plato.descripcion
            itemView.tv_precio.text = plato.precio
            itemView.iv_carrito.setImageResource(R.drawable.ic_shopping_cart_black_24dp)
            Picasso.get().load(plato.urlfoto).into(itemView.iv_imagenplato)


            itemView.iv_carrito.setOnClickListener {
                var nombreP = plato.nombre
                var precioP = plato.precio
                var idU = FirebaseAuth.getInstance().currentUser?.uid
                var idP = plato.id
                var cantidad1 = itemView.et_cantidad.text.toString()
                val pedido = Pedido(idP,nombreP,precioP,plato.urlfoto, cantidad1)
                Toast.makeText(context,"Producto añadido al carrito", Toast.LENGTH_SHORT).show()

                myRef.child(idU!!).child(idP).setValue(pedido)

            }
        }
    }
}