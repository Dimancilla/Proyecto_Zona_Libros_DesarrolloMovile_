package com.example.zona_libros.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zona_libros.views.LoginScreen
import com.example.zona_libros.views.RegisterScreen
import com.example.zona_libros.views.RecoverPasswordScreen
import com.example.zona_libros.views.HomeScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") { LoginScreen(navController) }
        composable("registro") { RegisterScreen(navController) }
        composable("recuperarContrasena") { RecoverPasswordScreen(navController) }
        composable("home") { HomeScreen(navController) }
    }
}







