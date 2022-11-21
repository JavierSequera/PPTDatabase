package com.example.piedrapapeltijeras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.piedrapapeltijeras.databinding.ActivityLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.botonJugar).setOnClickListener {
            var nombreJugador = añadirJugador()
            var cambioSuma = Intent(this, MainActivity::class.java)
            cambioSuma.putExtra("NombreJugador", nombreJugador)
            startActivity(cambioSuma)}
        }

    fun añadirJugador(): String{
        val ETNombre = findViewById<EditText>(R.id.ETNombre)
        val nombreJugador = ETNombre.text.toString()
        val jugador = EntidadPuntuacion(nickName = nombreJugador, partJugadas = 0, maxPunt = 0)
        if(nombreJugador.isNotEmpty()) {

            GlobalScope.launch {
                val existe = PuntuaciónApp.database.DAO().comprobarLista(nombreJugador)
                if (!existe){
                    PuntuaciónApp.database.DAO().insert(jugador)
                }
                ETNombre.text.clear()
            }
        }
        return nombreJugador
    }
}






