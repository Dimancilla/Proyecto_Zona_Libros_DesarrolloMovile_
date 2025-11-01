package com.example.zona_libros.viewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf


class LoginViewModel : ViewModel() {

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var errorMessage = mutableStateOf("")

    private val validEmail = "usuario@duoc.cl"
    private val validPassword = "Password123@"

    fun login() {
        errorMessage.value = when {
            email.value.isEmpty() -> "El correo no puede estar vacío"
            password.value.isEmpty() -> "Debes ingresar la contraseña"
            email.value != validEmail -> "Correo incorrecto"
            password.value != validPassword -> "Contraseña incorrecta"
            else -> "OK"
        }
    }
}