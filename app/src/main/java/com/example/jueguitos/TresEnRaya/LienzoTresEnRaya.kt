package com.example.jueguitos.TresEnRaya

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import com.example.jueguitos.R

class LienzoTresEnRaya(context: Context, cel: Array<Array<Int>>, turno: Boolean, ganar:Boolean, circulo:Int, cruz:Int,llenar:Boolean) : View(context) {
    var cel = cel;
    var turno = turno;
    val ganar = ganar;
    val circulo = circulo;
    var llenar = llenar;
    var cruz = cruz;
    override fun onDraw(canvas: Canvas) {
        canvas.drawRGB(0, 0, 0)
        val pincel1 = Paint()

        pincel1.setARGB(255, 255, 255, 255)

        tablero(canvas,pincel1);
        contador(canvas,pincel1);

        for (i in 0..2) {
            for (j in 0..2) {
                pintar(canvas,pincel1,i,j);
            }
        }

        ganar(canvas,pincel1);
    }

    fun ganar(canvas:Canvas,pincel1:Paint){
        val res = context.resources;
        val ancho = getWidth()
        val alto = getHeight()
        if(ganar){
            pincel1.setARGB(150,255,255,255);
            canvas.drawRect(0F,0F,ancho.toFloat(),alto.toFloat(),pincel1)
            pincel1.textSize = 90F;
            pincel1.setARGB(255,255,0,255);
            canvas.drawRoundRect((ancho/2-700).toFloat(),(alto/2+200).toFloat(),(ancho/2-200).toFloat(),(alto/2-200).toFloat(),80F,80F,pincel1)
            canvas.drawRoundRect((ancho/2+700).toFloat(),(alto/2+200).toFloat(),(ancho/2+150).toFloat(),(alto/2-200).toFloat(),80F,80F,pincel1)
            pincel1.setARGB(255,0,0,0);
            canvas.drawText(res.getString(R.string.reiniciar),(ancho/2-620).toFloat(),(alto/2+20).toFloat(),pincel1);
            canvas.drawText(res.getString(R.string.volver),(ancho/2+280).toFloat(),(alto/2+20).toFloat(),pincel1);

            if(llenar){
                pincel1.textSize = 150F;
                pincel1.isFakeBoldText = true;
                pincel1.setARGB(255,200,200,200);
                canvas.drawText(res.getString(R.string.empate),400F,900F,pincel1);
            }else {
                if (turno) {
                    pincel1.textSize = 150F;
                    pincel1.isFakeBoldText = true;
                    pincel1.setARGB(255, 0, 255, 255);
                    canvas.drawText(res.getString(R.string.circulo), 100F, 900F, pincel1);
                } else {
                    pincel1.textSize = 150F;
                    pincel1.isFakeBoldText = true;
                    pincel1.setARGB(255, 255, 0, 0);
                    canvas.drawText(res.getString(R.string.cruz), 100F, 900F, pincel1);
                }
            }
        }

    }

    fun contador(canvas: Canvas,pincel1: Paint){
        val ancho = getWidth()
        pincel1.setARGB(255,255,255,255);
        pincel1.textSize = 300F;
        canvas.drawText(cruz.toString() + ":" + circulo.toString(),(ancho/2-200).toFloat(),400F,pincel1);
    }

    fun tablero(canvas: Canvas,pincel1: Paint){
        val ancho = getWidth()
        val alto = getHeight()
        canvas.drawRect(
            (ancho / 3).toFloat(),
            600F,
            (ancho / 3 - 50).toFloat(),
            (alto - 50).toFloat(),
            pincel1
        )
        canvas.drawRect(
            (ancho / 3 * 2).toFloat(),
            600F,
            (ancho / 3 * 2 - 50).toFloat(),
            (alto - 50).toFloat(),
            pincel1
        )
        canvas.drawRect(
            50F,
            ((alto) / 3 * 2 + 300).toFloat(),
            (ancho - 50).toFloat(),
            ((alto) / 3 * 2 - 50 + 300).toFloat(),
            pincel1
        )
        canvas.drawRect(
            50F,
            ((alto) / 3 + 300).toFloat(),
            (ancho - 50).toFloat(),
            ((alto) / 3 - 50 + 300).toFloat(),
            pincel1
        )
    }

    fun pintar(canvas:Canvas,pincel1:Paint,i:Int,j:Int){
        if (cel[i][j] == 1) {
            pincel1.setARGB(255, 0, 0, 255)
            circulo(canvas,pincel1,i,j);

        } else if (cel[i][j] == 2) {
            pincel1.setARGB(255, 255, 0, 0)
            cuadrado(canvas,pincel1,i,j);
        }
    }

    fun circulo(canvas: Canvas,pincel1: Paint,i:Int,j:Int){
        val ancho = getWidth()
        val alto = getHeight()
        when (i) {
            0 -> when (j) {
                0 -> canvas.drawCircle(
                    (ancho / 3 / 2).toFloat(),
                    ((alto) / 3 / 2 + 400).toFloat(),
                    150F,
                    pincel1
                );
                1 -> canvas.drawCircle(
                    (ancho / 3 / 2).toFloat(),
                    ((alto) / 3 * 2 / 1.5 + 400).toFloat(),
                    150F,
                    pincel1
                );
                2 -> canvas.drawCircle(
                    (ancho / 3 / 2).toFloat(),
                    ((alto) / 3 * 2.2 + 400).toFloat(),
                    150F,
                    pincel1
                );
            }

            1 -> when (j) {
                0 -> canvas.drawCircle(
                    (ancho / 3 * 1.5).toFloat(),
                    ((alto) / 3 / 2 + 400).toFloat(),
                    150F,
                    pincel1
                );
                1 -> canvas.drawCircle(
                    (ancho / 3 * 1.5).toFloat(),
                    ((alto) / 3 * 2 / 1.5 + 400).toFloat(),
                    150F,
                    pincel1
                );
                2 -> canvas.drawCircle(
                    (ancho / 3 * 1.5).toFloat(),
                    ((alto) / 3 * 2.2 + 400).toFloat(),
                    150F,
                    pincel1
                );
            }

            2 -> when (j) {
                0 -> canvas.drawCircle(
                    (ancho / 3 * 2.5).toFloat(),
                    ((alto) / 3 / 2 + 400).toFloat(),
                    150F,
                    pincel1
                );
                1 -> canvas.drawCircle(
                    (ancho / 3 * 2.5).toFloat(),
                    ((alto) / 3 * 2 / 1.5 + 400).toFloat(),
                    150F,
                    pincel1
                );
                2 -> canvas.drawCircle(
                    (ancho / 3 * 2.5).toFloat(),
                    ((alto) / 3 * 2.2 + 400).toFloat(),
                    150F,
                    pincel1
                );
            }
        }
    }

