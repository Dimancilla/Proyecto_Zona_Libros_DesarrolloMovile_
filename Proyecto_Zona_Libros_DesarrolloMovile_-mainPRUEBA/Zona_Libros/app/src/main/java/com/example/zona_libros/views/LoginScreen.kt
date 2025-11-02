package com.example.zona_libros.views
// --- Importaciones de Jetpack Compose y dependencias ---

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.zona_libros.viewModel.LoginViewModel

// Función Composable principal para la interfaz de login
@Composable
fun LoginScreen(navController: NavHostController) {
    // Inicializa o recupera el ViewModel

    val viewModel: LoginViewModel = viewModel()

    // Obtiene los estados reactivos (MutableState) desde el ViewModel
    val email = viewModel.email.value
    val password = viewModel.password.value
    val error = viewModel.errorMessage.value

    // --- Lógica de Manejo de Errores con Alerta (Pop-up) ---
    // Muestra la alerta si existe un mensaje de error y no es la señal de éxito ("OK")
    if (error.isNotEmpty() && error != "OK") {
        Alert(
            message = error, // Muestra el mensaje de error de credenciales
            onDismiss = { viewModel.errorMessage.value = "" } // Borra el error al cerrar la alerta
        )
    }

    // --- Lógica de Navegación Post-Éxito ---
    // Si el error es "OK", significa que el login fue exitoso.

    if (error == "OK") {
        navController.navigate("home") { // Navega a la pantalla principal "home"
            // Borra la pantalla de 'login' de la pila de navegación para que el usuario no pueda volver con el botón "Atrás"
            popUpTo("login") { inclusive = true }
        }
        viewModel.errorMessage.value = "" // Resetea el estado de error
    }

    // Estructura de la pantalla - Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título de la Tienda
        Text(
            text = "ZONALIBROS",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.email.value = it },
            label = { Text("Correo Electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.password.value = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Iniciar Sesión
        Button(
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Enlace de Recuperación de Contraseña
        Text(
            text = "¿Olvidaste tu contraseña?",
            color = Color.Blue,
            modifier = Modifier.clickable {
                navController.navigate("recuperarContrasena")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Enlace a la pantalla de Registro
        Text(
            text = "Crear Cuenta (Registrar)",
            color = Color.DarkGray,
            modifier = Modifier.clickable {
                navController.navigate("registro")
            }
        )
    }
}




// Función Composable para mostrar un Diálogo de Alerta
@Composable
fun Alert(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Aceptar")
            }
        },
        title = { Text("Atención") },
        text = { Text(message) }
    )
}
