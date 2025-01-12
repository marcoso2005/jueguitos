package com.example.jueguitos.Minas

import android.content.ContentValues
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.jueguitos.BBDD.AdminBase
import com.example.jueguitos.MainActivity
import com.example.jueguitos.R

class Celda() {
    var context: Context? = null;
    var celda: ConstraintLayout? = null;
    var par: Boolean? = null;
    var bomba: Boolean = false;
    var accion: Int? = null
    var bombas: Int = 0;


    constructor(celda: ConstraintLayout, accion: Int, par: Boolean, context: Context) : this() {
        this.celda = celda;
        this.par = par;
        this.context = context;
        this.accion = accion
        this.celda?.setOnClickListener {
            // Acción que quieres que se ejecute al hacer clic en la celda
            onCeldaClick()
        }

        val fondo = Lienzo(this.context, this.par, this.accion, bombas, bomba)
        this.celda!!.addView(fondo)

    }

    fun onCeldaClick() {
        var res = context?.resources;
        MainMinas.primer = false;
        if (accion == 3 && MainMinas.desactivar && Tablero.fin && Tablero.ganar == false) {
            accion = 0;
            Tablero.alrrededor(this);
            val fondo = Lienzo(context, par, this.accion, bombas, bomba);
            celda!!.addView(fondo)
        } else if (MainMinas.desactivar && accion != 1 && Tablero.fin && Tablero.ganar == false) {
            accion = 3;
            val fondo = Lienzo(context, par, this.accion, bombas, bomba);
            celda!!.addView(fondo)
        } else if (!bomba && Tablero.fin && Tablero.ganar == false && accion != 3) {
            accion = 1;
            Tablero.alrrededor(this);
            if (Tablero.ganar == true) {
                MainMinas.texto?.text = res?.getString(R.string.ganar)
                MainMinas.texto?.visibility = View.VISIBLE;
                MainMinas.reiniciar?.isEnabled = true;
                MainMinas.reiniciar?.visibility = View.VISIBLE;
                puntos(30)
            }
            val fondo = Lienzo(context, par, this.accion, bombas, bomba);
            celda!!.addView(fondo)
        } else if (Tablero.fin && Tablero.ganar == false && accion != 3) {
            MainMinas.texto?.text = res?.getString(R.string.perder)
            puntos(-20)
            accion = 2;
            MainMinas.texto?.visibility = View.VISIBLE;
            Tablero.fin = false;
            destaparBombas()
            val fondo = Lienzo(context, par, this.accion, bombas, bomba);
            celda!!.addView(fondo)
            MainMinas.reiniciar?.isEnabled = true;
            MainMinas.reiniciar?.visibility = View.VISIBLE;

        }
    }

    fun puntos(puntos:Int){
        val admin = AdminBase(this.context!!, "usuario", null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()

        if (!MainActivity.nombre.isNullOrBlank()) {
            val cursor = bd.rawQuery(
                "SELECT puntuacion FROM usuario WHERE nombre = ?",
                arrayOf(MainActivity.nombre)
            )

            if (cursor.moveToFirst()) {
                val puntuacionActual = cursor.getInt(0)
                var nuevaPuntuacion:Int = 0
                if(puntuacionActual + puntos > 0)
                    nuevaPuntuacion = puntuacionActual + puntos

                registro.put("puntuacion", nuevaPuntuacion)
                bd.update("usuario", registro, "nombre=?", arrayOf(MainActivity.nombre))
            }
            cursor.close()
        }
        bd.close()

    }

    fun destaparBombas() {
        for (i in 0..Tablero.tablero.size - 1) {
            for (j in 0..Tablero.tablero[i].size - 1) {
                if (Tablero.tablero[i][j].accion == 2) {
                    Tablero.tablero[i][j].onCeldaClick()
                }
            }
        }
    }
}


class Lienzo(context: Context?, par: Boolean?, accion: Int?, bombas: Int, bomba: Boolean) :
    View(context) {
    var par = par
    var accion = accion;
    var bombas = bombas.toString();
    var bomba = bomba;
    override fun onDraw(canvas: Canvas) {
        canvas.drawRGB(0, 0, 0)
        val ancho = getWidth().toFloat()
        val alto = getHeight().toFloat()
        val pincel1: Paint = color()

        canvas.drawRect(0F, 0F, ancho, alto, pincel1)
        if (accion == 1) {
            destapar(canvas, pincel1, ancho.toFloat(), alto.toFloat());
        }
    }

    fun destapar(canvas: Canvas, pincel: Paint, ancho: Float, alto: Float) {
        pincel.setARGB(255, 0, 0, 0);
        pincel.textSize = 100F;
        if (!bomba && !bombas.equals("0")) {
            canvas.drawText(bombas, ancho / 2 - 30, alto / 2 + 30, pincel);
        }
    }

    fun color(): Paint {
        var pincel1 = Paint();
        if (accion == 1) {
            if (par!!) {
                pincel1.setARGB(255, 255, 228, 196);
            } else {
                pincel1.setARGB(255, 222, 184, 135);
            }


        } else if (accion == 0) {
            if (par!!) {
                pincel1.setARGB(255, 0, 255, 0);
            } else {
                pincel1.setARGB(255, 0, 150, 0);
            }
        } else if (accion == 3) {
            pincel1.setARGB(255, 255, 255, 0);
        } else if (bomba) {
            pincel1.setARGB(255, 255, 0, 0)
        }

        return pincel1;
    }
}


