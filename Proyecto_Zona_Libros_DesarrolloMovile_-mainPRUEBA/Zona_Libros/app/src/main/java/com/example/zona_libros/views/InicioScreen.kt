package com.example.zona_libros.views

// --- Importaciones de Jetpack Compose y dependencias ---
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.zonalibros.navigation.AppScreens



// Función Composable principal para la pantalla de inicio
@Composable
fun InicioScreen(navController: NavHostController) {

    // Estructura de la pantalla: Contenedor vertical
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       // Título o Logo de la aplicación
        Text(" ZonaLibros", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(40.dp))

        // Botón 1: Navegación a Iniciar Sesión (Login)
        Button(
            onClick = { navController.navigate(AppScreens.Login.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón 2: Navegación a Registrarse (Registro)
        Button(
            onClick = { navController.navigate(AppScreens.Register.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
    }
}

