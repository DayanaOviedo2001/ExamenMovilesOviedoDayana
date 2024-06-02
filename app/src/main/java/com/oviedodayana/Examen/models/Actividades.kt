package com.oviedodayana.Examen.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "actividades",
    foreignKeys = [ForeignKey(
        entity = Empleado::class,
        parentColumns = ["id"],
        childColumns = ["mascota_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Actividad(
    @PrimaryKey(autoGenerate = true)
    val id_actividad: Int = 0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "tipo")
    val tipo: String,
    @ColumnInfo(name = "empleado_id")
    val mascotaId: Int // Columna que servirá como clave externa (foreign key) para relacionar la actividad con una mascota
)
