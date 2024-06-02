package com.jeancasquete.ExamenMoviles_.views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jeancasquete.ExamenMoviles_.viewmodels.EmpleadoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioView(navController: NavController, viewModel:EmpleadoViewModel) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Mascotas", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },

    ) {

        ContentInicioView(it, navController, viewModel)
    }
}

@Composable
fun ContentInicioView(it: PaddingValues, navController: NavController, viewModel: EmpleadoViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier.padding(it)
    ) {
        LazyColumn {
            items(state.listaUsuarios) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .clickable {
                            navController.navigate("actividades/${it.id}")
                        }//

                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "ID: ${it.id.toString()}",
                            style = TextStyle(fontSize = 18.sp, color = Color.Black)
                        )
                        Text(
                            text = "Nombre",
                            style = TextStyle(fontSize = 18.sp, color = Color.Black) // Cambia el tamaño y el color aquí
                        )
                        Text(text = it.nombre)
                        Text(
                            text = "Raza",
                            style = TextStyle(fontSize = 18.sp, color = Color.Black) // Cambia el tamaño y el color aquí
                        )
                        Text(text = it.raza)
                        Text(
                            text = "Color",
                            style = TextStyle(fontSize = 18.sp, color = Color.Black) // Cambia el tamaño y el color aquí
                        )
                        Text(text = it.color)
                        Text(
                            text = "Edad",
                            style = TextStyle(fontSize = 18.sp, color = Color.Black) // Cambia el tamaño y el color aquí
                        )
                        Text(text = it.edad)

                        IconButton(
                            onClick = { viewModel.borrarUsuario(it) }
                        ) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")
                        }
                    }
                }
            }
        }
    }
}












