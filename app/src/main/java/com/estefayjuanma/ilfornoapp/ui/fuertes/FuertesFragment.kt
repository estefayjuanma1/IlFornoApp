package com.estefayjuanma.ilfornoapp.ui.fuertes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R

class FuertesFragment : Fragment() {

    private lateinit var fuertesViewModel: FuertesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fuertesViewModel =
            ViewModelProviders.of(this).get(FuertesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_fuertes, container, false)
        val textView: TextView = root.findViewById(R.id.text_fuertes)
        fuertesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}