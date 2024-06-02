package com.oviedodayana.Examen.room


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jeancasquete.ExamenMoviles_.models.Empleado

import kotlinx.coroutines.flow.Flow

@Dao
interface EmpleadoDatabaseDao {

    @Query("SELECT * FROM mascotas")
    fun obtenerUsuarios(): Flow<List<Empleado>>

    @Query("SELECT * FROM mascotas WHERE id = :id")
    fun obtenerUsuario(id: Int): Flow<Empleado>

    @Insert
    suspend fun agregarUsuario(mascota: Empleado)

    @Update
    suspend fun actualizarUsuario(mascota: Empleado)

    @Delete
    suspend fun borrarUsuario(mascota: Empleado)


}