package com.jeancasquete.ExamenMoviles_.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeancasquete.ExamenMoviles_.models.Empleado
import com.jeancasquete.ExamenMoviles_.room.EmpleadoDatabaseDao
import com.jeancasquete.ExamenMoviles_.states.EmpleadoState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EmpleadoViewModel(
    private val dao: EmpleadoDatabaseDao
): ViewModel() {

    var state by mutableStateOf(EmpleadoState())
        private set


    init {
        viewModelScope.launch {
            dao.obtenerUsuarios().collectLatest {
                state = state.copy(
                    listaUsuarios = it
                )
            }
        }
    }

    fun agregarUsuario(empleado: Empleado) = viewModelScope.launch {
        dao.agregarUsuario(empleado = empleado)

    }

    fun borrarUsuario(empleado: Empleado) = viewModelScope.launch {
        dao.borrarUsuario(empleado = empleado)
    }

}