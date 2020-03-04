package com.estefayjuanma.ilfornoapp.ui.configuracion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfigViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "En este fragmento se permite una configuracion de algunos aspectos de la aplicacion, se considera sustituirse"
    }
    val text: LiveData<String> = _text
}