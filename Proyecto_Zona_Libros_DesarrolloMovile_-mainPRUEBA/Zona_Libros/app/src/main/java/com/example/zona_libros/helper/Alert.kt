package com.example.zona_libros.helper


import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun Alert(message: String, onDismiss: () -> Unit) {
    if (message.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Aceptar")
                }

            },
            title = { Text("Atencion")},
            text = { Text(message)}
        )
    }
}

