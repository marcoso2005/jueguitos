package com.example.jueguitos.Usuarios

import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jueguitos.BBDD.AdminBase
import com.example.jueguitos.R

class Ranking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ranking)

        var video: VideoView = findViewById(R.id.videoView);
        video.setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.copa}"))
        video.start();

        video.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true // Habilita el bucle
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var volver = findViewById<Button>(R.id.volver)
        volver.setOnClickListener(){
            finish()
        }


        val admin = AdminBase(this, "usuario", null, 1)
        val bd = admin.readableDatabase
        val lista:MutableList<String> =ArrayList()
        val fila = bd.query("usuario", null,null,null,null,null,"puntuacion DESC")
        if (fila.moveToFirst()) {
            do{
                lista.add(fila.getString(0) + "   -->   " + fila.getInt(2))
            }while(fila.moveToNext())
        } else
            Toast.makeText(this, "NO existen alumnos",  Toast.LENGTH_SHORT).show()

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista)

        var lv = findViewById<ListView>(R.id.listView)
        lv.setAdapter(adapter)

        fila.close()
        bd.close()
    }
}