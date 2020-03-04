package com.estefayjuanma.ilfornoapp.ui.restaurantes
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R

class RestaurantesFragment : Fragment() {

    private lateinit var restaurantesViewModel: RestaurantesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restaurantesViewModel =
            ViewModelProviders.of(this).get(RestaurantesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_restaurantes, container, false)
        val textView: TextView = root.findViewById(R.id.text_restaurantes)
        restaurantesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}