package com.oviedodayana.Examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.jeancasquete.ExamenMoviles_.viewmodels.ActividadesViewModel
import com.oviedodayana.Examen.ui.theme.ExamenMoviles_Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenMovilesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val database = EmpleadoDatabase.getDatabase(this, lifecycleScope)
                    val EmpleadoDao = database.usuariosDao()
                    val actividadesDao = database.actividadesDao()

                    val EmpleadoViewModel = EmpleadoViewModel(empleadoDao)
                    val actividadesViewModel = ActividadesViewModel(actividadesDao)

                    NavManager(empleadoViewModel = empleadoViewModel, actividadesViewModel = actividadesViewModel)

                    // Llama al método para mostrar las empleado en la consola
                    mostrarempleado(database)
                    mostrarActividades(database)
                }
            }
        }
    }

    private fun mostrarempleado(database: EmpleadoDatabase) {
        // Usa un scope para lanzar una corrutina
        lifecycleScope.launch {
            // Obtén todas las empleado desde la base de datos
            val empleado = database.usuariosDao().obtenerUsuarios()
            // Recolecta los datos del flujo
            empleado.collect { listaEmpleado ->
                // Itera sobre la lista de empleado e imprime cada una
                listaEmpleado.forEach { empleado ->
                    println("Nombre: ${empleado.nombre}, Apellido: ${empleado.apellido}, Cedula: ${empleado.cedula}, Edad: ${empleado.id}")
                }
            }
        }
    }

    private fun mostrarActividades(database: EmpleadoDatabase) {
        // Usa un scope para lanzar una corrutina
        lifecycleScope.launch {
            // Obtén todas las actividades desde la base de datos
            val actividades = database.actividadesDao().obtenerActividades()
            // Recolecta los datos del flujo
            actividades.collect { listaActividades ->
                // Itera sobre la lista de actividades e imprime cada una
                listaActividades.forEach { actividad ->
                    println("Actividad: ${actividad.nombre}, Tipo: ${actividad.tipo}, ID del Empleado: ${actividad.empleadoId}")
                }
            }
        }
    }
}
