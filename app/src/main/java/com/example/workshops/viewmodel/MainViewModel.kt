package com.example.workshops.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workshops.roomDb.WorkshopDao
import com.example.workshops.roomDb.WorkshopDatabase
import com.example.workshops.roomDb.Workshops

class MainViewModel (application: Application): AndroidViewModel(application) {

    private var  workshopDao : WorkshopDao  = WorkshopDatabase.getDatabaseInstance(application).workshopDao()
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)


    fun ss(workshops: Workshops) {
        workshopDao.insertCartProduct(workshops)
    }
    // getting workshops from room
     fun getAllWorkshop() : LiveData<List<Workshops>>{
        return workshopDao.getAllWorkshops()
    }

    fun getAppliedWorkshops() : LiveData<List<Workshops>>{
        return workshopDao.getAppliedWorkshops()
    }

    fun updateWorkShop(workshops: Workshops){
        workshopDao.updateWorkShop(workshops)
    }
    // setting login status once the user is logged in
    fun settingLoginStatus(status : Boolean){
        val editor = sharedPreferences.edit()
        editor.apply{
            editor.putBoolean("loginStatus" , status)
        }.apply()
    }

    // getting the login status
    fun gettingLoginStatus() : LiveData<Boolean>{
        val status = MutableLiveData<Boolean>()
        status.value = sharedPreferences.getBoolean("loginStatus" , false)
        return status
    }



}