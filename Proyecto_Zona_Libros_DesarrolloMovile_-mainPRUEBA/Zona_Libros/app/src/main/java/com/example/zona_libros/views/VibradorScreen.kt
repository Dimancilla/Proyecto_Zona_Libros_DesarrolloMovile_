package com.example.miprimeraapp.views

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

class VibracionScreen(private val navController: NavHostController? = null) {


    @Composable
    fun BotonVibrar() {

        val context = LocalContext.current

        @RequiresApi(Build.VERSION_CODES.O)
        @RequiresPermission(Manifest.permission.VIBRATE)
        fun vibrar() {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            if (vibrator.hasVibrator()) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                // Método antiguo para versiones anteriores
                vibrator.vibrate(500)
            }
        }


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Botón que llama a 'vibrar' al presionarse
            Button(onClick = { vibrar() }) {
                Text("Vibrar dispositivo")
            }
        }

    }
}