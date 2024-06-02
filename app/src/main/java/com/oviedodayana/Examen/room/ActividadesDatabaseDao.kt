package com.oviedodayana.Examen.room

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.jeancasquete.ExamenMoviles_.models.Actividad;


import kotlinx.coroutines.flow.Flow;

@Dao
interface ActividadesDatabaseDao {

    @Query("SELECT * FROM actividades")
    fun obtenerActividades(): Flow<List<Actividad>>

    @Query("SELECT * FROM actividades WHERE empleado_id = :empleadoId")
    fun obtenerActividadesPorEmpleado(empleadoId: Int): Flow<List<Actividad>>

    @Insert
    suspend fun insertarActividad(actividad: Actividad)

    @Update
    suspend fun actualizarActividad(actividad: Actividad)

    @Delete
    suspend fun borrarActividad(actividad: Actividad)
}