package com.example.workshops.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.workshops.databinding.ProgressDialogBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

import java.time.LocalDate
import java.time.format.DateTimeFormatter


// object file for some common components that can be used across the UI

object Utils {
    private var dialog : AlertDialog? = null

    fun showDialog(context: Context,  message: String){
        val progress = ProgressDialogBinding.inflate(LayoutInflater.from(context))
        progress.tvMessage.text = message
        dialog   = AlertDialog.Builder(context).setView(progress.root).setCancelable(false).create()
        dialog!!.show()
    }

    fun hideDialog(){
        dialog?.dismiss()
    }

    fun showToast(context: Context, message : String){
        Toast.makeText(context,message , Toast.LENGTH_SHORT).show()
    }

}