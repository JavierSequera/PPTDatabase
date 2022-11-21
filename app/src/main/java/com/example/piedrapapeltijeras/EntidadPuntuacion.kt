package com.example.piedrapapeltijeras

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "Puntuacion")
data class EntidadPuntuacion (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var nickName:String = "",
    var maxPunt: Int = 0,
    var partJugadas: Int = 0
)