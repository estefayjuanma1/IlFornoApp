package com.estefayjuanma.ilfornoapp.ui.ayuda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R

class AyudanosFragment : Fragment() {

    private lateinit var ayudaViewModel: AyudaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ayudaViewModel =
            ViewModelProviders.of(this).get(AyudaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ayudanos, container, false)
        val textView: TextView = root.findViewById(R.id.text_ayuda)
        ayudaViewModel.text.observe(this, Observer { textView.text = it })

        return root
    }
}