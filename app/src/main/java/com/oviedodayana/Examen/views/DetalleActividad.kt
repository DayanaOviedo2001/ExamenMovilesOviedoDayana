package com.jeancasquete.ExamenMoviles_.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jeancasquete.ExamenMoviles_.viewmodels.ActividadesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleActividadView(navController: NavController, viewModel: ActividadesViewModel, mascotaId: Int) {
    // Obtenemos el estado del ViewModel
    val state = viewModel.state

    // Lanzamos un efecto para obtener las actividades por mascotaId cuando se monta el Composable
    LaunchedEffect(mascotaId) {
        viewModel.obtenerActividadesPorMascota(mascotaId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Actividades de la Mascota", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Regresar", tint = Color.White)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            // Mostrar la lista de actividades relacionadas con la mascota
            LazyColumn {
                items(state.listaActividades) { actividad ->
                    Column(
                        modifier = Modifier.padding(16.dp) // Añade padding alrededor de cada actividad
                    ) {
                        // Muestra la información de la actividad
                        Text(
                            text = "Actividad",
                            style = TextStyle(fontSize = 18.sp, color = Color.Black) // Cambia el tamaño y el color aquí
                        )
                        Text(text = actividad.nombre)
                        Text(
                            text = "TipoActividad",
                            style = TextStyle(fontSize = 18.sp, color = Color.Black) // Cambia el tamaño y el color aquí
                        )
                        Text(text = actividad.tipo)
                    }
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) // Añade una línea divisoria
                }
            }
        }
    }
}
