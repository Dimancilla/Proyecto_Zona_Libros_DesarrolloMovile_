package com.example.zona_libros.views

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import java.io.File
import java.util.concurrent.Executors

@Composable
fun CamaraScreen(navController: NavHostController) {

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current

    var tienePermiso by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val pedirPermiso = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> tienePermiso = granted }

    var mostrarCamara by remember { mutableStateOf(false) }
    var fotoUri by remember { mutableStateOf<Uri?>(null) }

    val ejecutor = remember { Executors.newSingleThreadExecutor() }
    val capturarFoto = remember { ImageCapture.Builder().build() }
    val proveedorCamara = remember { ProcessCameraProvider.getInstance(context) }

    LaunchedEffect(Unit) {
        if (!tienePermiso) {
            pedirPermiso.launch(Manifest.permission.CAMERA)
        }
    }

    if (tienePermiso && mostrarCamara) {
        Box(Modifier.fillMaxSize()) {
            AndroidView(
                factory = { _ ->
                    PreviewView(context).apply {
                        val cameraProvider = proveedorCamara.get()
                        val preview = Preview.Builder().build().apply {
                            setSurfaceProvider(this@apply.surfaceProvider)
                        }

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycle,
                                CameraSelector.DEFAULT_BACK_CAMERA,
                                preview,
                                capturarFoto
                            )
                        } catch (_: Exception) {}
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            Button(
                onClick = {
                    val archivo = File(context.cacheDir, "foto_${System.currentTimeMillis()}.jpg")
                    val output = ImageCapture.OutputFileOptions.Builder(archivo).build()

                    capturarFoto.takePicture(
                        output,
                        ejecutor,
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                fotoUri = Uri.fromFile(archivo)
                                mostrarCamara = false
                            }

                            override fun onError(exception: ImageCaptureException) {}
                        }
                    )
                },
                modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
            ) { Text("Tomar Foto") }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                if (tienePermiso) mostrarCamara = true
                else pedirPermiso.launch(Manifest.permission.CAMERA)
            }) {
                Text("Abrir Cámara")
            }

            Spacer(Modifier.height(24.dp))

            fotoUri?.let { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = "Foto tomada",
                    modifier = Modifier.size(300.dp)
                )
            }
        }
    }
}
