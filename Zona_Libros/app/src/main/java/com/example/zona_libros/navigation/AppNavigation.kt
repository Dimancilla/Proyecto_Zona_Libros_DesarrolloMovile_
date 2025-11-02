package com.example.zona_libros.navigation

// --- Importaciones de Jetpack Compose y dependencias de Navegación ---
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zona_libros.views.LoginScreen
import com.example.zona_libros.views.RecoverPasswordScreen
import com.example.zona_libros.views.HomeScreen
import com.example.zona_libros.views.RegisterScreen

// Función Composable que define el grafo de navegación de la aplicación
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login" //  Pantalla inicial
    ) {

        // 1. Ruta de Login
        composable("login") {
            LoginScreen(navController)
        }

        // 2. Ruta de Registro
        composable("registro") {
            RegisterScreen(navController)
        }

        // 3. Ruta de Recuperación de Contraseña
        composable("recuperarContrasena") {
            RecoverPasswordScreen(navController)
        }

        // 4. Ruta de Home (Acceso post-login exitoso)
        composable("home") {
            HomeScreen(navController)
        }
    }
}
// Función auxiliar necesaria para la estructura del código
fun composable(string: String, function: @Composable () -> Unit) {}


}




