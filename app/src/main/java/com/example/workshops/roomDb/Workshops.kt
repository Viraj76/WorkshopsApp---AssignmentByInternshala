package com.example.workshops.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "Workshops")
data class Workshops (
    @PrimaryKey
    val id : String = UUID.randomUUID().toString(),
    val workshopName : String  ?  = null,
    val workShopDate : String ? = null,
    var applied : Int ? = 0
)