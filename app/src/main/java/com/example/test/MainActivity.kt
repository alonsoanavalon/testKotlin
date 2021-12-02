package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

data class Cursos(val nombre:String, val url:String)

class MainActivity : AppCompatActivity() {

    val react = Cursos("React", "react.js")
    val kot = Cursos("Kotlin", "kotlin.kt")
    var cursoActual = react.copy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton = findViewById<Button>(R.id.botoncito)

        val bddtest = findViewById<TextView>(R.id.bddtest)
        val queue = Volley.newRequestQueue(this)
        val url = "https://autonueve.xyz/android"

        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            val JsonArray = JSONArray(response)
        }, Response.ErrorListener {
            println(it)
        })
        queue.add(stringRequest)



        fun verTexto (data:String) {
            val txt = findViewById<TextView>(R.id.texto)
            txt.text = cursoActual.nombre
        }


        fun cambiarCurso(curso:Cursos) {

            var cursoEntrante = curso.copy()

            when (cursoEntrante.nombre) {
                "Kotlin" -> cursoActual = react.copy()
                "React" -> cursoActual = kot.copy()
                else -> "No se que onda, error!"
            }
        }

        fun funcionEnLinea(txt: String) = "${kot.nombre} y ${react.nombre} son lenguajes ${txt} "

        boton.setOnClickListener {
            run {
                verTexto(cursoActual.nombre)
                cambiarCurso(cursoActual)

            }
        }

        boton.text = funcionEnLinea(("Increibles"))





    }
}