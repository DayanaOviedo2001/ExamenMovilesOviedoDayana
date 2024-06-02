package com.jeancasquete.ExamenMoviles_.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Empleado")
data class Empleado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("nombre")
    val nombre: String,
    @ColumnInfo("codigo")
    val codigo: String,
    @ColumnInfo("cedula")
    val cedula: String,
    @ColumnInfo("puesto")
    val puesto: String
)



