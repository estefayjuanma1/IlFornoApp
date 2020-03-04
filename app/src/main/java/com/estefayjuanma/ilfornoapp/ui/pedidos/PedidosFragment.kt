package com.estefayjuanma.ilfornoapp.ui.pedidos
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R


class PedidosFragment : Fragment() {

    private lateinit var pedidosViewModel: PedidosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pedidosViewModel =
            ViewModelProviders.of(this).get(PedidosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pedidos, container, false)
        val textView: TextView = root.findViewById(R.id.text_pedidos)
        pedidosViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}