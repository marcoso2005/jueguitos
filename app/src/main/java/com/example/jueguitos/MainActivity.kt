package com.example.jueguitos

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jueguitos.TresEnRaya.TresEnRaya
import com.example.jueguitos.Minas.MainMinas
import com.example.jueguitos.Pong.PongMain
import com.example.jueguitos.Usuarios.Inicio
import com.example.jueguitos.Usuarios.Ranking

class MainActivity : AppCompatActivity() {
    companion object{
        var imagen:Bitmap ?= null;
        var nombre = "";
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var media = MediaPlayer.create(this,R.raw.cancion);
        media?.setOnPreparedListener { player ->
            //player.seekTo(0)
            player.start()
        }


        media?.setOnCompletionListener { player ->
            player.seekTo(0)
            player.start()
        }

        var raya = findViewById<ImageView>(R.id.raya);
        raya.setOnClickListener(){
            val intento1 = Intent(this, TresEnRaya::class.java)
            startActivity(intento1)
        }

        var minas = findViewById<ImageView>(R.id.minas);
        minas.setOnClickListener(){
            val intento1 = Intent(this, MainMinas::class.java)
            startActivity(intento1)
        }

        var pong = findViewById<ImageView>(R.id.Pong);
        pong.setOnClickListener(){
            val intento1 = Intent(this, PongMain::class.java)
            startActivity(intento1)
        }

        var iniciar = findViewById<ImageView>(R.id.iniciarSesion)
        iniciar.setOnClickListener(){
            val intento1 = Intent(this, Inicio::class.java)
            startActivity(intento1)
        }

        var ranking = findViewById<ImageView>(R.id.ranking)
        ranking.setOnClickListener(){
            val intento1 = Intent(this, Ranking::class.java)
            startActivity(intento1)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!nombre.isNullOrBlank()) {
            val iniciar = findViewById<ImageView>(R.id.iniciarSesion)
            if (imagen != null) {
                iniciar.setImageBitmap(imagen)
            } else {
                iniciar.setImageResource(R.drawable.feliz)
            }
        }
    }


}