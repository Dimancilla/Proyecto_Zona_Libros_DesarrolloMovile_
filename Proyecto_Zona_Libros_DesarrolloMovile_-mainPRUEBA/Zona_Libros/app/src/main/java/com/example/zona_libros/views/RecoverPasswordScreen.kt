package com.example.zona_libros.views

// --- Importaciones de Jetpack Compose y dependencias ---
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Función Composable principal para la pantalla de Recuperación de Contraseña
@Composable
fun RecoverPasswordScreen(navController: NavHostController) {

    // Estados reactivos para manejar la entrada del usuario y el estado de la alerta
    var email by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }

    // Estructura de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Recuperar Contraseña", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // Campo de entrada para el correo registrado
        OutlinedTextField(
            value = email,
            onValueChange = {email = it },
            label = { Text("Correo registrado") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón principal de acción
        Button(
            onClick = {
                // Lógica de validación: Se verifica que el correo sea del dominio @duoc.cl,
                // que es un requisito clave del proyecto
                if (email.endsWith("@duoc.cl")) {
                    // Mensaje de éxito si cumple la validación
                    alertMessage = "Si el correo está registrado, recibirás un mensaje de recuperación ✅"
                } else {
                    // Mensaje de error personalizado si no es un correo de Duoc UC
                    alertMessage = "El correo debe ser @duoc.cl "
                }
                // Muestra la alerta con el resultado de la validación
                showAlert = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar contraseña")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de texto para volver a la pantalla de Login
        TextButton(
            onClick = { navController.navigate("login") }
        ) {
            Text("Volver al Login")
        }
    }

    /** Alert Dialog */
    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false }, // Se cierra al hacer clic fuera o presionar atrás
            confirmButton = {
                Button(onClick = { showAlert = false }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Información") },
            text = { Text(alertMessage) }
        )
    }
}

// Función auxiliar necesaria para la estructura del código, pero no implementada.
@Composable
fun KeyboardOptions(keyboardType: KeyboardType) {
    TODO("Not yet implemented")
}