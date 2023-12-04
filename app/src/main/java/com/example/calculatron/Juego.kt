package com.example.calculatron

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.calculatron.databinding.ActivityJuegoBinding

class Juego : AppCompatActivity() {

    private lateinit var sp : SharedPreferences
    private lateinit var bind : ActivityJuegoBinding
    private var contador = 20
    private var acertadas = 0
    private var falladas = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityJuegoBinding.inflate(layoutInflater)
        setContentView(bind.root)
        sp = PreferenceManager.getDefaultSharedPreferences(this)

        contador = sp.getString("cuenta_atras","20")!!.toInt()
        bind.textContador.text = contador.toString()

        bind.textoAcertadas.text = getString(R.string.acertadas_juego, acertadas)
        bind.textoFalladas.text = getString(R.string.falladas_juego, falladas)

    }




    fun ajustes(view: View) {
        var intent = Intent(this,Configuracion::class.java)
        startActivity(intent)
    }







}