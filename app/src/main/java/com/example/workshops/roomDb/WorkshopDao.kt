package com.example.workshops.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface WorkshopDao {

    // below are the just sql queries , Room made top of the Sqlite database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertCartProduct(workShops : Workshops)

    @Query("SELECT * FROM Workshops")     // sql query for getting all workshop from local db room.
     fun getAllWorkshops() : LiveData<List<Workshops>>

     @Query("SELECT * FROM Workshops WHERE applied = 1")
     fun getAppliedWorkshops() : LiveData<List<Workshops>>

     @Update
     fun updateWorkShop(workShops: Workshops)

}