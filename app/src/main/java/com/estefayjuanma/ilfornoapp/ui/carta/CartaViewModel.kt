package com.estefayjuanma.ilfornoapp.ui.carta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Este fragmento mostrara las listas de los productos ofrecidos"
    }
    val text: LiveData<String> = _text
}