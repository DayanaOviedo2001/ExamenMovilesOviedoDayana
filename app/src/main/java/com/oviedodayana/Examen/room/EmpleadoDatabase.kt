package com.oviedodayana.Examen.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jeancasquete.ExamenMoviles_.models.Empleado
import com.jeancasquete.ExamenMoviles_.models.Actividad

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Empleado::class, Actividad::class], version = 2, exportSchema = false)
abstract class EmpleadoDatabase : RoomDatabase() {
    abstract fun usuariosDao(): EmpleadoDatabaseDao
    abstract fun actividadesDao(): ActividadesDatabaseDao // Agrega un DAO para las actividades

    companion object {
        @Volatile
        private var INSTANCE: EmpleadoDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): EmpleadoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmpleadoDatabase::class.java,
                    "db_empleado"
                )
                    .addCallback(EmpleadoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class EmpleadoDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.usuariosDao(), database.actividadesDao())
                    }
                }
            }

            suspend fun populateDatabase(EmpleadoDao: EmpleadoDatabaseDao, actividadesDao: ActividadesDatabaseDao) {
                // Inserta datos iniciales de empleado
                for (i in 1..5) {
                    val empleado = Empleado(
                        id = i,
                        nombre = "nombre$i",
                        apellido = "apellido$i",
                        cedula = "Cedula$i",
                        edad = "$i años"
                    )
                    empleadoDao.agregarUsuario(empleado)

                    // Inserta 3 actividades por cada empleado
                    for (j in 1..3) {
                        val actividad = Actividad(
                            nombre = "Actividad$j de Empleado$i",
                            tipo = "Tipo$j",
                            empleadpId = i
                        )
                        actividadesDao.insertarActividad(actividad)
                    }
                }

                // Verifica si las empleado se insertaron correctamente
                println("Empleados insertados:")
                val listaEmpleados = EmpleadoDao.obtenerUsuarios()
                listaEmpleados.collect { Empleado->
                    empleado.forEach { empleado ->
                        println("ID: ${empleado.id}, Nombre: ${empleado.nombre}")
                    }
                }

                // Verifica si las actividades  de empleado se insertaron correctamente
                println("Actividades Insertadas:")
                val listaActividades = actividadesDao.obtenerActividades()
                listaActividades.collect { actividad ->
                    actividad.forEach { actividad ->
                        println("ID: ${actividad.id_actividad}, Nombre: ${actividad.nombre}")
                    }
                }
            }
        }
    }
}
