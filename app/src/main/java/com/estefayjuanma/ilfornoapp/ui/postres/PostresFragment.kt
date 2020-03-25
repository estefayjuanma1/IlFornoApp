package com.estefayjuanma.ilfornoapp.ui.postres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R

class PostresFragment : Fragment() {

    private lateinit var postresViewModel: PostresViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postresViewModel =
            ViewModelProviders.of(this).get(PostresViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_postres, container, false)
        val textView: TextView = root.findViewById(R.id.text_postres)
        postresViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}