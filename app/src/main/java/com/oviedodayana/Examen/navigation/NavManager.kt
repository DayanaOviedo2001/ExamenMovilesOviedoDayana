package com.oviedodayana.Examen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oviedodayana.Examen.ActividadesViewModel
import com.oviedodayana.Examen.viewmodels.MascotasViewModel
import com.oviedodayana.Examen.views.AgregarView
import com.oviedodayana.Examen.DetalleActividadView
import com.oviedodayana.Examen.InicioView



@Composable
fun NavManager(empleadoViewModel: EmpleadoViewModel, actividadesViewModel: ActividadesViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            InicioView(navController, empleadoViewModel)
        }

        composable("agregar") {
            AgregarView(navController, empleadoViewModel)
        }

        composable(
            "actividades/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetalleActividadView(navController, actividadesViewModel, id)
        }
    }
}
