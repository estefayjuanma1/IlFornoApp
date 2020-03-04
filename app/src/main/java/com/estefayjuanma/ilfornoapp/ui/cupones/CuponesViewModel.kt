package com.estefayjuanma.ilfornoapp.ui.cupones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CuponesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "En este espacio se mostraran los cupones validos para el usuario"
    }
    val text: LiveData<String> = _text
}