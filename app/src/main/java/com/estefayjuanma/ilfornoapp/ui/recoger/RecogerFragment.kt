package com.estefayjuanma.ilfornoapp.ui.recoger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R

class RecogerFragment : Fragment() {

    private lateinit var recogerViewModel: RecogerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recogerViewModel =
            ViewModelProviders.of(this).get(RecogerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recoger, container, false)
        val textView: TextView = root.findViewById(R.id.text_recoger)
        recogerViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}