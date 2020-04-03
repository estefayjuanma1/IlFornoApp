package com.estefayjuanma.ilfornoapp.ui.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.estefayjuanma.ilfornoapp.ui.model.Restaurante
import com.estefayjuanma.ilfornoapp.ui.model.Usuario
import com.estefayjuanma.ilfornoapp.ui.model.Usuarioroom

@Dao
interface UsuarioDAO {
    @Insert
    fun insertUsuario(usuarioroom: Usuarioroom)

    @Query("SELECT * FROM usuariosroom WHERE correo LIKE :correo")
    fun searchUsuario( correo: String): Usuarioroom

 //   @Query("SELECT * FROM USUARIOSROOM")
 //   fun getUsuarioroom(): List<Usuarioroom>
}