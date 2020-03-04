package com.estefayjuanma.ilfornoapp.ui.restaurantes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "mostrara la ubicacion de los restaurantes"
    }
    val text: LiveData<String> = _text
}