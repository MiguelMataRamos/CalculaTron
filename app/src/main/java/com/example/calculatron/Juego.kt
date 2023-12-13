package com.example.calculatron

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.calculatron.databinding.ActivityJuegoBinding
import java.util.Random


class Juego : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var sp: SharedPreferences
    private lateinit var bind: ActivityJuegoBinding
    private var contador = 20
    private var contadormili = contador * 1000
    private var acertadas = 0
    private var falladas = 0
    private var acertadastotal = 0
    private var falladastotal = 0
    private var contadorempezado = false

    private lateinit var operaciones: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        super.onCreate(savedInstanceState)

        operaciones = mutableListOf()

        var suma = sp.getBoolean("suma", true)
        if (suma) {
            operaciones.add("+")
        }
        var resta = sp.getBoolean("resta", true)
        if (resta) {
            operaciones.add("-")
        }
        var multi = sp.getBoolean("multiplicacion", true)
        if (multi) {
            operaciones.add("*")
        }
        var divi = sp.getBoolean("division", true)
        if (divi) {
            operaciones.add("/")
        }

        bind = ActivityJuegoBinding.inflate(layoutInflater)
        setContentView(bind.root)

        contador = sp.getString("cuenta_atras", "20")!!.toInt()
        bind.textContador.text = contador.toString()
        contadormili = contador * 1000

        bind.textoAcertadas.text = getString(R.string.acertadas_juegoDEF,acertadas)
        bind.textoFalladas.text = getString(R.string.falladas_juegoDEF, falladas)

        acertadastotal = sp.getInt("acertadas_total",0)
        falladastotal = sp.getInt("falladas_total",0)


        //Contador
        countDownTimer = object : CountDownTimer(contadormili.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Actualiza el TextView en cada tick
                bind.textContador.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                // Acciones a realizar cuando el contador llega a cero
                bind.textContador.text = "0"
                acertadastotal += acertadas
                falladastotal += falladas
                sp.edit().apply{
                    putInt("acertadas_actual",acertadas)
                    putInt("falladas_actual",falladas)
                    putInt("acertadas_total",acertadastotal)
                    putInt("falladas_total",falladastotal)
                    apply()
                }
                var intent = Intent(applicationContext, Final::class.java)
                startActivity(intent)
            }
        }
        rotacion()

        if (bind.textContador.text == "0"){
            recreate()
        }
    }



    private fun generarOperacion(): String {

        val random = Random()
        var operacion = operaciones.random()

        val min = sp.getString("minimo", "0")!!.toInt()
        val max = sp.getString("maximo", "100")!!.toInt()

        val n1 = random.nextInt(max - min) + min
        val n2 = random.nextInt(max - min) + min

        return n1.toString() + operacion + n2.toString()

    }

    private fun operacionPasada() {
        bind.operacionAnterior.text = sp.getString("operacion_actual", "0")

        sp.edit().apply {
            putString("operacion_anterior", sp.getString("operacion_actual", "0"))
            apply()
        }

    }

    private fun operacionActual() {

        sp.edit().apply {
            putString("operacion_actual", sp.getString("siguiente_operacion", "0"))
            apply()
        }

        bind.operacionActual.text = sp.getString("operacion_actual", "0") + "="

    }

    private fun siguienteOperacion() {

        var res = generarOperacion()

        sp.edit().apply {
            putString("siguiente_operacion", res)
            apply()
        }

        bind.operacionSiguiente.text = sp.getString("siguiente_operacion", "0")

    }

    private fun rotacion() {
        operacionPasada()
        operacionActual()
        siguienteOperacion()
    }

    private fun comprobarResultado(operacion: String, respuesta: Int): Boolean {
        var numeros: List<String>
        var total: Int = 0
        var op = operacion.replace("=","")

        if (op.contains("+")) {
            numeros = op.split("+")
            var n1 = numeros[0].toInt()
            var n2 = numeros[1].toInt()
            total = n1 + n2
        }

        if (op.contains("-")) {
            numeros = op.split("-")
            total = numeros[0].toInt() - numeros[1].toInt()
        }

        if (op.contains("*")) {
            numeros = op.split("*")
            total = numeros[0].toInt() * numeros[1].toInt()
        }

        if (op.contains("/")) {
            numeros = op.split("/")
            total = numeros[0].toInt() / numeros[1].toInt()
        }

        var iguales = false

        if (respuesta == total){
            acertadas++
            bind.textoAcertadas.text = getString(R.string.acertadas_juegoDEF, acertadas)
            iguales = true
        }else{
            falladas++
            bind.textoFalladas.text = getString(R.string.falladas_juegoDEF, falladas)
        }

        return iguales

    }


    fun ajustes(view: View) {
        var intent = Intent(this, Configuracion::class.java)
        countDownTimer.cancel()
        startActivity(intent)
    }

    fun boton(view: View) {
        when (view.id) {
            R.id.b0 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "0"
                bind.respuesta.text = respuesta
            }

            R.id.b1 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "1"
                bind.respuesta.text = respuesta

            }

            R.id.b2 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "2"
                bind.respuesta.text = respuesta
            }

            R.id.b3 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "3"
                bind.respuesta.text = respuesta
            }

            R.id.b4 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "4"
                bind.respuesta.text = respuesta
            }

            R.id.b5 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "5"
                bind.respuesta.text = respuesta
            }

            R.id.b6 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "6"
                bind.respuesta.text = respuesta
            }

            R.id.b7 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "7"
                bind.respuesta.text = respuesta
            }

            R.id.b8 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "8"
                bind.respuesta.text = respuesta
            }

            R.id.b9 -> {
                var respuesta = bind.respuesta.text.toString()
                respuesta += "9"
                bind.respuesta.text = respuesta
            }

            R.id.bigual -> {
                if (!contadorempezado) {
                    countDownTimer.start()
                    contadorempezado = true
                }

                if (!bind.respuesta.text.isNullOrBlank()){
                    var respuesta = bind.respuesta.text.toString().toInt()
                    var operacion = bind.operacionActual.text.toString()
                    comprobarResultado(operacion,respuesta)
                    rotacion()
                    bind.respuesta.text = null
                }


            }

            R.id.bc -> {
                var respuesta = bind.respuesta.text.toString()
                if (!respuesta.isNullOrBlank()){
                    respuesta = respuesta.substring(0, respuesta.length - 1)
                    bind.respuesta.text = respuesta
                }

            }

            R.id.bce -> {
                bind.respuesta.text = null
            }
        }
    }


}