package com.example.jueguitos.Minas

import android.widget.ListView

class Tablero {
    companion object {
        val tablero = Array(10) { Array<Celda>(10) { Celda() } }
        var fin = true;
        var ganar:Boolean? = null;
        fun alrrededor(celda:Celda){
            var x:Int = buscarX(celda);
            var y:Int = buscarY(celda);
            ganar = true;
            ganar();

            var bombas:Int = contarBombas(x,y);
            if(!tablero[y][x].bomba && fin){
                tablero[y][x].bombas = bombas;
            }


            if(bombas == 0){
                limpiarRededor(x,y)
            }
        }

        fun limpiarRededor(x:Int,y:Int){
            for(i in -1..1){
                for(j in -1..1){
                    if(y+i < 10 && x+j < 10 && y+i > -1 && x+j > -1 && tablero[y+i][x+j].accion != 1){
                        tablero[y+i][x+j].accion = 1;
                        tablero[y+i][x+j].onCeldaClick()
                    }
                }
            }
        }

        fun buscarX(celda:Celda) : Int{
            var x:Int = -1;
            for (i in 0..<tablero.size){
                if(x == -1){
                    x = tablero[i].indexOf(celda);
                }
            }
            return x;
        }

        fun buscarY(celda:Celda):Int{
            var y:Int = -1;
            for (i in 0..<tablero.size){
                if(tablero[i].indexOf(celda) != -1){
                    y = i;
                }
            }
            return y;
        }

        fun contarBombas(x:Int,y:Int):Int{
            var bombas:Int = 0;
            for(i in -1..1){
                for(j in -1..1){
                    if(y+i < 10 && x+j < 10 && y+i > -1 && x+j > -1 && tablero[y+i][x+j].bomba){
                        bombas ++;
                    }
                }
            }

            return bombas;
        }

        fun ganar(){
            ganar = true;
            for(i in 0.. Tablero.tablero.size-1){
                for(j in 0 .. Tablero.tablero[i].size-1){
                    if(Tablero.tablero[i][j].accion == 0){
                        ganar = false;
                    }else if(Tablero.tablero[i][j].accion == 3 && !Tablero.tablero[i][j].bomba){
                        ganar = false;
                    }
                }
            }
        }

    }


}