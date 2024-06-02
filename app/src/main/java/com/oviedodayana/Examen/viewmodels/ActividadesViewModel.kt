package com.jeancasquete.ExamenMoviles_.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeancasquete.ExamenMoviles_.room.ActividadesDatabaseDao
import com.jeancasquete.ExamenMoviles_.states.ActividadesState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ActividadesViewModel(
    private val dao: ActividadesDatabaseDao
): ViewModel() {

    var state by mutableStateOf(ActividadesState())
        private set

    init {
        viewModelScope.launch {
            dao.obtenerActividades().collectLatest {
                state = state.copy(
                    listaActividades = it
                )
            }
        }
    }

    fun obtenerActividadesPorEmpleado(empleadoId: Int) {
        viewModelScope.launch {
            dao.obtenerActividadesPorEmpleado(empleadoId).collectLatest {
                state = state.copy(
                    listaActividades = it
                )
            }
        }
    }
}