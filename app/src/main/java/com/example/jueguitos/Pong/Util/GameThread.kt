package com.example.pong.Util

import android.content.ContentValues
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.SurfaceHolder
import android.view.View
import android.widget.Button
import com.example.jueguitos.BBDD.AdminBase
import com.example.jueguitos.MainActivity
import com.example.jueguitos.Pong.PongMain
import com.example.jueguitos.R
import com.example.pong.Modelos.PongTable
import java.lang.Exception

class GameThread(
    private val mCtx: Context,
    private val mSurfaceHolder: SurfaceHolder,
    private val mPongTable: PongTable,
    private val mGameStatusHandler: Handler,
    private val mScoreHandler: Handler
) :
    Thread() {
    private val mSensorsOn = false
    private var mRun = false
    private var mGameState = 0
    private val mRunLock: Any = Any()
    override fun run() {
        var mNextGameTick = SystemClock.uptimeMillis()
        val skipTicks = 1000 / PHYS_FPS
        while (mRun) {
            var c: Canvas? = null
            try {
                c = mSurfaceHolder.lockCanvas(null)
                if (c != null) {
                    synchronized(mSurfaceHolder) {
                        if (mGameState == STATE_RUNNING) {
                            mPongTable.update(c)
                        }
                        synchronized(mRunLock) {
                            if (mRun) {
                                mPongTable.draw(c)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (c != null) {
                    mSurfaceHolder.unlockCanvasAndPost(c)
                }
            }
            mNextGameTick += skipTicks.toLong()
            val sleepTime = mNextGameTick - SystemClock.uptimeMillis()
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun setState(state: Int) {
        synchronized(mSurfaceHolder) {
            mGameState = state
            val res = mCtx.resources
            when (mGameState) {
                STATE_READY -> setUpNewRound()
                STATE_RUNNING -> hideStatusText()
                STATE_WIN -> {
                    setStatusText(res.getString(R.string.ganar))
                    mPongTable.player!!.score++
                    PongMain.miPunuacion?.text = (PongMain.miPunuacion?.text.toString().toInt()+1).toString()
                    setUpNewRound()
                    puntos(10)
                }
                STATE_LOSE -> {
                    setStatusText(res.getString(R.string.perder))
                    mPongTable.getMOpponent()!!.score++
                    setUpNewRound()
                    PongMain.oponente?.text = (PongMain.oponente?.text.toString().toInt()+1).toString()
                    puntos(-5)
                }
                STATE_PAUSED -> setStatusText(res.getString(R.string.estaado))
            }
        }
    }

    fun puntos(puntos:Int){
        val admin = AdminBase(this.mCtx!!, "usuario", null, 1)
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

    fun setUpNewRound() {
        synchronized(mSurfaceHolder) { mPongTable.setupTable() }
    }

    fun setRunning(running: Boolean) {
        synchronized(mRunLock) { mRun = running }
    }

    fun SensorsOn(): Boolean {
        return mSensorsOn
    }

    val isBetweenRounds: Boolean
        get() = mGameState != STATE_RUNNING

    private fun setStatusText(text: String) {
        val msg = mGameStatusHandler.obtainMessage()
        val b = Bundle()
        b.putString("text", text)
        b.putInt("visibility", View.VISIBLE)
        msg.data = b
        mGameStatusHandler.sendMessage(msg)
    }

    private fun hideStatusText() {
        val msg = mGameStatusHandler.obtainMessage()
        val b = Bundle()
        b.putInt("visibility", View.INVISIBLE)
        msg.data = b
        mGameStatusHandler.sendMessage(msg)
    }

    fun setScoreText(playerScore: String?, opponentScore: String?) {
        val msg = mScoreHandler.obtainMessage()
        val b = Bundle()
        b.putString("player", playerScore)
        b.putString("opponent", opponentScore)
        msg.data = b
        mScoreHandler.sendMessage(msg)
    }


}