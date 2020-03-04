package com.estefayjuanma.ilfornoapp.ui.carta
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R

class CartaFragment : Fragment() {

    private lateinit var cartaViewModel: CartaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartaViewModel =
            ViewModelProviders.of(this).get(CartaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_carta, container, false)
        val textView: TextView = root.findViewById(R.id.text_carta)
        cartaViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}