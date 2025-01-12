package com.example.jueguitos.Minas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.jueguitos.R
import java.util.Random

class MainMinas : AppCompatActivity() {
    lateinit var tabla: LinearLayout;

    companion object {
        var texto:TextView? = null;
        var reiniciar:Button? = null;
        var bandera:Button? = null
        var desactivar: Boolean = false;
        var primer: Boolean = true;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minas_main)
        iniciar();


        reiniciar?.setOnClickListener {
            iniciar();
        }

        bandera?.setBackgroundColor(Color.argb(255,38, 150, 0));

        bandera?.setOnClickListener {
            desactivar = !desactivar;

            if(!desactivar){
                bandera?.setBackgroundColor(Color.argb(255,38, 150, 0));
            }else{
                bandera?.setBackgroundColor(Color.argb(255,211, 211, 0));
            }
        }

        var volver = findViewById<Button>(R.id.Volver);
        volver.setOnClickListener(){
            this.finish();
        }
    }

    fun iniciar(){
        tabla = findViewById<LinearLayout>(R.id.layout);
        texto = findViewById(R.id.Ganar)
        reiniciar = findViewById(R.id.reiniciar)
        crearCeldas();
        colocarBombas();
        Tablero.ganar= false;
        Tablero.fin = true;
        texto?.visibility = View.INVISIBLE;
        MainMinas.reiniciar?.isEnabled = false;
        reiniciar?.visibility = View.INVISIBLE;
        bandera = findViewById(R.id.bandera)
        desactivar = false;
    }



    private fun crearCeldas() {
        var cambios = true;
        for (i in 0..9) {
            cambios = !cambios
            for (j in 0..9) {
                var c1: LinearLayout = tabla.getChildAt(i) as LinearLayout
                var c2: ConstraintLayout = c1.getChildAt(j) as ConstraintLayout
                cambios = !cambios;
                Tablero.tablero[i][j] = Celda(c2, 0, cambios, this);
            }
        }
    }

    private fun colocarBombas() {
        val azar:Random = Random();
        var cuenta = 0;
        var x:Int;
        var y:Int;
        while(cuenta < 11){
            x = azar.nextInt(9)
            y = azar.nextInt(9)

            if(x != 0 || y != 0 && Tablero.tablero[x][y].accion == 0){
                Tablero.tablero[x][y].bomba = true;
                Tablero.tablero[x][y].accion = 2;
                cuenta++;
            }
        }
    }
}





