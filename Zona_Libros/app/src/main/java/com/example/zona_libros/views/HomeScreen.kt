package com.example.zona_libros.views

// Importaciones de Jetpack Compose
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Función Composable para la pantalla que se muestra tras un login exitoso
@Composable
fun HomeScreen(navController: NavHostController) {

    // Estructura de la pantalla: Contenedor vertical
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Mensaje de bienvenida, confirmando el acceso a la plataforma
        Text("Bienvenido a ZonaLibros ", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        // Descripción de la tienda (contexto del negocio)
        Text("Explora libros raros, clásicos, e historias únicas ")

        Spacer(modifier = Modifier.height(40.dp))

        // Botón de Cerrar Sesión (Logout)
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}