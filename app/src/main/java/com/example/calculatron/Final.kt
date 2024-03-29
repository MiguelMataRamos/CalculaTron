package com.example.calculatron

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.calculatron.databinding.ActivityFinalBinding
import com.example.calculatron.databinding.ActivityJuegoBinding

class Final : AppCompatActivity() {
    private lateinit var bind: ActivityFinalBinding
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sp = PreferenceManager.getDefaultSharedPreferences(this)

        var acertadas = sp.getInt("acertadas_actual",0)
        var falladas = sp.getInt("falladas_actual",0)
        var cantidad = acertadas + falladas
        var porcentaje = acertadas.toDouble() / cantidad.toDouble() * 100
        bind.textoAcertadas.text = getString(R.string.acertadas_juegoDEF,acertadas)
        bind.textoFalladas.text = getString(R.string.falladas_juegoDEF,falladas)
        bind.porcentaje.text = getString(R.string.porcentaje,porcentaje.toInt())

        var acertadastotal = sp.getInt("acertadas_total",0)
        var falladastotal = sp.getInt("falladas_total",0)
        var cantidadtotal = acertadastotal + falladastotal
        var porcentajetotal = acertadastotal.toDouble() / cantidadtotal.toDouble() * 100
        bind.textoAcertadasTotal.text = getString(R.string.acertadas_totalDEF,acertadastotal)
        bind.textoFalladasTotal.text = getString(R.string.falladas_totalDEF,falladastotal)
        bind.porcentajeTotal.text = getString(R.string.porcentaje_total,porcentajetotal.toInt())
    }

    override fun onBackPressed() {
        var intent = Intent(this,Juego::class.java)
        startActivity(intent)
    }


}