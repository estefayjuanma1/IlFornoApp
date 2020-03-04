package com.estefayjuanma.ilfornoapp.ui.ayuda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AyudaViewModel : ViewModel() {

    //private
    val _text = MutableLiveData<String>().apply {
        value = "En este fragmento se brinda la oportunidad de dejar alguna sugerencia por parte de los" +
                "usuarios de ña app"
    }

    val text: LiveData<String> = _text
}