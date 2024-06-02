package com.oviedodayana.Examen.states

import com.oviedodayana.Examen.models.Empleado


data class EmpleadoState(
    val listaUsuarios: List<Empleado> = emptyList()
)


