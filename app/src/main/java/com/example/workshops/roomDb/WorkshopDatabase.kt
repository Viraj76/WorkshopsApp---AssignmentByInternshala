package com.example.workshops.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


//Room data base

@Database(entities = [Workshops::class] , version =  1, exportSchema = false)
abstract class WorkshopDatabase : RoomDatabase() {

    abstract fun workshopDao() : WorkshopDao

    companion object{
        @Volatile
        var INSTANCE : WorkshopDatabase ? = null
        fun getDatabaseInstance(contex : Context) : WorkshopDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this){
                val roomDbInstance =
                    Room.databaseBuilder(contex,WorkshopDatabase::class.java,"Workshops").allowMainThreadQueries().build()
                INSTANCE = roomDbInstance
                return roomDbInstance
            }
        }
    }
}