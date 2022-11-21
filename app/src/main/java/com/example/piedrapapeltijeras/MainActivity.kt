package com.example.piedrapapeltijeras

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : AppCompatActivity(), Comunicador {

    var maxPuntJugador = 0
    var nombreJugador = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombreJugador = intent.getStringExtra("NombreJugador").toString()

    }

    var numRonda = 0

    //Función que recibe una variable entera que usaremos luego para
    //definir las imágenes de los fragmentos máquina y jugador y realiza la llamada a
    //otros métodos.
    override fun controladora(eleccionJugador: Int){
        //Definición de variables
        var ronda = this.findViewById<TextView>(R.id.ronda)
        var randomGenerator = Random(System.currentTimeMillis())
        var maquina = randomGenerator.nextInt(-1, 2)

        //Aumentamos la ronda cada vez que se llama al método y se muestra
        //en la pantalla el aumento.
        numRonda +=1
        ronda.text = "Turno "+numRonda.toString()

        //Llamada a los demás métodos
        eleccionJugador(eleccionJugador)
        eleccionMaquina(maquina)
        compararResultados(eleccionJugador, maquina, numRonda)

    }

    //Método que establece la imagen del fragmento máquina dependiendo de su valor entero
    override fun eleccionMaquina(eleccion: Int) {

        var maquina = this.findViewById<ImageView>(R.id.maquina)
        maquina.visibility=View.VISIBLE

        if (eleccion == -1){
            maquina.setBackgroundResource(R.drawable.piedra)
        }
        if (eleccion == 0){
            maquina.setBackgroundResource(R.drawable.papel)
        }
        if (eleccion == 1){
            maquina.setBackgroundResource(R.drawable.tijeras)
        }
    }

    //Método que establece la imagen del fragmento jugador dependiendo de su
    //valor entero
    override fun eleccionJugador(eleccion: Int) {
        var jugador = this.findViewById<ImageView>(R.id.jugador)
        jugador.visibility=View.VISIBLE

        if (eleccion == -1){
            jugador.setBackgroundResource(R.drawable.piedra)
        }
        if (eleccion == 0){
            jugador.setBackgroundResource(R.drawable.papel)
        }
        if (eleccion == 1){
            jugador.setBackgroundResource(R.drawable.tijeras)
        }
    }

    //Declaración de variables que luego se van a manipular
    private var victorias = 0
    private var empates = 0
    private var derrotas = 0


    //Método que compara los valores enteros del jugador y de la máquina para
    //verificar y mostrar un mensaje de diáligo si ha ganado, perdido o empatado la
    //ronda el jugador
    override fun compararResultados(eleccionJugador: Int, eleccionMaquina: Int, turno: Int) {
        var textoEmpates = this.findViewById<TextView>(R.id.textoEmpates)
        var textoVictorias = this.findViewById<TextView>(R.id.textoVictorias)
        var textoDerrotas = this.findViewById<TextView>(R.id.textoDerrotas)
        if (eleccionJugador == eleccionMaquina){
                empates+=1
                textoEmpates.text = "E: "+empates
                AlertDialog.Builder(this)
                    .setTitle("Empate")
                    .setMessage("Empataste en la ronda nº$turno")
                    .show()
        }
        else if ((eleccionJugador == 1 && eleccionMaquina == 0) || (eleccionJugador == 0 && eleccionMaquina == -1) || (eleccionJugador == -1 && eleccionMaquina == 1)){
                victorias+=1
                textoVictorias.text = "V: "+victorias
                AlertDialog.Builder(this)
                    .setTitle("¡¡¡Victoria!!!")
                    .setMessage("Ganaste la ronda nº$turno")
                    .show()
        }
            else if ((eleccionMaquina == 1 && eleccionJugador == 0) || (eleccionMaquina == 0 && eleccionJugador == -1) || (eleccionMaquina == -1 && eleccionJugador == 1)) {
            derrotas += 1
            textoDerrotas.text = "D: " + derrotas
            AlertDialog.Builder(this)
                .setTitle("Derrota....")
                .setMessage("Perdiste la ronda nº$turno")
                .show()
        }
    }

    //Método que muestra un diálogo en el que la respuesta positiva provoca el reseteo de las
    //variables de la partida actual empezando una de nuevo
    override fun terminarPartida() {
        var jugador = this.findViewById<ImageView>(R.id.jugador)
        var maquina = this.findViewById<ImageView>(R.id.maquina)
        var textoEmpates = this.findViewById<TextView>(R.id.textoEmpates)
        var textoVictorias = this.findViewById<TextView>(R.id.textoVictorias)
        var textoDerrotas = this.findViewById<TextView>(R.id.textoDerrotas)
        var ronda = this.findViewById<TextView>(R.id.ronda)
        AlertDialog.Builder(this)
            .setTitle("Finalizar partida")
            .setMessage("¿Está seguro de que desea finalizar la partida y empezar una nueva?")
            .setPositiveButton("Si") { dialog, which ->
                guardarResultados()
                empates = 0
                victorias = 0
                derrotas = 0
                textoEmpates.text = "E: "+empates
                textoVictorias.text = "V: "+victorias
                textoDerrotas.text = "D: "+derrotas
                numRonda = 0
                ronda.text = ""
                jugador.visibility = View.INVISIBLE
                maquina.visibility = View.INVISIBLE


            }
//Si utilizamos null como listener, el diálogo simplemente se cierra
            .setNegativeButton("No", null)
            .show()
    }

    fun guardarResultados(){

        var diferencia = victorias - derrotas


            GlobalScope.launch {
                maxPuntJugador = PuntuaciónApp.database.DAO().buscarMaxPuntPorNombre(nombreJugador)
                if (diferencia > maxPuntJugador) {
                    var jugador = PuntuaciónApp.database.DAO().findbyname(nombreJugador)
                    jugador.maxPunt = diferencia
                    PuntuaciónApp.database.DAO().update(jugador)
                }
                PuntuaciónApp.database.DAO().actualizarPartidas(nombreJugador)
            }
    }
}