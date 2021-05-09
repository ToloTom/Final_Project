package com.example.final_project

import android.app.Application

class EquipmentApplication : Application() {

    val databse by lazy {EquipmentRoomDatabase.getDatabase(this)}
    val repository by lazy {EquipmentRepository(databse.equipmentDAO())}
}