package com.example.miprimeraapp.views

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import java.io.File
import java.util.concurrent.Executors

class CamaraScreen (private val navController: NavHostController? = null){

    @Composable
    fun camara(){
        val context = LocalContext.current
        val lifecycle = LocalLifecycleOwner.current

        var tenemosPermisoCamara by remember {
            mutableStateOf(
                ContextCompat.checkSelfPermission(context,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            )
        }

        val lanzarPermiso = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()) { granted ->
            tenemosPermisoCamara = granted
        }

        var camaraAbierta by remember { mutableStateOf(false) }
        var imagenUri by remember { mutableStateOf<Uri?>(null) }

        val ejecutarCamara = remember { Executors.newSingleThreadExecutor() }
        val capturaFoto = remember { ImageCapture.Builder().build() }

        val proveedorCamara = remember { ProcessCameraProvider.getInstance(context) }

        if(!tenemosPermisoCamara){
            LaunchedEffect(Unit) {
                lanzarPermiso.launch(Manifest.permission.CAMERA)
            }
        }

        if(tenemosPermisoCamara && camaraAbierta){
            Box(Modifier.fillMaxSize()){
                AndroidView(factory = {ctx ->
                    PreviewView(ctx).apply {
                        val cameraProvider = proveedorCamara.get()
                        val vistaPrevia = Preview.Builder().build().also {
                            it.setSurfaceProvider ( this.surfaceProvider )
                        }

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycle,
                                CameraSelector.DEFAULT_BACK_CAMERA,
                                vistaPrevia,
                                capturaFoto
                            )
                        }catch (e : Exception){

                        }
                    }
                },
                    modifier = Modifier.fillMaxSize())

                Button(onClick = {
                    val archivoFoto = File(context.cacheDir,"foto_${System.currentTimeMillis()}.jpg")
                    val salidaFoto = ImageCapture.OutputFileOptions.Builder(archivoFoto).build()
                    capturaFoto.takePicture(
                        salidaFoto,
                        ejecutarCamara,
                        object : ImageCapture.OnImageSavedCallback {

                            override fun onImageSaved(resultadoSalidaImagen: ImageCapture.OutputFileResults) {
                                imagenUri = Uri.fromFile(archivoFoto)
                                camaraAbierta = false
                            }

                            override fun onError(exception: ImageCaptureException) {
                                TODO("Not yet implemented")
                            }


                        }





                    )
                },
                    modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)

                )


                { Text(text = "Tomar foto") }

            }
        }else{
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(onClick = {
                    if(tenemosPermisoCamara){
                        camaraAbierta = true
                    }else{
                        lanzarPermiso.launch(Manifest.permission.CAMERA)
                    }
                }) {
                    Text(text = "Abrir camara")
                }

                Spacer(modifier = Modifier.height(24.dp))

                imagenUri?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "Foto",
                        modifier = Modifier.size(500.dp)
                    )
                }
            }
        }
    }
}