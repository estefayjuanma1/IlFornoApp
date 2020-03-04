package com.estefayjuanma.ilfornoapp.ui.inicio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InicioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento de inicio donde se establecera un slider y algunos botones de navegacion"
    }
    val text: LiveData<String> = _text
}