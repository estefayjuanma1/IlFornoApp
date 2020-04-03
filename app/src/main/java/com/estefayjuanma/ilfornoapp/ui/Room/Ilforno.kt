package com.estefayjuanma.ilfornoapp.ui.Room

import android.app.Application
import androidx.room.Room

class Ilforno: Application()
{
    companion object {
        lateinit var database: UsuarioDB
    } //se crea un cdatabase

    override fun onCreate() {
        super.onCreate()

        Ilforno.database = Room.databaseBuilder(
            this,
            UsuarioDB::class.java,
            "usuario_DB"
        )
            .allowMainThreadQueries()
            .build() //patron builder, me permtie retornar determinado objeto
    }
}