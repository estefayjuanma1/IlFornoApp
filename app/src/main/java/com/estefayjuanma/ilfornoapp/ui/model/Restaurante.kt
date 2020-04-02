package com.estefayjuanma.ilfornoapp.ui.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Restaurantes")
class Restaurante (
    @PrimaryKey @ColumnInfo(name = "id") var id: String = "",
    @ColumnInfo(name = "latitud") var latitud: String = "",
    @ColumnInfo(name = "longitud") var longitud: String = "",
    @ColumnInfo(name = "telefono") var telefono: String = "",
    @ColumnInfo(name = "direccion") var direccion: String = "",
    @ColumnInfo(name = "urlfoto") var urlfoto: String = "",
    @ColumnInfo(name = "nombre") var nombre: String = ""
)