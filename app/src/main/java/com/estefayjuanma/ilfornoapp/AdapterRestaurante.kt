package com.estefayjuanma.ilfornoapp

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estefayjuanma.ilfornoapp.ui.model.Restaurante
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_plato.view.*
import kotlinx.android.synthetic.main.item_restaurante.view.*

class AdapterRestaurante(
    private val context: Context,
    private val restaurantesList: ArrayList<Restaurante>
) : RecyclerView.Adapter<AdapterRestaurante.RestaurantesViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantesViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_restaurante, parent,false)
        return RestaurantesViewHolder(itemView, context)
           }

    override fun getItemCount(): Int = restaurantesList.size

    override fun onBindViewHolder(
        holder: RestaurantesViewHolder,
        position: Int
    ) {
        val restaurante: Restaurante = restaurantesList[position]
        holder.bindRestaurante(restaurante)
    }

    class RestaurantesViewHolder(
        itemView: View,
        var context: Context
    ) : RecyclerView.ViewHolder(itemView){

        fun bindRestaurante(restaurante: Restaurante) {

            itemView.tv_nombre.text = restaurante.nombre
            Picasso.get().load(restaurante.urlfoto).into(itemView.iv_imagenrestaurante);
            itemView.tv_telefono.text = restaurante.telefono
            itemView.tv_direccion.text = restaurante.direccion
            itemView.iv_location.setImageResource(R.drawable.ic_location_on_black_24dp)


            itemView.iv_location.setOnClickListener {
                var intent = Intent(context,MapsActivity::class.java)
                intent.putExtra("latitud", restaurante.latitud).addFlags(FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("longitud", restaurante.longitud)
                intent.putExtra("nombre", restaurante.nombre)
                context.startActivity(intent)
            }
        }
    }

}

