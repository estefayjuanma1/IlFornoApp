package com.estefayjuanma.ilfornoapp.ui.recoger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecogerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento donde se podran estableces restaurante para la recogida de algun pedido"
    }
    val text: LiveData<String> = _text
}