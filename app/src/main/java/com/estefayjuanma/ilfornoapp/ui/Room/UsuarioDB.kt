package com.estefayjuanma.ilfornoapp.ui.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.estefayjuanma.ilfornoapp.ui.model.Usuarioroom

@Database(entities = [Usuarioroom::class], version =1 )
abstract class UsuarioDB : RoomDatabase() {


    abstract fun UsuarioDAO() : UsuarioDAO
}