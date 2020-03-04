package com.estefayjuanma.ilfornoapp.ui.cupones
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R


class CuponesFragment : Fragment() {

    private lateinit var cuponesViewModel: CuponesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cuponesViewModel =
            ViewModelProviders.of(this).get(CuponesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cupones, container, false)
        val textView: TextView = root.findViewById(R.id.text_cupones)
        cuponesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}