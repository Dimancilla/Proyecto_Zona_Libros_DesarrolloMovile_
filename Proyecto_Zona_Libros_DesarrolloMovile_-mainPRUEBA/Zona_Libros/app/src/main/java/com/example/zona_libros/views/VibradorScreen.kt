package com.example.miprimeraapp.views

// --- Importaciones de Android y Compose ---
import android.Manifest
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

// Clase que contiene el Composable de la pantalla de vibración
class VibracionScreen(private val navController: NavHostController? = null) {


    @Composable
    fun BotonVibrar() {

        // Obtiene el contexto actual de la aplicación
        val context = LocalContext.current

        // Función interna marcada para requerir API 26 (Oreo) y el permiso VIBRATE
        @RequiresApi(Build.VERSION_CODES.O)
        @RequiresPermission(Manifest.permission.VIBRATE)
        fun vibrar() {
            // Accede al servicio de vibración del sistema
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            // Verifica si el dispositivo tiene capacidad de vibrar
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                // Método antiguo para versiones anteriores
                vibrator.vibrate(500)
            }
        }


        // Contenedor principal para centrar el botón
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Centra el contenido (el botón) en el medio

        ) {
            // Botón que llama a 'vibrar' al presionarse
            Button(onClick = { vibrar() }) {
                Text("Vibrar dispositivo")
            }
        }

    }
}