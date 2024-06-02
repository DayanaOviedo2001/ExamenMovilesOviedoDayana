package com.oviedodayana.Examen.states

import com.oviedodayana.Examen.models.Actividad

data class ActividadesState(
    val listaActividades: List<Actividad> = emptyList()
)