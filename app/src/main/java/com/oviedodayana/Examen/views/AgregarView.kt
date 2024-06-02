package com.jeancasquete.ExamenMoviles_.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jeancasquete.ExamenMoviles_.models.Empleado
import com.jeancasquete.ExamenMoviles_.viewmodels.EmpleadoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarView(navController: NavController, viewModel: EmpleadoViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Mascotas", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                }
            )
        }
    ) {
        ContentAgregarView(it, navController, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentAgregarView(it: PaddingValues, navController: NavController, viewModel: EmpleadoViewModel) {
    var nombre by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Casquete Rodriguez Jean Pier")
        Text(text = "Entidad Mascota")
        Text(text = "7mo ''A''")

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = raza,
            onValueChange = { raza = it },
            label = { Text(text = "raza") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        OutlinedTextField(
            value = color,
            onValueChange = { color = it },
            label = { Text(text = "color") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text(text = "edad") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        Button(
            onClick = {
                if (nombre.isNotBlank() && raza.isNotBlank() && color.isNotBlank() && edad.isNotBlank()) {
                    val mascota = Empleado(nombre = nombre, raza = raza, color = color, edad = edad)
                    viewModel.agregarUsuario(mascota)
                    navController.navigate("inicio")
                } else {
                    // Aquí puedes mostrar un mensaje de error o realizar alguna acción si los campos no están llenos.
                }
            }
        ) {
            Text(text = "Agregar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Navegar a la pantalla de listado de registros
                navController.navigate("inicio")
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black
            )
        ) {
            Text(text = "Ver listado de registros")
        }
    }
}
