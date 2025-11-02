package com.example.zona_libros.viewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf


class LoginViewModel : ViewModel() {

    // --- Variables de Estado (Entrada de Usuario y Mensajes) ---

    // Estado reactivo para almacenar el correo electrónico ingresado por el usuario
    var email = mutableStateOf("")
    // Estado reactivo para almacenar la contraseña ingresada por el usuario
    var password = mutableStateOf("")
    // Estado reactivo para comunicar errores o éxito de vuelta a la interfaz
    var errorMessage = mutableStateOf("")

    // --- Datos de Usuario Simulados (Para propósitos de prueba) ---

    // Correo válido hardcodeado. NOTA: En un sistema real, esto sería una consulta a una base de datos.
    private val validEmail = "usuario@duoc.cl"
    // Contraseña válida hardcodeada. NOTA: Se asume que esta cumple con el requisito complejo (mín. 10 caracteres, mayúsculas, etc.)
    private val validPassword = "Password123@"

    // --- Función Principal de Autenticación (Login) ---
    fun login() {
        errorMessage.value = when {
            // 1. Validación de campos vacíos
            email.value.isEmpty() -> "El correo no puede estar vacío"
            password.value.isEmpty() -> "Debes ingresar la contraseña"
            // 2. Validación de Credenciales (Simulada)
            // NOTA IMPORTANTE: Esta línea asume que el correo es INCORRECTO si no es una coincidencia exacta.
            // En un sistema real, aquí se llamaría a un servicio para verificar si el usuario existe.
            email.value != validEmail -> "Correo incorrecto"
            // 3. Éxito de Autenticación
            // Si pasa todas las validaciones anteriores  el login es exitoso.
            password.value != validPassword -> "Contraseña incorrecta"
            else -> "OK"
        }
    }
}