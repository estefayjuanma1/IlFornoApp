package com.estefayjuanma.ilfornoapp.ui.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuariosroom")
class Usuarioroom (
    @PrimaryKey(autoGenerate = true) @ColumnInfo (name = "id") var id: Int,
    @ColumnInfo (name = "correo")var correo: String,
    @ColumnInfo (name = "contraseña")var contraseña: String
)
