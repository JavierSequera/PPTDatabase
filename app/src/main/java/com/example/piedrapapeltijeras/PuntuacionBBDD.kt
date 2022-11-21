package com.example.piedrapapeltijeras

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.contracts.contract

@Database(entities = [EntidadPuntuacion::class], version = 1)
abstract class PuntuacionBBDD : RoomDatabase() {
    abstract fun DAO(): DAO
}