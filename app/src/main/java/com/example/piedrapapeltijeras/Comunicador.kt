package com.example.piedrapapeltijeras

interface Comunicador {
    fun controladora(eleccionJugador: Int)
    fun compararResultados(eleccionJugador: Int, eleccionMaquina: Int, turno: Int)
    fun eleccionMaquina(eleccion:Int)
    fun eleccionJugador(eleccion:Int)
    fun terminarPartida()
}