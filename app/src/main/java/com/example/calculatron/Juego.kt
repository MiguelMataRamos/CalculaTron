package com.example.calculatron

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calculatron.databinding.ActivityJuegoBinding

class Juego : AppCompatActivity() {

    private lateinit var bind : ActivityJuegoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityJuegoBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }

    fun ajustes(view: View) {
        var intent = Intent(this,Configuracion::class.java)
        startActivity(intent)
    }





}