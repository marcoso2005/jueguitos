package com.example.jueguitos.TresEnRaya

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jueguitos.R

class TresEnRaya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tres_en_raya)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        clic();
    }

    var ganar = false;
    var finalizar = true;
    var circulo = 0;
    var cruz = 0;


    fun clic() {
        var turno = false;
        var tablero = Array(3) { Array(3) { 0 } };
        val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
        val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
        layout1.addView(fondo)


        val volver = findViewById<Button>(R.id.volver);
        volver.setOnClickListener(){
            finish();
        }

        val win = findViewById<Button>(R.id.ganar);
        win.setOnClickListener {
            if (ganar == true) {
                for (i in 0..2) {
                    for (j in 0..2) {
                        tablero[i][j] = 0;
                    }
                }
                turno = false;
                ganar = false;
                val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                layout1.addView(fondo)
                val veintidos = findViewById<View>(R.id.button22)
                val doce = findViewById<View>(R.id.button12)
                val treintdos = findViewById<View>(R.id.button32)
                veintidos.isClickable = true;
                doce.isClickable = true;
                treintdos.isClickable = true
            }
        }

        val once = findViewById<View>(R.id.button11)
        once.setOnClickListener {
            if (!ganar) {
                if (tablero[0][0] == 0) {
                    if (turno) {
                        tablero[0][0] = 1
                    } else {
                        tablero[0][0] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }


        val doce = findViewById<View>(R.id.button12)
        doce.setOnClickListener {
            if (!ganar) {
                if (tablero[0][1] == 0) {
                    if (turno) {
                        tablero[0][1] = 1
                    } else {
                        tablero[0][1] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }

        val trece = findViewById<View>(R.id.button13)
        trece.setOnClickListener {
            if (!ganar) {
                if (tablero[0][2] == 0) {
                    if (turno) {
                        tablero[0][2] = 1
                    } else {
                        tablero[0][2] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }

        val veintiuno = findViewById<View>(R.id.button21)
        veintiuno.setOnClickListener {
            if (!ganar) {
                if (tablero[1][0] == 0) {
                    if (turno) {
                        tablero[1][0] = 1
                    } else {
                        tablero[1][0] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }

        val veintidos = findViewById<View>(R.id.button22)
        veintidos.setOnClickListener {
            if (!ganar) {
                if (tablero[1][1] == 0) {
                    if (turno) {
                        tablero[1][1] = 1
                    } else {
                        tablero[1][1] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }

        val veintitres = findViewById<View>(R.id.button23)
        veintitres.setOnClickListener {
            if (!ganar) {
                if (tablero[1][2] == 0) {
                    if (turno) {
                        tablero[1][2] = 1
                    } else {
                        tablero[1][2] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }

        val treintauno = findViewById<View>(R.id.button31)
        treintauno.setOnClickListener {
            if (!ganar) {
                if (tablero[2][0] == 0) {
                    if (turno) {
                        tablero[2][0] = 1
                    } else {
                        tablero[2][0] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }

        val treintados = findViewById<View>(R.id.button32)
        treintados.setOnClickListener {
            if (!ganar) {
                if (tablero[2][1] == 0) {
                    if (turno) {
                        tablero[2][1] = 1
                    } else {
                        tablero[2][1] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }

        val treintatres = findViewById<View>(R.id.button33)
        treintatres.setOnClickListener {
            if (!ganar) {
                if (tablero[2][2] == 0) {
                    if (turno) {
                        tablero[2][2] = 1
                    } else {
                        tablero[2][2] = 2
                    }
                    ganar(tablero, turno);
                    llenar(tablero);
                    val fondo = LienzoTresEnRaya(this, tablero, turno, ganar, circulo, cruz)
                    turno = !turno;
                    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
                    layout1.addView(fondo)
                }
            }
        }

    }

    fun llenar(cel: Array<Array<Int>>) {
        finalizar = true;
        for (i in 0..2 step 1) {
            for (j in 0..2) {
                if (cel[i][j] == 0) {
                    finalizar = false;
                }
            }
        }
        val veintidos = findViewById<View>(R.id.button22)
        var win = findViewById<Button>(R.id.ganar);
        if (finalizar) {
            ganar = true;
            Toast.makeText(this, R.string.empate.toString(), Toast.LENGTH_SHORT).show()
            val param = win.layoutParams as ConstraintLayout.LayoutParams
            param.topMargin = 0;
            win.layoutParams = param
            veintidos.isClickable = false;
            veintidos.isClickable = false;
        }
    }


    fun ganar(cel: Array<Array<Int>>, turno: Boolean) {
        var numVertical: Int;
        var numHorizontal: Int;
        var numDiagonal: Int;for (i in 0..2 step 1) {
            if (cel[i][0] != 0) {
                numVertical = cel[i][0];
                if (numVertical == cel[i][1] && numVertical == cel[i][2]) {
                    this.ganar = true;
                }
            }
            if (cel[0][i] != 0) {
                numHorizontal = cel[0][i];
                if (numHorizontal == cel[1][i] && numHorizontal == cel[2][i]) {
                    this.ganar = true;
                }
            }

        }
        numDiagonal = cel[0][0];
        if (numDiagonal != 0 && numDiagonal == cel[1][1] && numDiagonal == cel[2][2]) {
            this.ganar = true;
        }
        numDiagonal = cel[0][2];
        if (numDiagonal != 0 && numDiagonal == cel[1][1] && numDiagonal == cel[2][0]) {
            this.ganar = true;
        }

        var res = this.resources
        val veintidos = findViewById<View>(R.id.button22)
        var veintiuno = findViewById<View>(R.id.button12)
        var veinttres = findViewById<View>(R.id.button32)
        var win = findViewById<Button>(R.id.ganar);
        var volver = findViewById<Button>(R.id.volver);
        if(ganar && !finalizar){
            if(turno) {
                circulo++;
                Toast.makeText(this, res.getString(R.string.circulo), Toast.LENGTH_SHORT).show()
                win.isEnabled = true;

                val param = win.layoutParams as ConstraintLayout.LayoutParams
                param.topMargin = 0;
                param.rightMargin = 1000
                win.layoutParams = param
                veintidos.isClickable = false;
                veintiuno.isClickable = false
                veinttres.isClickable = false

                val param2 = volver.layoutParams as ConstraintLayout.LayoutParams
                param2.topMargin = 0;
                param2.leftMargin = 800
                volver.layoutParams = param2
            }else {
                cruz++
                Toast.makeText(this, res.getString(R.string.cruz), Toast.LENGTH_SHORT).show()
                win.isEnabled = true;
                val param = win.layoutParams as ConstraintLayout.LayoutParams
                param.topMargin = 0;
                param.rightMargin = 1000
                win.layoutParams = param
                veintidos.isClickable = false;
                veintiuno.isClickable = false
                veinttres.isClickable = false

                val param2 = volver.layoutParams as ConstraintLayout.LayoutParams
                param2.topMargin = 0;
                param2.leftMargin = 800
                volver.layoutParams = param2



            }
        }
    }




}