    fun cuadrado(canvas: Canvas,pincel1: Paint,i: Int,j: Int){
        val ancho = getWidth()
        val alto = getHeight()
        when (i) {
            0 -> when (j) {
                0 -> {
                    canvas.drawRect(
                        (ancho / 3 / 2).toFloat(),
                        (alto / 3 - 300).toFloat(),
                        (ancho / 3 / 2 - 50).toFloat(),
                        ((alto - 600) / 3 + 350).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        50F,
                        ((alto) / 3/2 + 380).toFloat(),
                        (ancho/3 - 100).toFloat(),
                        ((alto) / 3/2 + 430).toFloat(),
                        pincel1)

                }

                1 -> {

                    canvas.drawRect(
                        (ancho / 3 / 2).toFloat(),
                        (alto / 3*2-400).toFloat(),
                        (ancho / 3 / 2 - 50).toFloat(),
                        ((alto - 600) / 3*2 + 450).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        50F,
                        ((alto) / 3*2/2 +750).toFloat(),
                        (ancho/3 - 100).toFloat(),
                        ((alto) / 3*2/2 +800).toFloat(),
                        pincel1)
                }

                2 -> {
                    canvas.drawRect(
                        (ancho / 3 / 2).toFloat(),
                        (alto -500).toFloat(),
                        (ancho / 3 / 2 - 50).toFloat(),
                        ((alto - 600)  + 500).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        50F,
                        ((alto) /2 +1100).toFloat(),
                        (ancho/3 - 100).toFloat(),
                        ((alto) /2 +1150).toFloat(),
                        pincel1)
                }
            }

            1 -> when (j) {
                0 -> {
                    canvas.drawRect(
                        (ancho / 3 *1.5).toFloat(),
                        (alto / 3 - 300).toFloat(),
                        (ancho / 3 *1.5- 50).toFloat(),
                        ((alto - 600) / 3 + 350).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        (ancho/3+50).toFloat(),
                        ((alto) / 3/2 + 380).toFloat(),
                        (ancho/3*2 - 100).toFloat(),
                        ((alto) / 3/2 + 430).toFloat(),
                        pincel1)

                }
                1 -> {

                    canvas.drawRect(
                        (ancho / 3 *1.5).toFloat(),
                        (alto / 3*2-400).toFloat(),
                        (ancho / 3 *1.5- 50).toFloat(),
                        ((alto - 600) / 3*2 + 450).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        (ancho/3+50).toFloat(),
                        ((alto) / 3*2/2 +750).toFloat(),
                        (ancho/3*2 - 100).toFloat(),
                        ((alto) / 3*2/2 +800).toFloat(),
                        pincel1)
                }
                2 -> {
                    canvas.drawRect(
                        (ancho / 3 *1.5).toFloat(),
                        (alto -500).toFloat(),
                        (ancho / 3 *1.5- 50).toFloat(),
                        ((alto - 600)  + 500).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        (ancho/3+50).toFloat(),
                        ((alto) /2 +1100).toFloat(),
                        (ancho/3*2 - 100).toFloat(),
                        ((alto) /2 +1150).toFloat(),
                        pincel1)
                }
            }

            2 -> when (j) {
                0 -> {
                    canvas.drawRect(
                        (ancho / 3 *2.5).toFloat(),
                        (alto / 3 - 300).toFloat(),
                        (ancho / 3 *2.5- 50).toFloat(),
                        ((alto - 600) / 3 + 350).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        (ancho/3*2+50).toFloat(),
                        ((alto) / 3/2 + 380).toFloat(),
                        (ancho - 100).toFloat(),
                        ((alto) / 3/2 + 430).toFloat(),
                        pincel1)

                }
                1 -> {

                    canvas.drawRect(
                        (ancho / 3 *2.5).toFloat(),
                        (alto / 3*2-400).toFloat(),
                        (ancho / 3 *2.5- 50).toFloat(),
                        ((alto - 600) / 3*2 + 450).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        (ancho/3*2+50).toFloat(),
                        ((alto) / 3*2/2 +750).toFloat(),
                        (ancho- 100).toFloat(),
                        ((alto) / 3*2/2 +800).toFloat(),
                        pincel1)
                }
                2 -> {
                    canvas.drawRect(
                        (ancho / 3 *2.5).toFloat(),
                        (alto -500).toFloat(),
                        (ancho / 3 *2.5- 50).toFloat(),
                        ((alto - 600)  + 500).toFloat(),
                        pincel1
                    )
                    canvas.drawRect(
                        (ancho/3*2+50).toFloat(),
                        ((alto) /2 +1100).toFloat(),
                        (ancho- 100).toFloat(),
                        ((alto) /2 +1150).toFloat(),
                        pincel1)
                }
            }
        }
    }
}

