package com.example.jueguitos.Usuarios

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jueguitos.BBDD.AdminBase
import com.example.jueguitos.MainActivity
import com.example.jueguitos.R

class Inicio : AppCompatActivity() {
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val volver = findViewById<Button>(R.id.volver)
        volver.setOnClickListener(){
            finish()
        }

        val registro = findViewById<Button>(R.id.registro)
        registro.setOnClickListener() {
            val intento1 = Intent(this, Registro::class.java)
            finish()
            startActivity(intento1)
        }

        val inicio = findViewById<Button>(R.id.inicio)
        inicio.setOnClickListener() {
            val nombre = findViewById<TextView>(R.id.nombre)
            val contrasena = findViewById<TextView>(R.id.contraseña)
            val admin = AdminBase(this, "usuario", null, 1)
            val bd = admin.readableDatabase

            if (comprobar(nombre.text.toString(), contrasena.text.toString())) {

                val selection = "nombre = ?"
                val selectionArgs = arrayOf(nombre.text.toString())

                val fila = bd.query(
                    "usuario",
                    null,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
                )

                if (fila.moveToFirst()) {
                    if(fila.getString(1).equals(contrasena.text.toString())){
                        Toast.makeText(this, "Bienvenido " + nombre.text.toString(), Toast.LENGTH_SHORT).show()
                        MainActivity.nombre = fila.getString(0)
                        if(fila.getBlob(3) != null) {
                            MainActivity.imagen = byteArrayToBitmap(fila.getBlob(3))
                        }else{
                            MainActivity.imagen = BitmapFactory.decodeResource(resources, R.drawable.feliz)
                        }
                        finish()
                    }else{
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "No existe el alumno", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    private fun comprobar(nombre: String, contrasena: String): Boolean {
        var correcto = true
        if (nombre.isNullOrBlank() || contrasena.isNullOrBlank()) {
            Toast.makeText(this, "Rellene todos los datos", Toast.LENGTH_SHORT).show()
            correcto = false;
        }
        return correcto;
    }
}