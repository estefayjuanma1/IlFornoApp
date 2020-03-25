package com.estefayjuanma.ilfornoapp.ui.entradas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R

class EntradasFragment : Fragment() {

    private lateinit var  entradasViewModel: EntradasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        entradasViewModel =
            ViewModelProviders.of(this).get(EntradasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_entradas, container, false)
        val textView: TextView = root.findViewById(R.id.text_entradas)
        entradasViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}