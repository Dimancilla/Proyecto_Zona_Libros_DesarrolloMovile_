package com.example.zona_libros.navigation

import android.health.connect.datatypes.ExerciseRoute
import android.widget.MediaController
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.miprimeraapp.views.LoginScreen
import com.example.zona_libros.ui.screens.*

sealed class AppScreens(val route: String) {
    object Inicio : AppScreens ("inicio_screen")
    object Login : AppScreens ("login_screen")
    object Register : AppScreens ("register_screen")
    object Recover : AppScreens ("recover_screen")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.Inicio.route) {

        composable(AppScreens.Inicio.route){
            InicioScreen(navController)
        }

        composable(AppScreens.Login.route){
            LoginScreen(navController)
        }

        composable(AppScreens.Register.route){
            RegisterScreen(navController)
        }

        composable(AppScreens.Recover.route){
            RecoverPasswordScreen(navController)
        }


    }
}





