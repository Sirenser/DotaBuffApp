package com.example.screenapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

//Тут хочу сделать сервис с уведомлениями о появлении нового матча ( Тип чел сыграл и ему уведомлялка, что появился новый матч в списке)

class NewRecentMatchService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}