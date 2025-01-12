package com.example.jueguitos.Usuarios

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jueguitos.BBDD.AdminBase
import com.example.jueguitos.R
import java.io.ByteArrayOutputStream

class Registro : AppCompatActivity() {
    var PICK_IMAGE_REQUEST = 1;
    var imagen:Bitmap ?= null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var volver = findViewById<Button>(R.id.volver)
        volver.setOnClickListener(){
            finish()
        }

        var registro = findViewById<Button>(R.id.registro)
        registro.setOnClickListener() {
            val admin = AdminBase(this, "usuario", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()

            var nombre = findViewById<TextView>(R.id.nombre)
            var contraseña = findViewById<TextView>(R.id.contraseña)
            if (comprobar(nombre.text.toString(), contraseña.text.toString())) {

                registro.put("nombre", nombre.text.toString());
                registro.put("contraseña", contraseña.text.toString());
                registro.put("puntuacion", 0);
                if(imagen != null) {
                    registro.put("imagen", bitmapToByteArray(imagen!!))
                }
                val ins = bd.insert("usuario", null, registro);

                if (ins != -1L) {
                    Toast.makeText(this,"Se creo el usuario de forma correcta",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            }
        }

        var inicio = findViewById<Button>(R.id.inicio)
        inicio.setOnClickListener(){
            val intento1 = Intent(this, Inicio::class.java)
            finish()
            startActivity(intento1)
        }

        var subir = findViewById<ImageView>(R.id.subir)
        subir.setOnClickListener(){
            abrirExplorador();
        }
    }

    fun abrirExplorador(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            imageUri?.let {
                // Mostrar la imagen seleccionada en el ImageView
                imagen = MediaStore.Images.Media.getBitmap(contentResolver, it)
            }
        }
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    private fun comprobar(nombre: String, contraseña: String): Boolean {
        var correcto = true;
        if (nombre.isNullOrBlank() || contraseña.isNullOrBlank()) {
            Toast.makeText(this, "Rellene todos los datos", Toast.LENGTH_SHORT).show()
            correcto = false;
        }
        return correcto;
    }
}