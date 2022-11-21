package com.example.piedrapapeltijeras

import android.app.Application
import androidx.room.Room

class PuntuaciónApp:Application() {
    companion object {
        lateinit var database: PuntuacionBBDD
    }
    override fun onCreate() {
        super.onCreate()
        PuntuaciónApp.database =  Room.databaseBuilder(this, PuntuacionBBDD::class.java, "DBPuntuacion").build()
    }
}