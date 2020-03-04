package com.estefayjuanma.ilfornoapp.ui.pedidos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PedidosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento de pedidos donde se podran realizar las compras."
    }
    val text: LiveData<String> = _text
}