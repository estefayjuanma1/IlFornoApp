package com.estefayjuanma.ilfornoapp
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.estefayjuanma.ilfornoapp.ui.model.Pedido
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pedidos.view.*


class PedidosAdapter(
    private val context: Context,
    private val pedidosList: ArrayList<Pedido>,
    val onItemClickListener: OnItemClickListener  //
): RecyclerView.Adapter<PedidosAdapter.PedidosViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PedidosViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_pedidos, parent, false)
        return PedidosViewHolder(itemView, context,onItemClickListener)
    }

    override fun getItemCount(): Int = pedidosList.size

    override fun onBindViewHolder(@NonNull
        holder: PedidosViewHolder, position: Int
    ) {
        val pedido: Pedido = pedidosList[position]
        holder.bindPedido(pedido,position)
    }

    class PedidosViewHolder(
        itemView: View,
        var context: Context,
        var onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var pedido: Pedido
        private var posicion: Int = 0


        fun bindPedido(pedido: Pedido, position: Int) {
            this.pedido = pedido
            this.posicion = position
            itemView.tv_nombreplato2.text = pedido.nombreP
            itemView.tv_precio2.text = pedido.precioP
            itemView.iv_delete.setImageResource(R.drawable.ic_delete_forever_black_24dp)
            Picasso.get().load(pedido.urlfoto).into(itemView.iv_imagenplato2)
            itemView.iv_delete.setOnClickListener(this)
            itemView.tv_cantidad2.text = pedido.cantidad.toString()

        }
        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(pedido, posicion)
        }

    }
    interface OnItemClickListener {
        fun onItemClick(pedido: Pedido, position: Int)
    }
}//