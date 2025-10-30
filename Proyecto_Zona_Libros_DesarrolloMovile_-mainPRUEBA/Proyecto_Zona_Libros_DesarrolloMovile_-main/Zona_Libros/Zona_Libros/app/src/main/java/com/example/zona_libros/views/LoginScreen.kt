package com.example.miprimeraapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.miprimeraapp.R // Asegúrate de que este paquete sea correcto
import com.example.miprimeraapp.viewModel.LoginViewModel // Asumiendo que el ViewModel está aquí
import androidx.compose.ui.Alignment

// Clase para la pantalla de Login de ZONALIBROS
class LoginScreen(private val navController: NavHostController? = null) {

    // Composable principal para la interfaz de login
    @Composable
    fun login(){
        // Traemos el ViewModel (la lógica)
        val viewModel = viewModel<LoginViewModel>()
        val correo = viewModel.loginViewModel.correo
        val contrasena = viewModel.loginViewModel.contrasena
        val nav = viewModel.deberiamosNavegar

        // 1. Manejo de Navegación: Si el login es exitoso
        if(nav == true){
            // Navegamos a la pantalla "home" después del login exitoso
            navController?.navigate("home")
            viewModel.cambiarEstadoNavegacion() // Reseteamos el estado para evitar re-navegación
        }

        // 2. Manejo de Alertas (Usando las funciones del ejemplo original)
        // Lógica simplificada de visualización de Alertas/Confirmaciones aquí...
        if(viewModel.mostrarAlerta == true){
            // Aquí iría la llamada a showAlert, pero se omite para simplicidad y mantener el foco en la UI
        }
        // ... (Omitimos el resto del manejo de alertas y confirmaciones por simplicidad) ...

        // Estructura de la pantalla
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally // Centramos los elementos
        )
        {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "ZONALIBROS",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary // Usar el color principal del tema
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Inicio de Sesión",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))


            // Campo de Correo Electrónico
            TextField(
                value = correo,
                onValueChange = { viewModel.cambioCorreo(it) }, // Llama a la función del ViewModel
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                // Aquí deberíamos mostrar un error si viewModel.loginViewModel.correoError no es null
                // Simplificamos omitiendo el icono y el error visual directo en el TextField.
            )
            // Si hay un error de validación, lo mostramos debajo (Retroalimentación visual)
            if (viewModel.loginViewModel.correoError != null) {
                Text(
                    text = viewModel.loginViewModel.correoError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Contraseña
            TextField(
                value = contrasena,
                onValueChange = { viewModel.cambioContrasena(it) }, // Llama a la función del ViewModel
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            // Si hay un error de validación, lo mostramos debajo
            if (viewModel.loginViewModel.contrasenaError != null) {
                Text(
                    text = viewModel.loginViewModel.contrasenaError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Iniciar Sesión
            Button(
                onClick = { viewModel.auth() }, // Llama a la función de autenticación del ViewModel
                modifier = Modifier.fillMaxWidth().height(50.dp)
            )
            {
                Text("INICIAR SESIÓN", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Enlace de Recuperación de Contraseña (Punto clave del caso 2: Mejorar el acceso)
            Text(
                text = "¿Olvidaste tu Contraseña?",
                color = Color.Blue,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    // TODO: Navegar a la pantalla de Recuperación de Contraseña
                    navController?.navigate("recuperarContrasena")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Enlace a la pantalla de Registro
            Text(
                text = "Crear Cuenta (Registrar)",
                color = Color.DarkGray,
                modifier = Modifier.clickable {
                    // TODO: Navegar a la pantalla de Registro
                    navController?.navigate("registro")
                }
            )

            // Si hay un error de credenciales general (ej: usuario o contraseña incorrectos)
            if (viewModel.loginViewModel.errorCredenciales != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = viewModel.loginViewModel.errorCredenciales!!,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun verLogin(){
    // Para el preview, solo llamamos a la función de la clase
    LoginScreen().login()
}