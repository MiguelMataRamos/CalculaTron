package com.example.calculatron

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceManager
import com.example.calculatron.databinding.ActivityConfiguracionBinding

class Configuracion : AppCompatActivity() {
    private lateinit var bind : ActivityConfiguracionBinding
    private lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityConfiguracionBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sp = PreferenceManager.getDefaultSharedPreferences(this)

        var tiempo = sp.getString("cuenta_atras","25")
        bind.cuenta.setText(tiempo)
        var min = sp.getString("minimo","0")
        bind.min.setText(min)
        var max = sp.getString("maximo","20")
        bind.max.setText(max)

        var suma = sp.getBoolean("suma",true)
        bind.suma.isChecked = suma
        var resta = sp.getBoolean("resta",true)
        bind.resta.isChecked = resta
        var multi = sp.getBoolean("multiplicacion",false)
        bind.multi.isChecked = multi
        var divi = sp.getBoolean("division",false)
        bind.div.isChecked = divi


    }

    override fun onBackPressed() {
        var intent = Intent(this,Juego::class.java)
        startActivity(intent)
    }

    fun guardar(view: View) {

        var cuenta = bind.cuenta.text.toString()
        var min = bind.min.text.toString()
        var max = bind.max.text.toString()
        if (min >= max){
            min = "0"
        }
        var suma = bind.suma.isChecked
        var resta = bind.resta.isChecked
        var multi = bind.multi.isChecked
        var divi = bind.div.isChecked
        if (!suma && !resta && !multi && !divi){
            suma = true
            bind.suma.isChecked = true
            resta = true
            bind.resta.isChecked = true
        }

        sp.edit().apply{
            putString("cuenta_atras",cuenta)
            putString("minimo",min)
            putString("maximo",max)
            putBoolean("suma",suma)
            putBoolean("resta",resta)
            putBoolean("multiplicacion",multi)
            putBoolean("division",divi)
            apply()
        }

        var intent = Intent(this,Juego::class.java)
        startActivity(intent)

    }


}