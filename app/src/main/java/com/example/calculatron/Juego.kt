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
    var contador = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityJuegoBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sp = PreferenceManager.getDefaultSharedPreferences(this)
        contador = sp.getString("cuenta_atras","20")!!.toInt()

    }

    private fun actualizarContador() {
        bind.textContador.setText("Contador: $contador")
    }


    fun ajustes(view: View) {
        var intent = Intent(this,Configuracion::class.java)
        startActivity(intent)
    }





}