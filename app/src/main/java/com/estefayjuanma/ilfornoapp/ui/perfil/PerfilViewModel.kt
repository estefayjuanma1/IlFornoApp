package com.estefayjuanma.ilfornoapp.ui.perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PerfilViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Se mostraran los datos del usuario"
    }
    val text: LiveData<String> = _text
}