package com.example.piedrapapeltijeras

import androidx.room.*

@Dao
interface DAO {

    @Query("SELECT * FROM Puntuacion")
    fun obtenerTodo(): MutableList<EntidadPuntuacion>

    @Query("SELECT * FROM Puntuacion WHERE nickName LIKE :nombre")
    suspend fun findbyname(nombre: String): EntidadPuntuacion

    @Query("DELETE FROM Puntuacion")
    suspend fun BorrarTodo()

    @Query("SELECT EXISTS(SELECT * FROM Puntuacion WHERE nickName LIKE :nombre)")
    suspend fun comprobarLista(nombre : String) : Boolean

    @Query("SELECT EXISTS(SELECT maxPunt FROM Puntuacion WHERE nickName = :nombre)")
    suspend fun buscarMaxPuntPorNombre(nombre : String): Int

    @Query("UPDATE Puntuacion SET maxPunt = :puntuacion WHERE nickName = :nombre ")
    suspend fun actualizarPunt(nombre: String, puntuacion: Int): Int

    @Query("UPDATE Puntuacion SET partJugadas = (partJugadas + 1)  WHERE nickName = :nombre ")
    suspend fun actualizarPartidas(nombre: String): Int

    @Insert
    suspend fun insert(tabla: EntidadPuntuacion)

    @Delete
    suspend fun delete(tabla: EntidadPuntuacion)

    @Update
    suspend fun update(tabla: EntidadPuntuacion)



}