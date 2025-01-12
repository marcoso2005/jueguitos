package com.example.jueguitos.Pong

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jueguitos.R
import com.example.pong.Modelos.PongTable
import com.example.pong.Util.GameThread

class PongMain : AppCompatActivity() {
    companion object {
        var miPunuacion: TextView?= null;
        var oponente: TextView?= null;
        var volver: TextView?= null;
    }
    private var mGameThread: GameThread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pong_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var volver = findViewById<Button>(R.id.Volver)
        volver.setOnClickListener(){
            finish()
        }
        miPunuacion = findViewById(R.id.miPuntuacion);
        oponente = findViewById(R.id.miPuntuacion2);

        val table: PongTable = findViewById<View>(R.id.pongTable) as PongTable
        table.setScoreOpponent(findViewById<View>(R.id.tvScoreOpponent) as TextView)
        table.setScorePlayer(findViewById<View>(R.id.tvScorePlayer) as TextView)
        table.setStatus(findViewById<View>(R.id.tvStatus) as TextView)



        mGameThread = table.game
    }
}